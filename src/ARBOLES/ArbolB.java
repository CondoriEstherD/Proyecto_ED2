package ARBOLES;
import java.util.*;
public class ArbolB <T extends  Comparable<T>> extends ArbolMViasBase<T> implements IArbolBase <T> {
    public ArbolB() {
        super.orden = ORDEN_MINIMO;
    }

    public ArbolB(int orden) {
        if (orden < ORDEN_MINIMO) {
            throw new IllegalArgumentException("Orden del arblo deben de ser almenos 3");
        }
        super.orden = orden;
    }

    private int getNroMaximoDeHijos() {
        return orden;
    }

    private int getNroMaximoDeDatos() {
        return orden - 1;
    }

    private int getNroMinimoDeDatos() {
        return getNroMaximoDeDatos() / 2;
    }

    private int getNroMinimoDeHijos() {
        return getNroMinimoDeDatos() + 1;
    }

   
    @Override
    public void insertar(T datoAInsertar) {
        if (datoAInsertar == null) {
            throw new IllegalArgumentException(" No se insertan datos nulos en elarbol");
        }
        if (esArbolVacio()) {
            this.raiz = crearNodo(datoAInsertar);
            return;
        }
        NodoMVias<T> nodoEnTurno = raiz;
        Stack<NodoMVias<T>> pilaDeNodos = new Stack<>();
        do {
            int posDelDatoEnNodo = obtenerPosicionDeldatoNodo(nodoEnTurno, datoAInsertar);
            if (posDelDatoEnNodo != POSICION_INVALIDAD) {
                throw new IllegalArgumentException("Nose insertan datos repetidos");
            }
            if (esHoja(nodoEnTurno)) {
                if (!estanLosDatosLlenos(nodoEnTurno)) {
                    insertarDatoOrdenadoEnElNodo(nodoEnTurno, datoAInsertar);
                } else {
                    dividirYEmpujar(nodoEnTurno, pilaDeNodos, datoAInsertar);
                }
                nodoEnTurno = NODO_VACIO;
            } else {
                int posPorDondeBajar = obtenerPosicionPorDondeBajar(nodoEnTurno, datoAInsertar);
                pilaDeNodos.push(nodoEnTurno);
                nodoEnTurno = nodoEnTurno.hijos.get(posPorDondeBajar);
            }
        } while (!esNodoVacio(nodoEnTurno));
    }

     // metodo auxiliar del insertar

    public boolean estanLosDatosLlenos(NodoMVias<T> nodoActul) {
        return nroDatosNoVacios(nodoActul) == getNroMaximoDeDatos();
    }

    public void insertarDatoOrdenadoEnElNodo(NodoMVias<T> nodoActual, T datoAInsertar) {
        List<T> listaDeDatos = new ArrayList<>();
        for (int i = 0; i < nroDatosNoVacios(nodoActual); i++) {
            listaDeDatos.add(nodoActual.datos.get(i));
        }
        listaDeDatos.add(datoAInsertar);
        Collections.sort(listaDeDatos);
        for (int i = 0; i < listaDeDatos.size(); i++) {
            nodoActual.datos.set(i, listaDeDatos.get(i));
        }
    }

    public void dividirYEmpujar(NodoMVias<T> nodoActual, Stack<NodoMVias<T>> pilaDeNodos, T dato) {
        NodoMVias<T> nodoAntiguo = nodoActual;
        NodoMVias<T> nodoAnterior = NODO_VACIO;
        NodoMVias<T> nodoPosterior = NODO_VACIO;
        T datoAcolocar = dato;
        while (estanLosDatosLlenos(nodoActual)) {
            List<NodoMVias<T>> listaDeHijos = obtenerListaDehijos(nodoActual);
            List<T> listaDeDatos = obtenerdatoActualizados(nodoActual, datoAcolocar);
            if (!esNodoVacio(nodoAnterior) && !esNodoVacio(nodoPosterior)) {
                insertarHijosActualizados(listaDeHijos, nodoAntiguo, nodoAnterior, nodoPosterior);
            }
            int posDatoASubir = getNroMinimoDeDatos();
            T datoPadre = listaDeDatos.get(posDatoASubir);
            NodoMVias<T> nuevoNodoAnterior = obtenerNodoAnterior(listaDeDatos, listaDeHijos, posDatoASubir);
            NodoMVias<T> nuevoNodoPosterior = obtenerNodoPosterior(listaDeDatos, listaDeHijos, posDatoASubir);
            if (pilaDeNodos.isEmpty()) {
                NodoMVias<T> nuevaRaiz = crearNodo(datoPadre);
                nuevaRaiz.hijos.set(0, nuevoNodoAnterior);
                nuevaRaiz.hijos.set(1, nuevoNodoPosterior);
                this.raiz = nuevaRaiz;
                return;
            }
            NodoMVias<T> nodoabuelo = pilaDeNodos.pop();
            if (!estanLosDatosLlenos(nodoabuelo)) {
                int posHijo = buscarPosicinDelHijo(nodoabuelo, nodoActual);
                insertarHijosEnPadre(nodoabuelo, posHijo, nuevoNodoAnterior, nuevoNodoPosterior);
                insertarDatoOrdenadoEnElNodo(nodoabuelo, datoPadre);
                return;
            }
            nodoAntiguo = nodoActual;
            nodoAnterior = nuevoNodoAnterior;
            nodoPosterior = nuevoNodoPosterior;
            nodoActual = nodoabuelo;
            datoAcolocar = datoPadre;
        }
    }

