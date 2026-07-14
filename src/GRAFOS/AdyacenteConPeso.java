package GRAFOS;

public class AdyacenteConPeso implements  Comparable<AdyacenteConPeso> {

    private int indiceVertice;
    private double peso;
    public AdyacenteConPeso(int vertice){
        this.indiceVertice=vertice;
    }
    public AdyacenteConPeso(int vertice,double peso){
        this.indiceVertice=vertice;
        this.setPeso(peso);
    }
    public int getIndiceVertice(){
        return this.indiceVertice;
    }
    public void setIndiceVertice(int vertice){
        this.indiceVertice=vertice;
    }

    public double getPeso() {
        return this.peso;
    }

    public void setPeso(double peso) {
        if(peso<0){
            throw new IllegalArgumentException("Peso Invalido");
        }
        this.peso = peso;
    }

    @Override
    public int compareTo(AdyacenteConPeso vertice) {
        Integer esteVetice=this.indiceVertice;
        Integer otroVertice=vertice.indiceVertice;
        return esteVetice.compareTo(otroVertice);
    }

    @Override
    public int hashCode() {
        int hash=3;
        hash=67 * hash + this.indiceVertice;
        return hash;
    }
    @Override
    public boolean equals(Object otro) {
        if (this == otro) {
            return true;
        }
        if (otro == null) {
            return false;
        }
        if (this.getClass() != otro.getClass()) {
            return false;
        }

        AdyacenteConPeso other = (AdyacenteConPeso) otro;
        return this.indiceVertice == other.indiceVertice;
    }
}
