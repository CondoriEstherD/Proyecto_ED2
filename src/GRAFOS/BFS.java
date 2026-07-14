package GRAFOS;
import java.util.LinkedList;
import java.util.Queue;

public class BFS <T extends Comparable<T>> extends RecorridoGrafo<T>{

    public BFS(Grafo<T> grafo, T verticeDePartida){

        super(grafo,verticeDePartida);
    }

    @Override
    public void ejecutarRecorrido(T verticeInicial) { //implementar
        Grafo.validarVertice(verticeInicial);
        Queue<T> colaDeVertices = new LinkedList();
        colaDeVertices.add(verticeInicial);
        int posDeVertice = Grafo.getNroDeVertice(verticeInicial);
        marcados.marcadosVertices(posDeVertice);
        do{
            T verticeEnTurno = colaDeVertices.poll();
            recorrido.add(verticeEnTurno);
            Iterable<T> adysDelVerticeEnTurno = Grafo.adyacentesDelVertice(verticeEnTurno);
            for (T adyacente : adysDelVerticeEnTurno) {
                int posDelAdyacente = Grafo.getNroDeVertice(adyacente);
                if (!marcados.estaVerticeMarcado(posDelAdyacente)) {
                    colaDeVertices.offer(adyacente);
                    marcados.marcadosVertices(posDelAdyacente);
                }
            }
        } while(!colaDeVertices.isEmpty());
    }
}