    public void insertarHijosEnPadre(
            NodoMVias<T> nodoPadre,
            int posHijo,
            NodoMVias<T> nodoAnterior,
            NodoMVias<T> nodoPosterior) {
        if (posHijo == POSICION_INVALIDAD) {
            throw new IllegalStateException("No se encontró el hijo en el padre");
        }
        List<NodoMVias<T>> listaDeHijos = new ArrayList<>();
        for (int i = 0; i <= nroDatosNoVacios(nodoPadre); i++) {
            listaDeHijos.add(nodoPadre.hijos.get(i));
        }
        listaDeHijos.remove(posHijo);
        listaDeHijos.add(posHijo, nodoAnterior);
        listaDeHijos.add(posHijo + 1, nodoPosterior);
        for (int i = 0; i < nodoPadre.hijos.size(); i++) {
            nodoPadre.hijos.set(i, NODO_VACIO);
        }
        for (int i = 0; i < listaDeHijos.size(); i++) {
            nodoPadre.hijos.set(i, listaDeHijos.get(i));
        }
    }

    public List<NodoMVias<T>> obtenerListaDehijos(NodoMVias<T> nodoActual) {
        List<NodoMVias<T>> listaDehijos = new ArrayList<>();
        for (int i = 0; i < orden; i++) {
            listaDehijos.add(nodoActual.hijos.get(i));
        }
        return listaDehijos;
    }

    public NodoMVias<T> obtenerNodoAnterior(List<T> listaDeDatos,
                                            List<NodoMVias<T>> listaDehijos, int poshijo) {
        NodoMVias<T> nodoAnterior = crearNodoVacio();
        for (int i = 0; i < poshijo; i++) {
            T datoPorTurno = listaDeDatos.get(i);
            nodoAnterior.datos.set(i, datoPorTurno);
        }
        for (int i = 0; i <= poshijo; i++) {
            NodoMVias<T> nodoEnTurno = listaDehijos.get(i);
            nodoAnterior.hijos.set(i, nodoEnTurno);
        }
        return nodoAnterior;
    }

    public NodoMVias<T> obtenerNodoPosterior(List<T> listaDeDatos,
                                             List<NodoMVias<T>> listaDehijos, int poshijo) {
        NodoMVias<T> nodoPosterior = crearNodoVacio();
        int j = 0;
        for (int i = poshijo + 1; i < listaDeDatos.size(); i++) {
            T datoPorTurno = listaDeDatos.get(i);
            nodoPosterior.datos.set(j, datoPorTurno);
            j++;
        }
        j = 0;
        for (int i = poshijo + 1; i < listaDehijos.size(); i++) {
            NodoMVias<T> nodoEnTurno = listaDehijos.get(i);
            nodoPosterior.hijos.set(j, nodoEnTurno);
            j++;
        }
        return nodoPosterior;
    }

    public List<T> obtenerdatoActualizados(NodoMVias<T> nodoActual, T datoASubir) {
        List<T> listaActualizada = new ArrayList<>();
        for (int i = 0; i < nroDatosNoVacios(nodoActual); i++) {
            listaActualizada.add(nodoActual.datos.get(i));
        }
        listaActualizada.add(datoASubir);
        Collections.sort(listaActualizada);
        return listaActualizada;
    }

    public void insertarHijosActualizados(List<NodoMVias<T>> listaDeHijos, NodoMVias<T> nodoActual,
                                          NodoMVias<T> nodoAnterior, NodoMVias<T> nodoPosterior) {
        int posHijo = listaDeHijos.indexOf(nodoActual);
        if (posHijo == POSICION_INVALIDAD) {
            throw new IllegalStateException("No se encontró el hijo dividido");
        }
        listaDeHijos.remove(posHijo);
        listaDeHijos.add(posHijo, nodoAnterior);
        listaDeHijos.add(posHijo + 1, nodoPosterior);
    }

    public NodoMVias<T> crearNodoVacio() {
        return new NodoMVias<>(this.orden, (T) DATO_VACIO);
    }

    public int buscarPosicinDelHijo(NodoMVias<T> nodoPadre, NodoMVias<T> nodoBuscado) {
        for (int i = 0; i < orden; i++) {
            if (nodoPadre.hijos.get(i) == nodoBuscado) {
                return i;
            }
        }
        return POSICION_INVALIDAD;
    }

    

    public int obtenerPosicionPorDondeBajar(NodoMVias<T> nodoActual, T dato) {
        for (int i = 0; i < nroDatosNoVacios(nodoActual); i++) {
            T datoDelNodo = nodoActual.datos.get(i);
            if (dato.compareTo(datoDelNodo) < 0) {
                return i;
            }
        }
        return nroDatosNoVacios(nodoActual);
    }

