package ARBOLES;

import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class ArbolBinarioBase<T> implements IArbolBase<T> {

    protected static class NodoBinario<T> {
        T dato;
        NodoBinario<T> hijoIzquierdo;
        NodoBinario<T> hijoDerecho;

        public NodoBinario(T unDato) {
            dato = unDato;
        }
    }

    protected NodoBinario<T> raiz;
    protected static NodoBinario NODO_VACIO = null;

    protected NodoBinario<T> nodoVacio() {
        return null;
    }
    public List<T> recorridoInOrden(){
        List<T> recorrido=new ArrayList<>();
        if(!esArbolVacio()){
            Stack<NodoBinario<T>> pilaDeNodos=new Stack<>();
            NodoBinario<T> nodoEnTurno=raiz;
            apiladoInOrden(pilaDeNodos,nodoEnTurno);
            do{
                nodoEnTurno=pilaDeNodos.pop();
                recorrido.add(nodoEnTurno.dato);
                if(!esNodoVacio(nodoEnTurno.hijoDerecho)){
                    apiladoInOrden(pilaDeNodos,nodoEnTurno.hijoDerecho);
                }
            }while (!pilaDeNodos.isEmpty());
        }
        return recorrido;
    }
    public void apiladoInOrden(Stack<NodoBinario<T>> pila,NodoBinario<T> nodoActual){
        while(!esNodoVacio(nodoActual)){
            pila.push(nodoActual);
            nodoActual=nodoActual.hijoIzquierdo;
        }
    }
    protected boolean esNodoVacio(NodoBinario<T> elNodo) {
        return elNodo == NODO_VACIO;
    }

    @Override
    public boolean esArbolVacio() {
        return this.esNodoVacio(this.raiz);
    }

    @Override
    public void vaciar() {
        this.raiz = NODO_VACIO;
    }

    @Override
    public List<T> recorridoPorNiveles() {
        List<T> recorrido = new ArrayList<>();
        if (!esArbolVacio()) {
            Queue<NodoBinario<T>> colaDeNodos = new LinkedList<>();
            colaDeNodos.offer(raiz);

            do {
                NodoBinario<T> nodoEnTurno = colaDeNodos.poll();
                recorrido.add(nodoEnTurno.dato);

                if (!esNodoVacio(nodoEnTurno.hijoIzquierdo)) {
                    colaDeNodos.offer(nodoEnTurno.hijoIzquierdo);
                }
                if (!esNodoVacio(nodoEnTurno.hijoDerecho)) {
                    colaDeNodos.offer(nodoEnTurno.hijoDerecho);
                }
            } while (!colaDeNodos.isEmpty());
        }
        return recorrido;
    }

    public void insertarPilaDeNodosPostOrden(NodoBinario<T> nodoEnTurno, Stack<NodoBinario<T>> pilaDeNodos) {
        while (!esNodoVacio(nodoEnTurno)) {
            pilaDeNodos.push(nodoEnTurno);
            if (!esNodoVacio(nodoEnTurno.hijoIzquierdo)) {
                nodoEnTurno = nodoEnTurno.hijoIzquierdo;
            } else {
                nodoEnTurno = nodoEnTurno.hijoDerecho;
            }
        }
    }

    @Override
    public List<T> recorridoEnPostOrden() {
        List<T> recorrido = new ArrayList<>();
        if (!esArbolVacio()) {
            Stack<NodoBinario<T>> pilaDeNodos = new Stack<>();
            NodoBinario<T> nodoEnTurno = raiz;
            insertarPilaDeNodosPostOrden(nodoEnTurno, pilaDeNodos);

            do {
                nodoEnTurno = pilaDeNodos.pop();
                recorrido.add(nodoEnTurno.dato);

                if (!pilaDeNodos.isEmpty()) {
                    NodoBinario<T> nodoTope = pilaDeNodos.peek();
                    if (!esNodoVacio(nodoTope.hijoDerecho)) {
                        if (nodoEnTurno != nodoTope.hijoDerecho) {
                            insertarPilaDeNodosPostOrden(nodoTope.hijoDerecho, pilaDeNodos);
                        }
                    }
                }
            } while (!pilaDeNodos.isEmpty());
        }
        return recorrido;
    }

    @Override
    public void insertar(T dato) {
        if (dato == null) {
            throw new IllegalArgumentException("El árbol no acepta nulos");
        }

        if (esArbolVacio()) {
            this.raiz = new NodoBinario<>(dato);
            return;
        }

        Queue<NodoBinario<T>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(raiz);

        do {
            NodoBinario<T> nodoEnTurno = colaDeNodos.poll();

            if (esNodoVacio(nodoEnTurno.hijoIzquierdo)) {
                nodoEnTurno.hijoIzquierdo = new NodoBinario<>(dato);
                return;
            } else {
                colaDeNodos.offer(nodoEnTurno.hijoIzquierdo);
            }

            if (esNodoVacio(nodoEnTurno.hijoDerecho)) {
                nodoEnTurno.hijoDerecho = new NodoBinario<>(dato);
                return;
            } else {
                colaDeNodos.offer(nodoEnTurno.hijoDerecho);
            }

        } while (!colaDeNodos.isEmpty());
    }

    @Override
    public void eliminar(T datoAEliminar) {
        if (datoAEliminar == null) {
            throw new IllegalArgumentException("El árbol no acepta nulos");
        }
        if (esArbolVacio()) {
            throw new IllegalArgumentException("No se puede eliminar en un árbol vacío");
        }

        if (datoAEliminar.equals(raiz.dato)) {
            raiz = eliminarNodo(raiz);
            return;
        }

        Queue<NodoBinario<T>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(raiz);

        do {
            NodoBinario<T> nodoEnTurno = colaDeNodos.poll();

            if (!esNodoVacio(nodoEnTurno.hijoIzquierdo)) {
                if (datoAEliminar.equals(nodoEnTurno.hijoIzquierdo.dato)) {
                    nodoEnTurno.hijoIzquierdo = eliminarNodo(nodoEnTurno.hijoIzquierdo);
                    return;
                }
                colaDeNodos.offer(nodoEnTurno.hijoIzquierdo);
            }

            if (!esNodoVacio(nodoEnTurno.hijoDerecho)) {
                if (datoAEliminar.equals(nodoEnTurno.hijoDerecho.dato)) {
                    nodoEnTurno.hijoDerecho = eliminarNodo(nodoEnTurno.hijoDerecho);
                    return;
                }
                colaDeNodos.offer(nodoEnTurno.hijoDerecho);
            }

        } while (!colaDeNodos.isEmpty());
    }

    private NodoBinario<T> eliminarNodo(NodoBinario<T> nodoAEliminar) {
        if (esHoja(nodoAEliminar)) {
            return NODO_VACIO;
        }
        if (!esNodoVacio(nodoAEliminar.hijoDerecho) && esNodoVacio(nodoAEliminar.hijoIzquierdo)) {
            return nodoAEliminar.hijoDerecho;
        }
        if (esNodoVacio(nodoAEliminar.hijoDerecho) && !esNodoVacio(nodoAEliminar.hijoIzquierdo)) {
            return nodoAEliminar.hijoIzquierdo;
        }

        NodoBinario<T> nodoReemplazo = nodoAEliminar.hijoIzquierdo;
        NodoBinario<T> nodoAConectarDerecha = recorridoMasALaDerecha(nodoReemplazo);
        nodoAConectarDerecha.hijoDerecho = nodoAEliminar.hijoDerecho;
        return nodoReemplazo;
    }

    public NodoBinario<T> recorridoMasALaDerecha(NodoBinario<T> nodoDerecha) {
        while (!esNodoVacio(nodoDerecha.hijoDerecho)) {
            nodoDerecha = nodoDerecha.hijoDerecho;
        }
        return nodoDerecha;
    }

    boolean esHoja(NodoBinario<T> nodoEnTurno) {
        return esNodoVacio(nodoEnTurno.hijoDerecho) && esNodoVacio(nodoEnTurno.hijoIzquierdo);
    }

    @Override
    public T buscar(T dato) {
        if (dato == null) {
            throw new IllegalArgumentException("No se permiten datos nulos");
        }
        return buscarRecorridoPreOrden(raiz, dato);
    }
    public int contarNodosDeUnNivel1(int nivelActual){
        int contadorDeNodos=0;
        int nivelDeNodos=0;
        if(esArbolVacio()){
           return contadorDeNodos;
       }
        if(nivelActual==nivelDeNodos){
            return 1;
        }
        nivelDeNodos++;
        Queue<NodoBinario<T>> colaDeNodos=new LinkedList<>();
        colaDeNodos.offer(raiz);
        int controladorDeVueltas=1;
        do{
            int controladorDeNodos=1;
            int controladorsiguiente=0;
            while (controladorDeNodos<=controladorDeVueltas){
                NodoBinario<T> nodoEnTurno=colaDeNodos.poll();
                if(!esNodoVacio(nodoEnTurno.hijoIzquierdo)){
                    colaDeNodos.offer(nodoEnTurno.hijoIzquierdo);
                    controladorsiguiente++;
                    if(nivelActual==nivelDeNodos){
                        contadorDeNodos++;
                    }
                }
                if(!esNodoVacio(nodoEnTurno.hijoDerecho)){
                    colaDeNodos.offer(nodoEnTurno.hijoDerecho);
                    controladorsiguiente++;
                    if(nivelActual==nivelDeNodos){
                        contadorDeNodos++;
                    }
                }
                controladorDeNodos++;
            }
            controladorDeVueltas=controladorsiguiente;
            nivelDeNodos++;
        }while(!colaDeNodos.isEmpty());
        return contadorDeNodos;
    }
    private T buscarRecorridoPreOrden(NodoBinario<T> nodoActual, T dato) {
        if (esNodoVacio(nodoActual)) {
            return null;
        }
        if (dato.equals(nodoActual.dato)) {
            return nodoActual.dato;
        }

        T datoEncontrado = buscarRecorridoPreOrden(nodoActual.hijoIzquierdo, dato);
        if (datoEncontrado != null) {
            return datoEncontrado;
        }
        return buscarRecorridoPreOrden(nodoActual.hijoDerecho, dato);
    }

    @Override
    public boolean contiene(T dato) {
        return buscar(dato) != null;
    }

    @Override
    public int size() {
        if (esArbolVacio()) {
            return 0;
        }
        return size(raiz);
    }

    private int size(NodoBinario<T> nodoActual) {
        if (esNodoVacio(nodoActual)) {
            return 0;
        }
        return size(nodoActual.hijoIzquierdo) + size(nodoActual.hijoDerecho) + 1;
    }

    @Override
    public int altura() {
        return altura(raiz);
    }

    private int altura(NodoBinario<T> nodoActual) {
        if (esNodoVacio(nodoActual)) {
            return 0;
        }
        int ladoDerecho = altura(nodoActual.hijoDerecho);
        int ladoIzquierdo = altura(nodoActual.hijoIzquierdo);
        return (ladoDerecho > ladoIzquierdo) ? ladoDerecho + 1 : ladoIzquierdo + 1;
    }

    @Override
    public int nivel() {
        return altura() - 1;
    }
    public int cantidadDeNodosEnNivel(int nivel) {
        if (esArbolVacio()) {
            return 0;
        }
        return cantidadDeNodosEnNivel(this.raiz, 0, nivel);
    }
    private int cantidadDeNodosEnNivel(NodoBinario<T> nodoActual,
                                       int nivelActual,int nivelParametro){
        if(esNodoVacio(nodoActual)){
            return 0;
        }
        if(nivelActual==nivelParametro){
            return 1;
        }
        int conteoIzquierda=cantidadDeNodosEnNivel(nodoActual.hijoIzquierdo,
                nivelActual+1,nivelParametro);
        int conteoDerecha=cantidadDeNodosEnNivel(nodoActual.hijoDerecho,
                nivelActual+1,nivelParametro);
    return conteoDerecha+conteoIzquierda;
    }
    @Override
    public List<T> recorridoEnInOrden() {
        List<T> recorrido = new ArrayList<>();
        recorridoEnInOrden(raiz, recorrido);
        return recorrido;
    }

    private void recorridoEnInOrden(NodoBinario<T> nodoActual, List<T> recorrido) {
        if (esNodoVacio(nodoActual)) {
            return;
        }
        recorridoEnInOrden(nodoActual.hijoIzquierdo, recorrido);
        recorrido.add(nodoActual.dato);
        recorridoEnInOrden(nodoActual.hijoDerecho, recorrido);
    }

    @Override
    public List<T> recorridoEnPreOrden() {
        List<T> recorrido = new ArrayList<>();

        if (!esArbolVacio()) {
            Stack<NodoBinario<T>> pilaDeNodos = new Stack<>();
            pilaDeNodos.push(raiz);

            do {
                NodoBinario<T> nodoEnTurno = pilaDeNodos.pop();
                recorrido.add(nodoEnTurno.dato);

                if (!esNodoVacio(nodoEnTurno.hijoDerecho)) {
                    pilaDeNodos.push(nodoEnTurno.hijoDerecho);
                }
                if (!esNodoVacio(nodoEnTurno.hijoIzquierdo)) {
                    pilaDeNodos.push(nodoEnTurno.hijoIzquierdo);
                }

            } while (!pilaDeNodos.isEmpty());
        }

        return recorrido;
    }

    private void construirRepresentacionVisual(NodoBinario<T> nodoActual,
                                               StringBuilder acumuladorTexto,
                                               String indentacionActual,
                                               boolean esElUltimoHijo,
                                               String tipoHijo) {
        if (esNodoVacio(nodoActual)) {
            return;
        }

        String simboloConector = esElUltimoHijo ? "`-- " : "|-- ";

        acumuladorTexto
                .append(indentacionActual)
                .append(simboloConector)
                .append(tipoHijo)
                .append(": [")
                .append(nodoActual.dato)
                .append("]")
                .append("\n");

        String indentacionHijos = indentacionActual + (esElUltimoHijo ? "    " : "|   ");

        boolean tieneHijoIzquierdoYDerecho = !esNodoVacio(nodoActual.hijoIzquierdo)
                && !esNodoVacio(nodoActual.hijoDerecho);

        if (!esNodoVacio(nodoActual.hijoIzquierdo)) {
            construirRepresentacionVisual(
                    nodoActual.hijoIzquierdo, acumuladorTexto,
                    indentacionHijos, !tieneHijoIzquierdoYDerecho, "I");
        }
        if (!esNodoVacio(nodoActual.hijoDerecho)) {
            construirRepresentacionVisual(
                    nodoActual.hijoDerecho, acumuladorTexto,
                    indentacionHijos, true, "D");
        }
    }

    @Override
    public String toString() {
        StringBuilder acumuladorTexto = new StringBuilder();

        if (esArbolVacio()) {
            acumuladorTexto.append("(arbol vacio)");
        } else {
            acumuladorTexto.append("Raiz: [").append(raiz.dato).append("]\n");

            boolean raizTieneHijoIzquierdoYDerecho = !esNodoVacio(raiz.hijoIzquierdo)
                    && !esNodoVacio(raiz.hijoDerecho);

            if (!esNodoVacio(raiz.hijoIzquierdo)) {
                construirRepresentacionVisual(
                        raiz.hijoIzquierdo, acumuladorTexto,
                        "", !raizTieneHijoIzquierdoYDerecho, "I");
            }
            if (!esNodoVacio(raiz.hijoDerecho)) {
                construirRepresentacionVisual(
                        raiz.hijoDerecho, acumuladorTexto,
                        "", true, "D");
            }
        }
        return acumuladorTexto.toString();
    }

    public static void main(String[] args) {
        ArbolBinarioBase<Integer> arbolDeNodos = new ArbolBinarioBase<>();
        arbolDeNodos.insertar(3);
        arbolDeNodos.insertar(34);
        arbolDeNodos.insertar(2);
        arbolDeNodos.insertar(6);
        arbolDeNodos.insertar(7);
        arbolDeNodos.insertar(9);
        arbolDeNodos.insertar(10);
        System.out.println(arbolDeNodos.recorridoInOrden());

        System.out.println(arbolDeNodos.toString());
        System.out.println("Cantidad de nodos en nivel : " + arbolDeNodos.contarNodosDeUnNivel1(2));
    }
}


