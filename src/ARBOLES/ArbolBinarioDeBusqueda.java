package ARBOLES;

import java.util.*;

public class ArbolBinarioDeBusqueda <T extends Comparable<T>> extends
ArbolBinarioBase {
    public ArbolBinarioDeBusqueda() {
        super();
    }

    public void insertar(T dato) {
        if (dato == null) {
            throw new IllegalArgumentException("Nose aceptan datos nulos");
        }
        if (esArbolVacio()) {
            NodoBinario<T> nodoActual = new NodoBinario<>(dato);
            super.raiz = nodoActual;
            return;
        }
        NodoBinario<T> nodoAnterior = ArbolBinarioBase.NODO_VACIO;
        NodoBinario<T> nodoActual = super.raiz;
        do {
            T datoAComparar = nodoActual.dato;
            nodoAnterior = nodoActual;
            if (datoAComparar.compareTo(dato) > 0) {
                nodoActual = nodoActual.hijoIzquierdo;
            } else {
                if (datoAComparar.compareTo(dato) < 0) {
                    nodoActual = nodoActual.hijoDerecho;
                } else {
                    throw new IllegalArgumentException("no sé insertan datos repetidos");
                }
            }

        } while (!esNodoVacio(nodoActual));
        NodoBinario<T> nodoInsert = new NodoBinario<>(dato);
        if (dato.compareTo(nodoAnterior.dato) < 0) {
            nodoAnterior.hijoIzquierdo = nodoInsert;
        } else {
            nodoAnterior.hijoDerecho = nodoInsert;
        }
    }
    public void insertarRecursivo(T datoAInsertar){
        if(datoAInsertar==null)
        {
            throw new IllegalArgumentException("no se insertar datos nulos");
        }
        this.raiz=insertarRecursivo(this.raiz,datoAInsertar);
    }
    private NodoBinario<T> insertarRecursivo(NodoBinario<T> nodoActual,T datoAInsertar){
        if(esNodoVacio(nodoActual)){
            NodoBinario<T> nodoNuevo=new NodoBinario<>(datoAInsertar);
            return nodoNuevo;
        }
        if(datoAInsertar.compareTo(nodoActual.dato)>0){
            NodoBinario<T> nodoNuevo=insertarRecursivo(nodoActual.hijoDerecho,datoAInsertar);
            nodoActual.hijoDerecho=nodoNuevo;
            return nodoActual;
        }
        if(datoAInsertar.compareTo(nodoActual.dato)<0){
            NodoBinario<T> nodoNuevo=insertarRecursivo(nodoActual.hijoIzquierdo,datoAInsertar);
            nodoActual.hijoIzquierdo=nodoNuevo;
            return nodoActual;
        }
        throw new IllegalArgumentException("nosé insertar datos repetidos");
    }
    public T busacar(T datoABuscar) {
        if (datoABuscar == null) {
            throw new IllegalArgumentException("No se aceptan datos nulos");
        }
        if (esArbolVacio()) {
            return null;
        }
        NodoBinario<T> nodoAnterior = null;
        NodoBinario<T> nodoActual = null;
        do {
            T datoAComparar = nodoActual.dato;
            nodoAnterior = nodoActual;
            if (datoAComparar.compareTo(datoABuscar) < 0) {
                nodoActual = nodoActual.hijoIzquierdo;
            } else {
                if (datoAComparar.compareTo(datoABuscar) > 0) {
                    nodoActual = nodoActual.hijoDerecho;
                } else {
                    return nodoActual.dato;
                }
            }

        } while (!esNodoVacio(nodoActual));
        return null;
    }

    public void eliminar(T dato) {
       if(esArbolVacio()) {
           throw new IllegalArgumentException("no se puede eliminar nada de un  arbol vacio");
       }
       this.raiz=eliminar(this.raiz,dato);
    }

    private NodoBinario<T> eliminar(NodoBinario<T> nodoActual, T datoEliminar) {
        if (datoEliminar == null) {
            throw new IllegalArgumentException("No se aceptan dqatos nulos");
        }
        if (datoEliminar.compareTo(nodoActual.dato) > 0) {
            NodoBinario<T> nodoNuevo = eliminar(nodoActual.hijoDerecho, datoEliminar);
            nodoActual.hijoDerecho = nodoNuevo;
            return nodoActual;
        }
        if (datoEliminar.compareTo(nodoActual.dato) <  0) {
            NodoBinario<T> nodoNuevo = eliminar(nodoActual.hijoIzquierdo,
                    datoEliminar);
            nodoActual.hijoIzquierdo = nodoNuevo;
            return nodoActual;
        }
        //caso 1
        if (esHoja(nodoActual)) {
            return ArbolBinarioBase.NODO_VACIO;
        }
        // caso 2
        if (super.esNodoVacio(nodoActual.hijoDerecho) && !super.esNodoVacio(nodoActual.hijoIzquierdo)) {
            return nodoActual.hijoIzquierdo;
        }
        if (!super.esNodoVacio(nodoActual.hijoDerecho) && super.esNodoVacio(nodoActual.hijoIzquierdo)) {
            return nodoActual.hijoDerecho;
        }
        //caso 3
        T datoRempalzo = BuscarDatoRemplazo(nodoActual.hijoDerecho);
        NodoBinario<T> nodoNuevo = eliminar(nodoActual.hijoDerecho, datoRempalzo);
        nodoActual.hijoDerecho = nodoNuevo;
        nodoActual.dato = datoRempalzo;

        return nodoActual;
    }

    public T BuscarDatoRemplazo(NodoBinario<T> nodoActual) {
        while (!esNodoVacio(nodoActual.hijoIzquierdo)) {
            nodoActual = nodoActual.hijoIzquierdo;
        }
        return nodoActual.dato;
    }
    public void reconstrucionconPostYInOrden(List<T> listaIn, List<T> listaPost){
        if(listaIn.size()!=listaPost.size()){
             throw new IllegalArgumentException("no se pude recontruir el arbol si no son iguales ");
        }
        raiz=reconstrucionconPostYInOrde(listaIn,listaPost);

    }
    private NodoBinario<T> reconstrucionconPostYInOrde(List<T> listaIn, List<T> listaPost){
        if(listaIn.isEmpty()){
           return NODO_VACIO;
        }
        T postPadre=listaPost.get(listaPost.size()-1);
        int obtenerPosicionPadre=obtenerPosicionDatoEnInOrden(postPadre,listaIn);
        List<T> postIzq=listaPost.subList(0,obtenerPosicionPadre);
        List<T> inIzq=listaIn.subList(0,obtenerPosicionPadre);
        List<T> posDere=listaPost.subList(obtenerPosicionPadre,listaPost.size()-1);
        List<T> inDere=listaIn.subList(obtenerPosicionPadre+1,listaIn.size());
        NodoBinario<T> nodoPadre=new NodoBinario<>(postPadre);
        nodoPadre.hijoDerecho=reconstrucionconPostYInOrde(inDere,posDere);
        nodoPadre.hijoIzquierdo=reconstrucionconPostYInOrde(inIzq,postIzq);
        return nodoPadre;
    }


    private int obtenerPosicionDatoEnInOrden(T valorABuscar, List<T> valoresInOrden){
        for (int i = 0; i < valoresInOrden.size(); i++) {
            T valorActual = valoresInOrden.get(i);
            if (valorActual.compareTo(valorABuscar) == 0) {
                return i;
            }
        }
        return -1;
    }
    public List<T> recorridoIn1(){
        List<T> recorrido=new ArrayList<>();
        recorridoIn1(recorrido,raiz);
        return recorrido;
    }
    private void recorridoIn1(List<T> recorrido,NodoBinario<T> nodoActual){
        if(esNodoVacio(nodoActual)){
            return;
        }
        recorridoIn1(recorrido,nodoActual.hijoIzquierdo);
        recorrido.add(nodoActual.dato);
        recorridoIn1(recorrido,nodoActual.hijoDerecho);
    }
    // metodo de encontrar 
    // los elementos matores de un arbol de busqueda binaria
    public List<T> elementosMayores(){
        List<T> recorridoMayores=new ArrayList<>();
        if(!esArbolVacio()){
            Stack<NodoBinario<T>> pilaDeNodos=new Stack<>();
            NodoBinario<T> nodoEnTurno=raiz;
            aplilarInvercioderecha(pilaDeNodos,nodoEnTurno);
            while (!pilaDeNodos.isEmpty() && recorridoMayores.size()<3){
                nodoEnTurno=pilaDeNodos.pop();
                recorridoMayores.add(nodoEnTurno.dato);
                if(!esNodoVacio(nodoEnTurno.hijoIzquierdo)){
                    aplilarInvercioderecha(pilaDeNodos,nodoEnTurno.hijoIzquierdo);
                }
            }
        }
        Collections.sort(recorridoMayores,Collections.reverseOrder());
        return recorridoMayores;
    }
    public void aplilarInvercioderecha(Stack<NodoBinario<T>> pila,NodoBinario<T> nodoActual){
        while (!esNodoVacio(nodoActual)){
            pila.push(nodoActual);
            nodoActual=nodoActual.hijoDerecho;
        }
    }
    public List<T> mayoresBusqueda(){
        List<T> recorrido=new ArrayList<>();
        mayoresBusqueda(recorrido,raiz);
        Collections.sort(recorrido,Collections.reverseOrder());
        return recorrido;
    }
    private void mayoresBusqueda(List<T> recorrido, NodoBinario<T> nodoActual){
        if(recorrido.size()<3) {
            if (esNodoVacio(nodoActual)) {
                return;
            }
            mayoresBusqueda(recorrido, nodoActual.hijoDerecho);
            if(recorrido.size()<3) {
                recorrido.add(nodoActual.dato);
            }
            mayoresBusqueda(recorrido, nodoActual.hijoIzquierdo);
        }
    }
    public List<T> recorridoPre(){
        List<T> recorrido=new ArrayList<>();
        if(!esArbolVacio()){
            Stack<NodoBinario<T>> pila=new Stack<>();
            pila.push(raiz);
            do{
                NodoBinario<T> nodoTurno=pila.pop();
                recorrido.add(nodoTurno.dato);
                if(!esNodoVacio(nodoTurno.hijoDerecho)){
                    pila.push(nodoTurno.hijoDerecho);
                }
                if(!esNodoVacio(nodoTurno.hijoIzquierdo)){
                    pila.push(nodoTurno.hijoIzquierdo);
                }
            }while (!pila.isEmpty());
        }
        return recorrido;
    }
    public List<T> recorridoIn(){
        List<T> recorrido=new ArrayList<>();
        if(!esArbolVacio()) {
          Stack<NodoBinario<T>> pila=new Stack<>();
         apilado(pila,raiz);
         do{
             NodoBinario<T> nodoEnturno=pila.pop();
             recorrido.add(nodoEnturno.dato);
             if(!esNodoVacio(nodoEnturno.hijoDerecho)){
                 apilado(pila,nodoEnturno.hijoDerecho);
             }
         }while (!pila.isEmpty());
        }
        return recorrido;
    }
    public void apilado(Stack<NodoBinario<T>> pila,NodoBinario<T> nodoActual){
        while (!esNodoVacio(nodoActual)){
            pila.push(nodoActual);
            nodoActual=nodoActual.hijoIzquierdo;
        }
    }
    public void apilado1(Stack<NodoBinario<T>> pila){
     NodoBinario<T> ElNdo=pila.peek();
     while(!esHoja(ElNdo)){
         if(!esNodoVacio(ElNdo.hijoIzquierdo)){
             ElNdo=ElNdo.hijoIzquierdo;
         }else {
             ElNdo=ElNdo.hijoDerecho;
         }
         pila.push(ElNdo);
     }
    }

    public List<T> postO() {
        List<T> recorrido = new ArrayList<>();
        if (!esArbolVacio()) {
            Stack<NodoBinario<T>> pila = new Stack<>();
            pila.push(raiz);
            NodoBinario<T> nodoActual = null;
            apilado1(pila);
            do {
                NodoBinario<T> Comparas = pila.peek();
                if (!esNodoVacio(Comparas.hijoDerecho) && Comparas.hijoDerecho != nodoActual) {
                    pila.push(Comparas.hijoDerecho);
                    apilado1(pila);
                }
                nodoActual = pila.pop();
                recorrido.add(nodoActual.dato);
            } while (!pila.isEmpty());
        }
        return recorrido;
    }

    public  static void main(String[] args) {
        ArbolBinarioDeBusqueda<Integer> arbolBusqueda=new ArbolBinarioDeBusqueda<>();
        arbolBusqueda.insertarRecursivo(20);
        arbolBusqueda.insertarRecursivo(10);
        arbolBusqueda.insertarRecursivo(7);
        arbolBusqueda.insertarRecursivo(9);
        arbolBusqueda.insertarRecursivo(12);
        arbolBusqueda.insertarRecursivo(1);
        System.out.println(arbolBusqueda.toString());
        arbolBusqueda.eliminar(20);
        System.out.println(arbolBusqueda.toString());
        List<Integer> listaIn = Arrays.asList(1, 7, 9, 10, 12);
        List<Integer> listaPost = Arrays.asList(1, 9, 7, 12, 10);


        arbolBusqueda.reconstrucionconPostYInOrden(listaIn, listaPost);


        System.out.println(arbolBusqueda.postO());
        /*System.out.println(arbolBusqueda.toString());
        //arbolBusqueda.eliminar(30);
        System.out.println(arbolBusqueda.toString());
        System.out.println(arbolBusqueda.mayoresBusqueda());*/
    }
}