    public int obtenerPosicionDeldatoNodo(NodoMVias<T> nodoActal, T datoBuscado) {
        if (datoBuscado == null) {
            throw new IllegalArgumentException("NO se aceptan datos nulos");
        }
        for (int i = 0; i < nroDatosNoVacios(nodoActal); i++) {
            T dato = nodoActal.datos.get(i);
            if (dato.compareTo(datoBuscado) == 0) {
                return i;
            }
        }
        return POSICION_INVALIDAD;
    }

    

    @Override
    public void eliminar(T datoAEliminar) {
        if (datoAEliminar == null) {
            throw new IllegalArgumentException("El arbol no permite datos nulos");
        }

        NodoMVias<T> nodoActual = raiz;
        Stack<NodoMVias<T>> pilaDeNodos = new Stack<>();
        NodoMVias<T> nodoDelDatoAEliminar = NODO_VACIO;
        int posDelDatoAEliminar = POSICION_INVALIDAD;
        while (!esNodoVacio(nodoActual)) {
            int posDelDatoEnNodo = obtenerPosicionDeDatoEnNodo(nodoActual, datoAEliminar);
            if (posDelDatoEnNodo != POSICION_INVALIDAD) {
                nodoDelDatoAEliminar = nodoActual;
                posDelDatoAEliminar = posDelDatoEnNodo;
                nodoActual = NODO_VACIO;
            } else {
                int posPorDondeBajar = obtenerPosicionPorDondeBajar(
                        nodoActual, datoAEliminar
                );
                pilaDeNodos.push(nodoActual);
                nodoActual = nodoActual.hijos.get(posPorDondeBajar);
            }
        }
        if (esNodoVacio(nodoDelDatoAEliminar)) {
            throw new IllegalStateException("Dato no esta en el arbol");
        }

        if (esHoja(nodoDelDatoAEliminar)) {
            eliminarDatoDePosicion(nodoDelDatoAEliminar, posDelDatoAEliminar);
            if (nodoDelDatoAEliminar == raiz && nroDatosNoVacios(raiz) == 0) {
                raiz = NODO_VACIO;
                return;
            }
            if (nodoDelDatoAEliminar != raiz
                    && nroDatosNoVacios(nodoDelDatoAEliminar) < getNroMinimoDeDatos()) {
                prestarseOFusionarse(nodoDelDatoAEliminar, pilaDeNodos);
            }
        } else {
            pilaDeNodos.push(nodoDelDatoAEliminar);
            NodoMVias<T> nodoDelPredecesor = obtenerNodoDelPredecesor(
                    nodoDelDatoAEliminar.hijos.get(posDelDatoAEliminar), pilaDeNodos
            );
            T datoPredecesor = nodoDelPredecesor.datos.get(
                    nroDatosNoVacios(nodoDelPredecesor) - 1
            );
            nodoDelPredecesor.datos.set(
                    nroDatosNoVacios(nodoDelPredecesor) - 1, (T) DATO_VACIO
            );
            nodoDelDatoAEliminar.datos.set(posDelDatoAEliminar, datoPredecesor);
            if (nroDatosNoVacios(nodoDelPredecesor) < getNroMinimoDeDatos()) {
                prestarseOFusionarse(nodoDelPredecesor, pilaDeNodos);
            }
        }
    }

    // metodo auxiliar del eliminar 

    private int obtenerPosicionDeDatoEnNodo(NodoMVias<T> nodoActual, T datoBuscado) {
        return obtenerPosicionDeldatoNodo(nodoActual, datoBuscado);
    }

    private NodoMVias<T> obtenerNodoDelPredecesor(
            NodoMVias<T> nodoActual,
            Stack<NodoMVias<T>> pilaDeNodos) {
        NodoMVias<T> nodoEnTurno = nodoActual;
        while (!esHoja(nodoEnTurno)) {
            pilaDeNodos.push(nodoEnTurno);
            nodoEnTurno = nodoEnTurno.hijos.get(nroDatosNoVacios(nodoEnTurno));
        }
        return nodoEnTurno;
    }

    private void prestarseOFusionarse(
            NodoMVias<T> nodoConProblema,
            Stack<NodoMVias<T>> pilaDeNodos) {
        if (nodoConProblema == raiz) {
            if (nroDatosNoVacios(raiz) == 0) {
                raiz = primerHijoNoVacio(raiz);
            }
            return;
        }
        if (nroDatosNoVacios(nodoConProblema) >= getNroMinimoDeDatos()) {
            return;
        }

        NodoMVias<T> nodoPadre = pilaDeNodos.peek();
        int posicionDelHijo = buscarPosicinDelHijo(nodoPadre, nodoConProblema);
        corregirPrestamoOFusion(nodoPadre, posicionDelHijo);

        if (nodoPadre == raiz && nroDatosNoVacios(raiz) == 0) {
            raiz = primerHijoNoVacio(raiz);
            return;
        }
        if (nodoPadre != raiz && nroDatosNoVacios(nodoPadre) < getNroMinimoDeDatos()) {
            pilaDeNodos.pop();
            prestarseOFusionarse(nodoPadre, pilaDeNodos);
        }
    }

