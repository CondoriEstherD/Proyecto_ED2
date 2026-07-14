package GRAFOS;

public class DFS <T extends Comparable<T>> extends RecorridoGrafo<T>{
    public DFS(Grafo<T> grafo, T verticeDePartida){
        super(grafo,verticeDePartida);
    }

    @Override
    public void ejecutarRecorrido(T verticeEnTurno) { //implementar
        Grafo.validarVertice(verticeEnTurno);
        int posDelVerticeEnTurno = Grafo.getNroDeVertice(verticeEnTurno);
        marcados.marcadosVertices(posDelVerticeEnTurno);
        recorrido.add(verticeEnTurno);
        Iterable<T> adysDelVerticeEnTurno = Grafo.adyacentesDelVertice(verticeEnTurno);
        for (T adyacente : adysDelVerticeEnTurno) {
            int posDelAdyacente = Grafo.getNroDeVertice(adyacente);
            if (!marcados.estaVerticeMarcado(posDelAdyacente)) {
                ejecutarRecorrido(adyacente);
            }
        }

    }
}
