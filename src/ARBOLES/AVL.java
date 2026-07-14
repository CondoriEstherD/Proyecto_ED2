package ARBOLES;

//import org.jetbrains.annotations.NotNull;

public class AVL<T extends Comparable<T>> extends ArbolBinarioDeBusqueda<T> {

    public AVL() {
        super();
    }
    private static final byte LIMITED_MAXIMO = 1;
    public void insertar(T dato){
        if(dato==null){
            throw new IllegalArgumentException("No se insertar datos nulos");
        }
        this.raiz=insertar(this.raiz,dato);
    }
    private NodoBinario<T> insertar(NodoBinario<T> nodoActual,T datoAInsertar){
        if(esNodoVacio(nodoActual)){
           return  crearNodo(datoAInsertar);
        }
        T datoActual=nodoActual.dato;
        if(datoAInsertar.compareTo(datoActual)<0){
            NodoBinario<T> subArblIzqui=insertar(nodoActual.hijoIzquierdo,datoAInsertar);
            nodoActual.hijoIzquierdo=subArblIzqui;
            return balancear(nodoActual);
        }else{
            if(datoAInsertar.compareTo(datoActual)>0){
                NodoBinario<T> subArblDere=insertar(nodoActual.hijoDerecho,datoAInsertar);
                nodoActual.hijoDerecho=subArblDere;
                return balancear(nodoActual);
            }else{
                throw new IllegalArgumentException("no se insertan datos repetidos");
            }
        }
    }
    public NodoBinario<T> balancear(NodoBinario<T> nodoActual){
        int alturaIzq=altura(nodoActual.hijoIzquierdo);
        int alturaDere=altura(nodoActual.hijoDerecho);
        if(alturaIzq-alturaDere > LIMITED_MAXIMO){
            NodoBinario<T> nodoNuevo=nodoActual.hijoIzquierdo;
            alturaIzq=altura(nodoNuevo.hijoIzquierdo);
            alturaDere=altura(nodoNuevo.hijoDerecho);
            if(alturaIzq < alturaDere){
                return rotacionDobleDerecha(nodoActual);
            }
            return rotacionSimpleDerecha(nodoActual);
        }
        if(alturaIzq-alturaDere < -LIMITED_MAXIMO) {
            NodoBinario<T> nodoNuevo = nodoActual.hijoDerecho;
            alturaIzq = altura(nodoNuevo.hijoIzquierdo);
            alturaDere = altura(nodoNuevo.hijoDerecho);
            if (alturaIzq > alturaDere) {
                return rotacionDobleIzquierda(nodoActual);
            }
            return rotacionSimpleIzquierda(nodoActual);
        }
        return nodoActual;
    }

    public NodoBinario<T> crearNodo(T dato){
        return new NodoBinario<>(dato);
    }
    public NodoBinario<T> rotacionSimpleIzquierda(NodoBinario<T> nodoActual){
        NodoBinario<T> hijoDereActual=nodoActual.hijoDerecho;
        nodoActual.hijoDerecho=hijoDereActual.hijoIzquierdo;
        hijoDereActual.hijoIzquierdo=nodoActual;
        return hijoDereActual;
    }
    public NodoBinario<T> rotacionSimpleDerecha(NodoBinario<T> nodoActual){
        NodoBinario<T> hijoIzquiActual=nodoActual.hijoIzquierdo;
        nodoActual.hijoIzquierdo=hijoIzquiActual.hijoDerecho;
        hijoIzquiActual.hijoDerecho=nodoActual;
        return hijoIzquiActual;
    }
    public NodoBinario<T> rotacionDobleIzquierda(NodoBinario<T> nodoActual){
          nodoActual.hijoDerecho=rotacionSimpleDerecha(nodoActual.hijoDerecho);
          return rotacionSimpleIzquierda(nodoActual);
    }
    public NodoBinario<T> rotacionDobleDerecha(NodoBinario<T> nodoActual){
        nodoActual.hijoIzquierdo=rotacionSimpleIzquierda(nodoActual.hijoIzquierdo);
        return rotacionSimpleDerecha(nodoActual);
    }
    public int altura(NodoBinario<T> nodoActual) {
        return altura1(nodoActual);
    }

    private int altura1(NodoBinario<T> nodoActual) {
        if (esNodoVacio(nodoActual)) {
            return 0;
        }
        int ladoDerecho = altura(nodoActual.hijoDerecho);
        int ladoIzquierdo = altura(nodoActual.hijoIzquierdo);
        return (ladoDerecho > ladoIzquierdo) ? ladoDerecho + 1 : ladoIzquierdo + 1;
    }
    public void eliminar(T datoAEliminar) {

        if (datoAEliminar == null) {
            throw new IllegalArgumentException("!No se puede eliminar nulos !");
        }
        super.raiz = eliminar(super.raiz, datoAEliminar);
    }

    private NodoBinario<T> eliminar(NodoBinario<T> nodoActual, T datoAEliminar){

        if (esNodoVacio(nodoActual)) {
            return NODO_VACIO;
        }

        if (datoAEliminar.compareTo(nodoActual.dato) < 0) { // si es menor mando el subIzquierdo
            nodoActual.hijoIzquierdo = eliminar(nodoActual.hijoIzquierdo, datoAEliminar); //enganchamos el nodo actual con el subArbolIzquierdo eliminado
            return balancear(nodoActual);
        } else if (datoAEliminar.compareTo(nodoActual.dato) > 0) { //si es mayor mando el subDerecho
            nodoActual.hijoDerecho = eliminar(nodoActual.hijoDerecho, datoAEliminar);
            return balancear(nodoActual);
        } else { // si es igual, estoy en el nodo que quiero eliminar

            if (esNodoHoja(nodoActual)) { //CASO 1
                nodoActual = NODO_VACIO;
                return nodoActual; // eliminamos el nodoActual y su padre se encargara de balancear
            }

            if (!esNodoVacio(nodoActual.hijoIzquierdo) && esNodoVacio(nodoActual.hijoDerecho)) { // CASO 2 : tiene un hijo izquierdo
                return nodoActual.hijoIzquierdo; //lo enganchamos al padre del nodoActual con su hijo izquierdo, y el padre se encargara de balancear
            }

            if (!esNodoVacio(nodoActual.hijoDerecho) && esNodoVacio(nodoActual.hijoIzquierdo)) { // CASO 2 : tiene un hijo derecho
                return nodoActual.hijoDerecho;
            }

            //CASO 3 : tiene lo 2 hijos

            NodoBinario<T> sucesorInOrden = sucesorInOrden(nodoActual.hijoDerecho);
            nodoActual.dato = sucesorInOrden.dato;
            NodoBinario<T> nodoDerecho = eliminar(nodoActual.hijoDerecho, sucesorInOrden.dato);
            nodoActual.hijoDerecho = nodoDerecho;
            return balancear(nodoActual); // el nodo actual se debe balancear antes de que se enganche nuevamente a su padre

        }

    }

    private NodoBinario<T> sucesorInOrden(NodoBinario<T> nodoActual) {
        if (esNodoVacio(nodoActual)) {
            return NODO_VACIO;
        }

        NodoBinario<T> sucesor = sucesorInOrden(nodoActual.hijoIzquierdo);
        if (esNodoVacio(sucesor)) {
            return nodoActual;
        }
        return sucesor;
    }
    public boolean esNodoHoja(NodoBinario<T> nodoActual){
        return nodoActual==NODO_VACIO;
    }
}