    private void corregirPrestamoOFusion(NodoMVias<T> nodoPadre, int posicionDelHijo) {
        NodoMVias<T> hijoConProblema = nodoPadre.hijos.get(posicionDelHijo);
        if (esNodoVacio(hijoConProblema) || nroDatosNoVacios(hijoConProblema) >= getNroMinimoDeDatos()) {
            return;
        }

        if (puedePrestarHermanoIzquierdo(nodoPadre, posicionDelHijo)) {
            prestarseDelHermanoIzquierdo(nodoPadre, posicionDelHijo);
            return;
        }
        if (puedePrestarHermanoDerecho(nodoPadre, posicionDelHijo)) {
            prestarseDelHermanoDerecho(nodoPadre, posicionDelHijo);
            return;
        }

        if (posicionDelHijo > 0) {
            fusionarConHermanoIzquierdo(nodoPadre, posicionDelHijo);
        } else {
            fusionarConHermanoDerecho(nodoPadre, posicionDelHijo);
        }
    }

    private boolean puedePrestarHermanoIzquierdo(NodoMVias<T> nodoPadre, int posicionDelHijo) {
        if (posicionDelHijo == 0) {
            return false;
        }
        NodoMVias<T> hermanoIzquierdo = nodoPadre.hijos.get(posicionDelHijo - 1);
        return !esNodoVacio(hermanoIzquierdo)
                && nroDatosNoVacios(hermanoIzquierdo) > getNroMinimoDeDatos();
    }

    private boolean puedePrestarHermanoDerecho(NodoMVias<T> nodoPadre, int posicionDelHijo) {
        int cantidadDeDatosPadre = nroDatosNoVacios(nodoPadre);
        if (posicionDelHijo >= cantidadDeDatosPadre) {
            return false;
        }
        NodoMVias<T> hermanoDerecho = nodoPadre.hijos.get(posicionDelHijo + 1);
        return !esNodoVacio(hermanoDerecho)
                && nroDatosNoVacios(hermanoDerecho) > getNroMinimoDeDatos();
    }

    private void prestarseDelHermanoIzquierdo(NodoMVias<T> nodoPadre, int posicionDelHijo) {
        NodoMVias<T> hijo = nodoPadre.hijos.get(posicionDelHijo);
        NodoMVias<T> hermanoIzquierdo = nodoPadre.hijos.get(posicionDelHijo - 1);

        List<T> datosDelHijo = obtenerDatosNoVacios(hijo);
        List<T> datosDelHermano = obtenerDatosNoVacios(hermanoIzquierdo);
        List<NodoMVias<T>> hijosDelHijo = obtenerHijosUsados(hijo);
        List<NodoMVias<T>> hijosDelHermano = obtenerHijosUsados(hermanoIzquierdo);

        T datoQueBajaDelPadre = nodoPadre.datos.get(posicionDelHijo - 1);
        T datoQueSubeAlPadre = datosDelHermano.remove(datosDelHermano.size() - 1);
        datosDelHijo.add(0, datoQueBajaDelPadre);
        nodoPadre.datos.set(posicionDelHijo - 1, datoQueSubeAlPadre);

        if (!hijosDelHermano.isEmpty()) {
            NodoMVias<T> hijoPrestado = hijosDelHermano.remove(hijosDelHermano.size() - 1);
            hijosDelHijo.add(0, hijoPrestado);
        }

        cargarDatosEHijos(hermanoIzquierdo, datosDelHermano, hijosDelHermano);
        cargarDatosEHijos(hijo, datosDelHijo, hijosDelHijo);
    }

    private void prestarseDelHermanoDerecho(NodoMVias<T> nodoPadre, int posicionDelHijo) {
        NodoMVias<T> hijo = nodoPadre.hijos.get(posicionDelHijo);
        NodoMVias<T> hermanoDerecho = nodoPadre.hijos.get(posicionDelHijo + 1);

        List<T> datosDelHijo = obtenerDatosNoVacios(hijo);
        List<T> datosDelHermano = obtenerDatosNoVacios(hermanoDerecho);
        List<NodoMVias<T>> hijosDelHijo = obtenerHijosUsados(hijo);
        List<NodoMVias<T>> hijosDelHermano = obtenerHijosUsados(hermanoDerecho);

        T datoQueBajaDelPadre = nodoPadre.datos.get(posicionDelHijo);
        T datoQueSubeAlPadre = datosDelHermano.remove(0);
        datosDelHijo.add(datoQueBajaDelPadre);
        nodoPadre.datos.set(posicionDelHijo, datoQueSubeAlPadre);

        if (!hijosDelHermano.isEmpty()) {
            NodoMVias<T> hijoPrestado = hijosDelHermano.remove(0);
            hijosDelHijo.add(hijoPrestado);
        }

        cargarDatosEHijos(hijo, datosDelHijo, hijosDelHijo);
        cargarDatosEHijos(hermanoDerecho, datosDelHermano, hijosDelHermano);
    }

