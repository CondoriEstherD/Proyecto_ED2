package ARBOLES;

import java.util.ArrayList;
import java.util.List;

public class ArbolMViasBase <T extends Comparable<T>> {
    protected static class NodoMVias<T>{
        public List<T> datos;
        public List<NodoMVias<T>> hijos;
        public NodoMVias( int orden,T dato){
            datos=new ArrayList<>();
            hijos=new ArrayList<>();
            for(int i = 0;i < orden-1; i++){
                datos.add((T)DATO_VACIO);
                hijos.add(NODO_VACIO);
            }
            datos.set(0, dato);
            hijos.add(NODO_VACIO);
        }
    }
    protected NodoMVias<T> raiz;
    protected int orden;
    protected static NodoMVias NODO_VACIO=null;
    protected static Object DATO_VACIO=null;
    protected static final int POSICION_INVALIDAD=-1;
    protected static final int ORDEN_MINIMO=3;

    protected boolean esNodoVacio(NodoMVias<T> elNodo){
        return elNodo==NODO_VACIO;
    }
    protected NodoMVias<T> crearNodo(T dato){
        return new NodoMVias<>(this.orden,dato);
    }
    protected int nroDatosNoVacios(NodoMVias<T> elNodo){
        int cantidadPosicion=0;
        while(cantidadPosicion < elNodo.datos.size() &&
                !esDatoVacio(elNodo.datos.get(cantidadPosicion))) {
            cantidadPosicion++;
        }
        return cantidadPosicion;
    }
    protected boolean esDatoVacio(T dato){
        return dato==DATO_VACIO;
    }
    protected boolean esHoja(NodoMVias<T> elNodo){
        for(NodoMVias<T> hijo: elNodo.hijos){
            if(!esNodoVacio(hijo)){
                return false;
            }
        }
        return true;
    }
}
