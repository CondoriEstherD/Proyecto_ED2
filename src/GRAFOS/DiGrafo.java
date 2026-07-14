package GRAFOS;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DiGrafo<T extends Comparable<T>> extends Grafo<T>{
    public DiGrafo(){
        super();
    }
    public DiGrafo(Iterable<T> vertices){
        super(vertices);
    }
    public int gradoDelVertice(T unVertice){
        throw new UnsupportedOperationException("metodo no soportado"+"en grafo dirigido");
    }

    @Override
    public void eliminarVertice(T unVertice) {
        super.eliminarVertice(unVertice);
    }


    @Override
    public void insertarVertice(T unVertice) {
        if(existeVertice(unVertice)){
            throw new IllegalArgumentException("Nose insertar verties repetidos");
        }
        listaDeVertices.add(unVertice);
        List<Integer> listaDeadyacenciaOrigen=new ArrayList<>();
        listaDeAdyacencia.add(listaDeadyacenciaOrigen);
    }
    public void insertarArista(T verticeOrigen, T verticeDestino){
        if(adyacencia(verticeOrigen,verticeDestino)){
            throw new IllegalArgumentException("Adyacencia ya existente");
        }
        int nroVerticeOrigen=getNroDeVertice(verticeOrigen);
        int nroVerticeDestino=getNroDeVertice(verticeDestino);
        List<Integer> listaDeverticeOrigen=listaDeAdyacencia.get(nroVerticeOrigen);
        listaDeverticeOrigen.add(nroVerticeDestino);
        Collections.sort(listaDeverticeOrigen);
    }
    public void eliminarAristas(T verticeOrigen, T verticeDestino) {
      validarVertice(verticeDestino);
        validarVertice(verticeOrigen);
      if(!adyacencia(verticeOrigen,verticeDestino)){
          throw new IllegalArgumentException("No hay adyacencia");
      }
      int nroVerticeOrigen=getNroDeVertice(verticeOrigen);
      int nroVerticeDestino=getNroDeVertice(verticeDestino);
      List<Integer> listaAdyacenciaOrigen=listaDeAdyacencia.get(nroVerticeOrigen);
      listaAdyacenciaOrigen.remove(nroVerticeDestino);
    }

    @Override
    public int contarArista() {

        return super.contarArista();
    }

    @Override
    public int contarVertices() {

        return super.contarVertices();
    }

    @Override
    public boolean adyacencia(T verticeOrigen, T verticeDestino) {
        return super.adyacencia(verticeOrigen, verticeDestino);
    }

    @Override
    public boolean existeVertice(T unVertice) {

        return super.existeVertice(unVertice);
    }
    /*Para un grafo dirigido implementar un metodo que retorne verdadero
      si el el grafo tiene ciclo. Su método debe utilizar como base de su
      logica alguno de los dos recorridos vistos en clases
     */
    public boolean hayciclo(){
        if(listaDeVertices.isEmpty()){
            return false;
        }
        for(T vertice:listaDeVertices){
            Iterable<T> listaDeVertice=adyacentesDelVertice(vertice);
            for(T verticeDestino:listaDeVertice){
                DFS<T> recorrido=new DFS<>(this,verticeDestino);
                if(recorrido.seVisitoVertice(vertice)){
                    return true;
                }
            }
        }
        return false;
    }
    public int contarIslas(){
        if(listaDeVertices.isEmpty()){
            return 0;
        }
        int contarIslas=0;
        List<T> listaVerticesProcesados=new ArrayList<>();
        for(T vertice:listaDeVertices){
            if(!contieneVertice(listaVerticesProcesados,vertice)){
                DFS<T> recorrido=new DFS<>(this,vertice);
                for(T verticeEnTurno: recorrido.getRecorrido()){
                    if(!contieneVertice(listaVerticesProcesados,verticeEnTurno)){
                        listaVerticesProcesados.add(verticeEnTurno);
                    }
                }
                contarIslas++;
            }
        }
        return contarIslas;
    }

}