    private void fusionarConHermanoIzquierdo(NodoMVias<T> nodoPadre, int posicionDelHijo) {
        NodoMVias<T> hermanoIzquierdo = nodoPadre.hijos.get(posicionDelHijo - 1);
        NodoMVias<T> hijo = nodoPadre.hijos.get(posicionDelHijo);
        List<T> datosDelPadre = obtenerDatosNoVacios(nodoPadre);
        List<NodoMVias<T>> hijosDelPadre = obtenerHijosUsados(nodoPadre);

        List<T> datosFusionados = obtenerDatosNoVacios(hermanoIzquierdo);
        datosFusionados.add(datosDelPadre.get(posicionDelHijo - 1));
        datosFusionados.addAll(obtenerDatosNoVacios(hijo));

        List<NodoMVias<T>> hijosFusionados = obtenerHijosUsados(hermanoIzquierdo);
        hijosFusionados.addAll(obtenerHijosUsados(hijo));

        cargarDatosEHijos(hermanoIzquierdo, datosFusionados, hijosFusionados);
        datosDelPadre.remove(posicionDelHijo - 1);
        hijosDelPadre.remove(posicionDelHijo);
        cargarDatosEHijos(nodoPadre, datosDelPadre, hijosDelPadre);
    }

    private void fusionarConHermanoDerecho(NodoMVias<T> nodoPadre, int posicionDelHijo) {
        NodoMVias<T> hijo = nodoPadre.hijos.get(posicionDelHijo);
        NodoMVias<T> hermanoDerecho = nodoPadre.hijos.get(posicionDelHijo + 1);
        List<T> datosDelPadre = obtenerDatosNoVacios(nodoPadre);
        List<NodoMVias<T>> hijosDelPadre = obtenerHijosUsados(nodoPadre);

        List<T> datosFusionados = obtenerDatosNoVacios(hijo);
        datosFusionados.add(datosDelPadre.get(posicionDelHijo));
        datosFusionados.addAll(obtenerDatosNoVacios(hermanoDerecho));

        List<NodoMVias<T>> hijosFusionados = obtenerHijosUsados(hijo);
        hijosFusionados.addAll(obtenerHijosUsados(hermanoDerecho));

        cargarDatosEHijos(hijo, datosFusionados, hijosFusionados);
        datosDelPadre.remove(posicionDelHijo);
        hijosDelPadre.remove(posicionDelHijo + 1);
        cargarDatosEHijos(nodoPadre, datosDelPadre, hijosDelPadre);
    }

    private NodoMVias<T> primerHijoNoVacio(NodoMVias<T> nodoActual) {
        for (NodoMVias<T> hijo : nodoActual.hijos) {
            if (!esNodoVacio(hijo)) {
                return hijo;
            }
        }
        return NODO_VACIO;
    }

    private void eliminarDatoDePosicion(NodoMVias<T> nodoActual, int posicionAEliminar) {
        int cantidadDeDatos = nroDatosNoVacios(nodoActual);
        for (int i = posicionAEliminar; i < cantidadDeDatos - 1; i++) {
            nodoActual.datos.set(i, nodoActual.datos.get(i + 1));
        }
        nodoActual.datos.set(cantidadDeDatos - 1, (T) DATO_VACIO);
    }

    private List<T> obtenerDatosNoVacios(NodoMVias<T> nodoActual) {
        List<T> datosNoVacios = new ArrayList<>();
        for (int i = 0; i < nroDatosNoVacios(nodoActual); i++) {
            datosNoVacios.add(nodoActual.datos.get(i));
        }
        return datosNoVacios;
    }

    private List<NodoMVias<T>> obtenerHijosUsados(NodoMVias<T> nodoActual) {
        List<NodoMVias<T>> hijosUsados = new ArrayList<>();
        if (esHoja(nodoActual)) {
            return hijosUsados;
        }
        int cantidadDeDatos = nroDatosNoVacios(nodoActual);
        for (int i = 0; i <= cantidadDeDatos; i++) {
            hijosUsados.add(nodoActual.hijos.get(i));
        }
        return hijosUsados;
    }

    private void cargarDatosEHijos(
            NodoMVias<T> nodoActual,
            List<T> datosActualizados,
            List<NodoMVias<T>> hijosActualizados) {
        for (int i = 0; i < nodoActual.datos.size(); i++) {
            nodoActual.datos.set(i, (T) DATO_VACIO);
        }
        for (int i = 0; i < nodoActual.hijos.size(); i++) {
            nodoActual.hijos.set(i, NODO_VACIO);
        }
        for (int i = 0; i < datosActualizados.size(); i++) {
            nodoActual.datos.set(i, datosActualizados.get(i));
        }
        for (int i = 0; i < hijosActualizados.size(); i++) {
            nodoActual.hijos.set(i, hijosActualizados.get(i));
        }
    }

    

    @Override
    public T buscar(T datoABuscar) {
        if (datoABuscar == null) {
            throw new IllegalArgumentException("No se aceptan datos nulos");
        }
        NodoMVias<T> nodoEnTurno = raiz;
        while (!esNodoVacio(nodoEnTurno)) {
            int posDelDatoNodo = obtenerPosicionDeldatoNodo(nodoEnTurno, datoABuscar);
            if (posDelDatoNodo != POSICION_INVALIDAD) {
                return nodoEnTurno.datos.get(posDelDatoNodo);
            }
            if (esHoja(nodoEnTurno)) {
                nodoEnTurno = NODO_VACIO;
            } else {
                int posPorDondeBajar = obtenerPosicionPorDondeBajar(nodoEnTurno, datoABuscar);
                nodoEnTurno = nodoEnTurno.hijos.get(posPorDondeBajar);
            }
        }// solo se da si no encuentra el dato en el arbol
        return null;
    }

