package GRAFOS;

import java.util.ArrayList;
import java.util.List;

public abstract class RecorridoGrafo <T extends Comparable<T>> {
    protected Grafo<T> Grafo;
    protected ControlMarcados marcados;
    protected List<T> recorrido;
    public RecorridoGrafo(Grafo<T> unGrafo, T verticeDePartida){
        unGrafo.validarVertice(verticeDePartida);
        this.Grafo = unGrafo;
        this.marcados = new ControlMarcados(Grafo.contarVertices());
        this.recorrido = new ArrayList<>();
        ejecutarRecorrido(verticeDePartida);
    }

    public abstract void ejecutarRecorrido(T verticeEnTurno);

    public Iterable<T> getRecorrido(){

        return recorrido;
    }

    public boolean seVisitoVertice(T vertice){
        Grafo.validarVertice(vertice);
        int posDeVertice = Grafo.getNroDeVertice(vertice);
        return marcados.estaVerticeMarcado(posDeVertice);
    }

    public boolean seVisitoTodosLosVertices(){
        return marcados.estanTodosMarcados();
    }

}