package GRAFOS;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Clase Dijkstra para grafos no dirigidos y dirigidos con peso
 * @param <T>
 */
public class Dijkstra<T extends Comparable<T>> {
    private GrafoPesado<T> grafoPesado;
    private ControlMarcados controlMarcados;
    private double[] costos;
    private int[] predecesores;
    private List<T> listaDeVerticesMenorCamino;
    private double menorCostoFinal;
    
    public Dijkstra(GrafoPesado<T> grafoPesado, T verticeOrigen, T verticeLLegada) {
        this.grafoPesado = grafoPesado;
        int cantidadVertices = this.grafoPesado.cantidadDeVertice();
        this.controlMarcados = new ControlMarcados(cantidadVertices);
        this.costos = new double[cantidadVertices];
        this.predecesores = new int[cantidadVertices];
        
        for (int i = 0; i < cantidadVertices; i++) {
            this.costos[i] = Double.POSITIVE_INFINITY;
            this.predecesores[i] = -1;
        }
        
        this.listaDeVerticesMenorCamino = new ArrayList<>();
        ejecutarAlgoritmoDijkstra(verticeOrigen, verticeLLegada);
    }

    private void ejecutarAlgoritmoDijkstra(T verticeOrigen, T verticeLLegada) {
        grafoPesado.validarVertice(verticeOrigen);
        grafoPesado.validarVertice(verticeLLegada);
        
        int posVerticeOrigen = grafoPesado.getPosicionDeVertice(verticeOrigen);
        int posVerticeLLegada = grafoPesado.getPosicionDeVertice(verticeLLegada); 
        costos[posVerticeOrigen] = 0;
        
        int posDelVerticeNoMarcado;
        
        while(!controlMarcados.estaVerticeMarcado(posVerticeLLegada)){
            posDelVerticeNoMarcado = posicionDelCostoMenorNoMarcado();
            
            if (posDelVerticeNoMarcado == -1) {
                throw new IllegalArgumentException("No existe camino entre los vertices");
            }
            
            controlMarcados.marcadosVertices(posDelVerticeNoMarcado);  
            
            double costoDelVerticeNoMarcado = costos[posDelVerticeNoMarcado];
            if(costoDelVerticeNoMarcado == Double.POSITIVE_INFINITY){
                throw new IllegalArgumentException("No existe camino entre los vertices");
            }
            
            List<AdyacenteConPeso> adyacentes = grafoPesado.listaDeAdyacencia.get(posDelVerticeNoMarcado);
            
            if (!controlMarcados.estaVerticeMarcado(posVerticeLLegada)) {
                for (AdyacenteConPeso adyacente : adyacentes) {
                    int posVerticeAdyacente = adyacente.getIndiceVertice();
                    double costoDelAdyacenteEnTurno = costos[posVerticeAdyacente];
                    double costoDelAdyacente = adyacente.getPeso();
                    
                    if (costoDelAdyacenteEnTurno > (costoDelVerticeNoMarcado + costoDelAdyacente)) {
                        costos[posVerticeAdyacente] = costoDelVerticeNoMarcado + costoDelAdyacente;
                        predecesores[posVerticeAdyacente] = posDelVerticeNoMarcado; 
                    }
                }
            }
        }
        
        caminoFinalEntreVertices(posVerticeLLegada);
        costoMenor(posVerticeLLegada);
    }

    private int posicionDelCostoMenorNoMarcado() {
        double menorCosto = Double.POSITIVE_INFINITY;
        int verticeMenor = -1;

        for (int i = 0; i < costos.length; i++) {
            if (!controlMarcados.estaVerticeMarcado(i)) {
                double costo = costos[i];
                if (costo < menorCosto) {
                    menorCosto = costo;
                    verticeMenor = i;
                }
            }
        }

        return verticeMenor;
    }

    private void caminoFinalEntreVertices(int posVerticeLLegada) {
        Stack<Integer> pilaPosicionesDeVertice = new Stack<>();
        int posicionesDelCamino = posVerticeLLegada;
        
        while(posicionesDelCamino != -1){
            pilaPosicionesDeVertice.push(posicionesDelCamino);
            posicionesDelCamino = predecesores[posicionesDelCamino];
        }
        
        while(!pilaPosicionesDeVertice.isEmpty()){
            int posicionVertice = pilaPosicionesDeVertice.pop();
            listaDeVerticesMenorCamino.add(grafoPesado.verticePorPosicion(posicionVertice));
        }
    }
    
    public List<T> getCaminoMasCorto(){
        return this.listaDeVerticesMenorCamino;
    } 
    
    public double getMenorCosto(){
        return this.menorCostoFinal;
    } 
    
    private void costoMenor(int posVerticeLLegada) {
        this.menorCostoFinal = costos[posVerticeLLegada];
    }
}