    @Override
    public boolean contiene(T dato) {
        return buscar(dato) != null;
    }

    @Override
    public int size() {
        return size(raiz);
    }

    private int size(NodoMVias<T> nodoActual) {
        if (esNodoVacio(nodoActual)) {
            return 0;
        }
        int cantidaNodos = 0;
        for (int i = 0; i < nroDatosNoVacios(nodoActual); i++) {
            int cantidadNodosDelHijo = size(nodoActual.hijos.get(i));
            cantidaNodos += cantidadNodosDelHijo;
        }
        cantidaNodos += size(nodoActual.hijos.get(nroDatosNoVacios(nodoActual)));
        return cantidaNodos + 1;
    }

    @Override
    public int altura() {
        if (esArbolVacio()) {
            throw new IllegalArgumentException("es un arbol vacio no se peude calcular la altura");
        }
        return altura(raiz);

    }

    public int altura(NodoMVias<T> nodoActual) {
        if (esNodoVacio(nodoActual)) {
            return 0;
        }
        int altura = 0;
        for (int i = 0; i <= nroDatosNoVacios(nodoActual); i++) {
            int alturaDelHijo = altura(nodoActual.hijos.get(i));
            if (alturaDelHijo > altura) {
                altura = alturaDelHijo;
            }
        }
        return altura + 1;
    }

    @Override
    public void vaciar() {
        this.raiz = NODO_VACIO;
    }

    @Override
    public boolean esArbolVacio() {

        return raiz == NODO_VACIO;
    }

    @Override
    public int nivel() {

        return altura() - 1;
    }

    @Override
    public List<T> recorridoEnInOrden() {
        ArrayList<T> recorrido = new ArrayList<>();
        recorridoEnInOrden(this.raiz, recorrido);
        return recorrido;
    }

    private void recorridoEnInOrden(NodoMVias<T> nodoActual, List<T> recorrido) {
        if (esNodoVacio(nodoActual)) {
            return;
        }
        for (int i = 0; i < nroDatosNoVacios(nodoActual); i++) {
            T dato = nodoActual.datos.get(i);
            recorridoEnInOrden(nodoActual.hijos.get(i), recorrido);
            if (!esDatoVacio(dato)) {
                recorrido.add(dato);
            }
        }
        recorridoEnInOrden(nodoActual.hijos.get(nroDatosNoVacios(nodoActual)), recorrido);
    }

    @Override
    public List<T> recorridoEnPreOrden() {
        ArrayList<T> recorrido = new ArrayList<>();
        recorridoEnPreOrden(this.raiz, recorrido);
        return recorrido;
    }

    private void recorridoEnPreOrden(NodoMVias<T> nodoActual, List<T> recorrido) {
        if (esNodoVacio(nodoActual)) {
            return;
        }
        for (int i = 0; i < nroDatosNoVacios(nodoActual); i++) {
            T dato = nodoActual.datos.get(i);
            if (!esDatoVacio(dato)) {
                recorrido.add(dato);
            }
            recorridoEnPreOrden(nodoActual.hijos.get(i), recorrido);
        }
        recorridoEnPreOrden(nodoActual.hijos.get(nroDatosNoVacios(nodoActual)), recorrido);
    }

    @Override
    public List<T> recorridoEnPostOrden() {
        ArrayList<T> recorrido = new ArrayList<>();
        recorridoEnPostOrden(this.raiz, recorrido);
        return recorrido;
    }

    private void recorridoEnPostOrden(NodoMVias<T> nodoActual, List<T> recorrido) {
        if (esNodoVacio(nodoActual)) {
            return;
        }
        recorridoEnPostOrden(nodoActual.hijos.get(0), recorrido);
        for (int i = 0; i < nroDatosNoVacios(nodoActual); i++) {
            recorridoEnPostOrden(nodoActual.hijos.get(i + 1), recorrido);
            T dato = nodoActual.datos.get(i);
            recorrido.add(dato);
        }
    }

    @Override
    public List<T> recorridoPorNiveles() {
        List<T> recorrido = new ArrayList<>();
        if (!esArbolVacio()) {
            Queue<NodoMVias<T>> colaDeNodos = new LinkedList<>();
            colaDeNodos.offer(raiz);

            do {
                NodoMVias<T> nodoEnTurno = colaDeNodos.poll();
                int nroDatosNoVacios = nroDatosNoVacios(nodoEnTurno);
                for (int i = 0; i < nroDatosNoVacios; i++) {
                    recorrido.add(nodoEnTurno.datos.get(i));
                    if (!esNodoVacio(nodoEnTurno.hijos.get(i))) {
                        colaDeNodos.offer(nodoEnTurno.hijos.get(i));
                    }
                }
                if (!esNodoVacio(nodoEnTurno.hijos.get(nroDatosNoVacios))) {
                    colaDeNodos.offer(nodoEnTurno.hijos.get(nroDatosNoVacios));
                }
            } while (!colaDeNodos.isEmpty());
        }
        return recorrido;
    }

    /*Para un árbol M vias implementar un método que retorne
    el nro de hojas que tiene el árbol*/
    public int cantidadDeHojas() {
        if (esArbolVacio()) {
            return 0;
        }
        return cantidadDeHojas(raiz);
    }

