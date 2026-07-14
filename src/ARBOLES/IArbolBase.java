/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ARBOLES;

import java.util.List;

/**
 *
 * @author MARILYN
 * @param <T>
 */
public interface IArbolBase <T> {
     void insertar (T dato);

    /**
     *
     * @param datoAEliminar
     */
    void eliminar(T datoAEliminar);
    T buscar(T dato);
    boolean contiene(T dato);
    int size();
    int altura();
    void vaciar();
    boolean esArbolVacio();
    int nivel();
    List<T> recorridoEnInOrden();
    List<T> recorridoEnPreOrden();
    List<T> recorridoEnPostOrden();
    List<T> recorridoPorNiveles();
    String toString ();
}
