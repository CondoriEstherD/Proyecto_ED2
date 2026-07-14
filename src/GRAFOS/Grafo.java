package GRAFOS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grafo <T extends Comparable<T>>{
    protected List<T> listaDeVertices;
    protected List<List<Integer>> listaDeAdyacencia;
    protected static final int NRO_VERTICE_INVALIDO=-1;
    public Grafo(){
        listaDeVertices=new ArrayList<>();
        listaDeAdyacencia=new ArrayList<>();
    }
    public Grafo(Iterable<T> vertices){
        this();
        for(T unVertice: vertices){
            insertarVertice(unVertice);
        }
    }
    protected void validarVertice(T unVertice)  {
        if(unVertice==null){
            throw new NullPointerException("El vertice no puede ser nulo");
        }
        if(getNroDeVertice(unVertice)==NRO_VERTICE_INVALIDO){
            throw new NullPointerException("Vertice no encontrado");
        }
    }
    protected int getNroDeVertice(T unVertice){
        for(int i=0; i< listaDeVertices.size(); i++){
            if(listaDeVertices.get(i).compareTo(unVertice)==0){
                return i;
            }
        }
        return NRO_VERTICE_INVALIDO;
    }
    public void insertarVertice(T unVertice){
        if(existeVertice(unVertice)){
            throw new IllegalArgumentException("Vertice ya existente en el grefo");
        }
        listaDeVertices.add(unVertice);
        List<Integer> listDeAdjacencyDelVertices=new ArrayList<>();
        listaDeAdyacencia.add(listDeAdjacencyDelVertices);
    }
    public boolean existeVertice(T unVertice){
        if(unVertice==null){
            throw new NullPointerException("Vertice no pude ser nulo");
        }
        return getNroDeVertice(unVertice) != NRO_VERTICE_INVALIDO;
    }
    public boolean adyacencia(T verticeOrigen, T verticeDestino){
        validarVertice(verticeOrigen);
        validarVertice(verticeDestino);
        int nroDeVerticeOrigen=getNroDeVertice(verticeOrigen);
        int nroDeVerticeDestino=getNroDeVertice(verticeDestino);
        List<Integer> listaDeAdyacenciaOrigen=listaDeAdyacencia.get(nroDeVerticeOrigen);
        return listaDeAdyacenciaOrigen.contains(nroDeVerticeDestino);
    }
    public void insertarArista(T verticeOrigen, T verticeDestino){
        if(adyacencia(verticeOrigen,verticeDestino)){
            throw new IllegalArgumentException("Arista ya existente en el grafo");
        }
        int nroDeVerticeOrigen=getNroDeVertice(verticeOrigen);
        int nroDeVerticeDestino=getNroDeVertice(verticeDestino);
        List<Integer> listaAdyacenciaOrigen=listaDeAdyacencia.get(nroDeVerticeOrigen);
        listaAdyacenciaOrigen.add(nroDeVerticeDestino);
        Collections.sort(listaAdyacenciaOrigen);
        if(nroDeVerticeOrigen!=nroDeVerticeDestino){
            List<Integer> listaAdyacenciaDestino=listaDeAdyacencia.get(nroDeVerticeDestino);
            listaAdyacenciaDestino.add(nroDeVerticeOrigen);
            Collections.sort(listaAdyacenciaDestino);
        }
    }
    public void eliminarVertice(T unVertice){
        validarVertice(unVertice);
        int nroVerticeEliminar=getNroDeVertice(unVertice);
        listaDeVertices.remove(nroVerticeEliminar);
        listaDeAdyacencia.remove(nroVerticeEliminar);
        for(List<Integer> adyacentesDeUnVertice: listaDeAdyacencia){
            adyacentesDeUnVertice.remove(nroVerticeEliminar);
            for(int i=0;i < adyacentesDeUnVertice.size();i++){
                int adyacente=adyacentesDeUnVertice.get(i);
                if(adyacente > nroVerticeEliminar){
                    adyacente--;
                    adyacentesDeUnVertice.set(i,adyacente);
                }
            }
        }
    }
    public int contarVertices(){
        return listaDeVertices.size();
    }
    public int contarArista(){
        int contadorArista=0;
        for(int i=0; i < listaDeAdyacencia.size(); i++){
            List<Integer> listaAdyacenciaTurno=listaDeAdyacencia.get(i);
            for(Integer elemento:listaAdyacenciaTurno){
                if(elemento<=i){
                    contadorArista++;
                }
            }
        }
        return contadorArista;
    }
    public Iterable<T> adyacentesDelVertice(T unVertice) {
        validarVertice(unVertice);

        int nroDeVertice = getNroDeVertice(unVertice);

        List<Integer> listaDeAdyacentes = listaDeAdyacencia.get(nroDeVertice);

        List<T> verticesAdyacentes = new ArrayList<>();

        for (Integer posicionDelAdyacente : listaDeAdyacentes) {
            T verticeAdyacente = listaDeVertices.get(posicionDelAdyacente);
            verticesAdyacentes.add(verticeAdyacente);
        }

        return verticesAdyacentes;
    }
    public void eliminarAristas(T verticeOrigen, T verticeDestino){
        if(!adyacencia(verticeOrigen,verticeDestino)){
            throw new IllegalArgumentException("no existe adyacencia");
        }
        int nroVerticeOrigen=getNroDeVertice(verticeOrigen);
        int nroVerticeDestino=getNroDeVertice(verticeDestino);
        List<Integer> listaAdyacenciaOrigen=listaDeAdyacencia.get(nroVerticeOrigen);
        listaAdyacenciaOrigen.remove(nroVerticeDestino);
        List<Integer> listaAdyacenciaDestino=listaDeAdyacencia.get(nroVerticeDestino);
        listaAdyacenciaDestino.remove(nroVerticeOrigen);
    }
    // es un grafo conexo
    public boolean esConexo(){
        if(listaDeVertices.isEmpty()){
            return true;
        }
        T verticePrimero=listaDeVertices.get(0);
        DFS<T> recorrido=new DFS<>(this,verticePrimero);
        return recorrido.seVisitoTodosLosVertices();
    }
    public boolean esFuertementeConexo(){
        if(listaDeVertices.isEmpty()){
            return true;
        }
        for(T verticeEnTurno:listaDeVertices){
            DFS<T> recorrido=new DFS<>(this,verticeEnTurno);
            if (!recorrido.seVisitoTodosLosVertices()){
                return false;
            }
        }
        return true;
    }
    // hay camino
    public boolean hayCamino(T verticeOrigen,T verticeDestino){
        BFS<T> recorridoBFS=new BFS<>(this,verticeOrigen);
        return recorridoBFS.seVisitoVertice(verticeDestino);
    }
    /*
    Para un grafo no dirigido implementar un metodo que retorne verdadero
    si el el grafo tiene ciclo. Su método debe utilizar como base de su
    logica alguno de los dos recorridos vistos en clases
    */
    public boolean hayCiclo(){
        if(listaDeVertices.isEmpty()){
            return false;
        }
        List<T> listaVeticesProcesados=new ArrayList<>();
        for(T verticeActual:listaDeVertices){
            if(!contieneVertice(listaVeticesProcesados,verticeActual)){
                List<T> componentes=new ArrayList<>();
                DFS<T> recorridoDfs=new DFS<>(this,verticeActual);
                for(T verticeNuevo:recorridoDfs.getRecorrido()){
                    if(!contieneVertice(listaVeticesProcesados,verticeNuevo)){
                        componentes.add(verticeNuevo);
                        listaVeticesProcesados.add(verticeNuevo);
                    }
                }
                if (tieneCiclo(componentes)){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean tieneCiclo(List<T> componentes){
        int cantidadArista=0;
        for(T verticeActual:componentes) {
            if (adyacencia(verticeActual, verticeActual)) {
                return true;
            }
            Iterable<T> listaDeAdyacenciasDeVertice = adyacentesDelVertice(verticeActual);
            for(T verticeEnTurno:listaDeAdyacenciasDeVertice){
                if(contieneVertice(componentes,verticeEnTurno)){
                    cantidadArista++;
                }
            }
        }
        cantidadArista=cantidadArista/2;
        return cantidadArista >= componentes.size();
    }

    public boolean contieneVertice(List<T> listaVertices,T verticeBuscado){
        for(T verticeEnTurno : listaVertices){
            if(verticeBuscado.compareTo(verticeEnTurno)==0){
                return true;
            }
        }
        return false;
    }
    /*
    Para un grafo no dirigido implementar un método que retorne la cantidad
    de islas que tiene el grafo
    */
    // Contar las Islas
    public int contarLasIslas(){
        if(listaDeVertices.isEmpty()){
            return 0;
        }
        int contarIslas=0;
        List<T> listaDeVeticesProcesados=new ArrayList<>();
        for(T verticeActual:listaDeVertices){
            if(!contieneVertice(listaDeVeticesProcesados,verticeActual)){
                contarIslas++;
                DFS<T> recorrido=new DFS<>(this,verticeActual);
                for (T verticeEnTurno:recorrido.getRecorrido()){
                    if(!contieneVertice(listaDeVeticesProcesados,verticeEnTurno)){
                        listaDeVeticesProcesados.add(verticeEnTurno);
                    }
                }
            }
        }
        return contarIslas;
    }
    /*
    para un grafo implementar un metodo que retorne verdadero si se puede
    llegar desde un vertice a otro, solo usando en su lógica alguno de los
    recorridos vistos en clases
    * */

}