    private int cantidadDeHojas(NodoMVias<T> nodoActual) {
        if (esNodoVacio(nodoActual)) {
            return 0;
        }
        if (esHoja(nodoActual)) {
            return 1;
        }
        int nroHojas = 0;
        for (int i = 0; i <= nroDatosNoVacios(nodoActual); i++) {
            nroHojas += cantidadDeHojas(nodoActual.hijos.get(i));
        }
        return nroHojas;
    }

    // de forma iterativa
    public int cantidadDehojas() {
        if (esArbolVacio()) {
            return 0;
        }
        Queue<NodoMVias<T>> colaDeNodoMvias = new LinkedList<>();
        colaDeNodoMvias.offer(raiz);
        int nroHojas = 0;
        do {
            NodoMVias<T> nodoEnTurno = colaDeNodoMvias.poll();
            if (esHoja(nodoEnTurno)) {
                nroHojas += 1;
            } else {
                for (int i = 0; i <= nroDatosNoVacios(nodoEnTurno); i++) {
                    if (!esNodoVacio(nodoEnTurno.hijos.get(i))) {
                        colaDeNodoMvias.offer(nodoEnTurno.hijos.get(i));
                    }
                }
            }
        } while (!colaDeNodoMvias.isEmpty());
        return nroHojas;
    }

    /*Para un árbol M vias implementar un método que retorne
    la cantidad de datos vacios que tiene el árbol
     */
    public int nroDatosVacios() {
        if (esArbolVacio()) {
            return 0;
        }
        Queue<NodoMVias<T>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(raiz);
        int candidaDeDatosVacios = 0;
        do {
            NodoMVias<T> nodoEnTurno = colaDeNodos.poll();
            for (int i = 0; i <= nroDatosNoVacios(nodoEnTurno); i++) {
                if (!esNodoVacio(nodoEnTurno.hijos.get(i))) {
                    colaDeNodos.offer(nodoEnTurno.hijos.get(i));
                }
            }
            candidaDeDatosVacios += getNroMaximoDeDatos() - nroDatosNoVacios(nodoEnTurno);
        } while (!colaDeNodos.isEmpty());
        return candidaDeDatosVacios;
    }

    /*Para un árbol B implementar un método que retorne
    el predecesor inorden de un dato, para esto este método
    recibirá como parametros un nodo no hoja y el indice del
    dato en este nodo del que se debe reemplazar
     */
    public T predecesorInorden(NodoMVias<T> nodoActual, int poscion) {
        nodoActual = nodoActual.hijos.get(poscion);
        while (!esHoja(nodoActual)) {
            int pos = nroDatosNoVacios(nodoActual);
            nodoActual = nodoActual.hijos.get(pos);
        }
        return nodoActual.datos.get(nroDatosNoVacios(nodoActual) - 1);
    }

    // predecesor postOrden
    public T postOrden(NodoMVias<T> nodoActual, int pos) {
        if (pos == POSICION_INVALIDAD) {
            throw new IllegalArgumentException("es una posicion invalidad");
        }
        nodoActual = nodoActual.hijos.get(pos + 1);
        return nodoActual.datos.get(nroDatosNoVacios(nodoActual) - 1);
    }

    // sucesor Inorden
    public T sucesorInorden(ArbolMViasBase.NodoMVias<T> nodoActual, int pos) {
        nodoActual = nodoActual.hijos.get(pos + 1);
        while (!esHoja(nodoActual)) {
            nodoActual = nodoActual.hijos.get(0);
        }
        return nodoActual.datos.get(0);
    }

     /*Para un arbol B implementar un método que retorne cuantos nodos
     estan con los datos llenos a partir del nivel n
      */
    public int contarLosdatosLlenosDeUnNivel(int nivel){
        if(esArbolVacio()){
            return 0;
        }
        return contarLosdatosLlenosDeUnNivel(raiz,nivel,0);
    }
    private int contarLosdatosLlenosDeUnNivel(NodoMVias<T> nodoActual,int nivel,int nivelActual){
        int contarLosNodosLLenos=0;
        if(esNodoVacio(nodoActual)){
            return 0;
        }
        if(nivelActual>=nivel && estanLosDatosLlenos(nodoActual)){
            contarLosNodosLLenos=1;
        }
        nivelActual+=1;
        for(int i=0;i<=nroDatosNoVacios(nodoActual);i++){
            contarLosNodosLLenos+=contarLosdatosLlenosDeUnNivel(nodoActual.hijos.get(i),nivel,nivelActual);
        }
        return contarLosNodosLLenos;
    }
    // de forma iterativa
    public int contarlosnodosLLenosIterativo(int nivel){
        if(esArbolVacio()){
            return 0;
        }
        int contarNodos=0;
        Queue<NodoMVias<T>> colaDeNodos=new LinkedList<>();
        colaDeNodos.offer(raiz);
        Queue<Integer> colaNiveles=new LinkedList<>();
        int nivelActual=0;
        colaNiveles.offer(nivelActual);
        do{
            NodoMVias<T> nodoEnTurno=colaDeNodos.poll();
            nivelActual=colaNiveles.poll();
            if(nivelActual >= nivel && estanLosDatosLlenos(nodoEnTurno)){
               contarNodos+=1;
            }
            for(int i = 0; i<= nroDatosNoVacios(nodoEnTurno);i++){
                if(!esNodoVacio(nodoEnTurno.hijos.get(i))){
                    colaNiveles.offer(nivelActual+1);
                    colaDeNodos.offer(nodoEnTurno.hijos.get(i));
                }
            }

        }while(!colaDeNodos.isEmpty());
        return contarNodos;
    }

    @Override
    public String toString() {
        if (esArbolVacio()) {
            return "Arbol vacio";
        }

        List<List<NodoMVias<T>>> niveles = new ArrayList<>();
        Queue<NodoMVias<T>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(raiz);

        while (!colaDeNodos.isEmpty()) {
            int cantidadDeNodosDelNivel = colaDeNodos.size();
            List<NodoMVias<T>> nivelActual = new ArrayList<>();

            for (int i = 0; i < cantidadDeNodosDelNivel; i++) {
                NodoMVias<T> nodoActual = colaDeNodos.poll();
                nivelActual.add(nodoActual);

                for (int j = 0; j <= nroDatosNoVacios(nodoActual); j++) {
                    NodoMVias<T> hijo = nodoActual.hijos.get(j);
                    if (!esNodoVacio(hijo)) {
                        colaDeNodos.offer(hijo);
                    }
                }
            }
            niveles.add(nivelActual);
        }

        StringBuilder arbolComoCadena = new StringBuilder();
        int alturaDelArbol = niveles.size();
        for (int nivel = 0; nivel < niveles.size(); nivel++) {
            List<NodoMVias<T>> nodosDelNivel = niveles.get(nivel);
            int espaciosAntes = (int) Math.pow(2, alturaDelArbol - nivel + 1);
            int espaciosEntre = (int) Math.pow(2, alturaDelArbol - nivel + 2);

            arbolComoCadena.append(repetirEspacios(espaciosAntes));
            for (NodoMVias<T> nodoActual : nodosDelNivel) {
                arbolComoCadena.append(nodoComoCadena(nodoActual));
                arbolComoCadena.append(repetirEspacios(espaciosEntre));
            }
            arbolComoCadena.append("\n");

            if (nivel < niveles.size() - 1) {
                arbolComoCadena.append(repetirEspacios(espaciosAntes));
                for (NodoMVias<T> nodoActual : nodosDelNivel) {
                    arbolComoCadena.append(conectoresDelNodo(nodoActual));
                    arbolComoCadena.append(repetirEspacios(espaciosEntre));
                }
                arbolComoCadena.append("\n");
            }
        }

        return arbolComoCadena.toString();
    }

    private String nodoComoCadena(NodoMVias<T> nodoActual) {
        StringBuilder nodoComoCadena = new StringBuilder("[");
        for (int i = 0; i < nroDatosNoVacios(nodoActual); i++) {
            if (i > 0) {
                nodoComoCadena.append(" | ");
            }
            nodoComoCadena.append(nodoActual.datos.get(i));
        }
        nodoComoCadena.append("]");
        return nodoComoCadena.toString();
    }

    private String conectoresDelNodo(NodoMVias<T> nodoActual) {
        int cantidadDeHijos = 0;
        for (int i = 0; i <= nroDatosNoVacios(nodoActual); i++) {
            if (!esNodoVacio(nodoActual.hijos.get(i))) {
                cantidadDeHijos++;
            }
        }

        if (cantidadDeHijos == 0) {
            return "";
        }
        if (cantidadDeHijos == 1) {
            return "|";
        }
        if (cantidadDeHijos == 2) {
            return "/ \\";
        }

        StringBuilder conectores = new StringBuilder("/");
        for (int i = 1; i < cantidadDeHijos - 1; i++) {
            conectores.append(" |");
        }
        conectores.append(" \\");
        return conectores.toString();
    }

    private String repetirEspacios(int cantidad) {
        StringBuilder espacios = new StringBuilder();
        for (int i = 0; i < cantidad; i++) {
            espacios.append(" ");
        }
        return espacios.toString();
    }

     public static void main(String[] args) {
         ArbolB<Integer> arbolB1=new ArbolB<>(4);
         arbolB1.insertar(50);
         arbolB1.insertar(70);
         arbolB1.insertar(500);
         arbolB1.insertar(100);
         arbolB1.insertar(90);
         arbolB1.insertar(400);
         arbolB1.insertar(91);
         arbolB1.insertar(75);
         arbolB1.insertar(300);
         arbolB1.insertar(800);
         arbolB1.insertar(99);
         arbolB1.insertar(700);
         arbolB1.insertar(98);

         System.out.println(arbolB1.recorridoPorNiveles());
         System.out.println("Por niveles: " + arbolB1.recorridoPorNiveles());
         System.out.println("InOrden: " + arbolB1.recorridoEnInOrden());
         System.out.println("Size: " + arbolB1.size());

         NodoMVias<Integer> nodo=arbolB1.raiz;
         int pos=0;
         System.out.println("predesesorInorden"+ arbolB1.predecesorInorden(nodo,pos));
         System.out.println("alrura" + arbolB1.altura());
         System.out.println(arbolB1.toString());
         System.out.println( arbolB1.contarlosnodosLLenosIterativo(0));

     }
}
