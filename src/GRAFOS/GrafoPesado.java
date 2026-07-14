package GRAFOS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GrafoPesado <T extends Comparable<T>> {
    protected List<T> listaDeVertices;
    protected List<List<AdyacenteConPeso>> listaDeAdyacencia;//
    //  protected static final int POSICION_DEL_VERTICE_INVALIDO;

    public GrafoPesado()
    {
        this.listaDeVertices=new ArrayList<>();
        this.listaDeAdyacencia=new ArrayList<>();
    }
    public GrafoPesado(Iterable<T> vertices)
    {
        this();
        for(T unVertice:vertices){
            this.insertarVertice(unVertice);
        }
    }
    //__INSERTAR VERTICE___
    public void insertarVertice(T unVertice){
        int posVertice=this.getPosicionDeVertice(unVertice);
        if(posVertice != -1){
            throw new IllegalArgumentException("El vertice ya existe");
        }
        else
        {
            this.listaDeVertices.add(unVertice);
            this.listaDeAdyacencia.add(new ArrayList<>());
        }

    }
    protected int getPosicionDeVertice(T unVertice) {
        for (int i = 0; i < this.listaDeVertices.size(); i++) {
            T verticeEnTurno=(T)this.listaDeVertices.get(i);
            if(unVertice.compareTo(verticeEnTurno)==0)
            {
                return i;
            }
        }
        return -1;
    }
    public void validarVertice(T unVertice)
    {
        int posDelVertice=this.getPosicionDeVertice(unVertice);
        if(posDelVertice==-1)
        {
            throw new IllegalArgumentException("El vertice"+ unVertice.toString()+"no existe en su grafo");
        }
    }
    public int cantidadDeVertice()
    {
        return this.listaDeVertices.size();
    }
    public Iterable<T>getVertice(T unVertice)
    {

        return this.listaDeVertices;
    }
    /* public Iterable<T>getAdyacentesDeVertice(T unVertice){
         this.validarVertice(unVertice);
         int posVertice= this.getPosicionDeVertice(unVertice);
         List<AdyacenteConPeso> adyacentesDeUnVertice=(List)this.listaDeAdyacencia.get(posVertice);
         List<T>listaDeVerticesAdyacentes=new ArrayList<>();
         for (AdyacenteConPeso posDeUnVertice:adyacentesDeUnVertice) {
             T vertieEnTurno=this.listaDeVertices.get(posDeUnVertice.getIndiceVertice());

         }
     }*/
    public boolean existeAdyacencia(T Origen,T Destino)
    {
        this.validarVertice(Origen);
        this.validarVertice(Destino);

        int posOrigen=this.getPosicionDeVertice(Origen);
        int posDestino=this.getPosicionDeVertice(Destino);

        List<AdyacenteConPeso> adyacentes=this.listaDeAdyacencia.get(posOrigen);
        AdyacenteConPeso aristaBuscada=new AdyacenteConPeso(posDestino);
        return adyacentes.contains(aristaBuscada);
    }
    public int gradoDelVertice(T unVertice)
    {
        this.validarVertice(unVertice);
        int posDelVertice= this.getPosicionDeVertice(unVertice);
        return this.listaDeAdyacencia.get(posDelVertice).size();
    }
    public T verticePorPosicion(int posVertice)
    {
        if(posVertice>=0 && posVertice<this.listaDeVertices.size())
        {
            return this.listaDeVertices.get(posVertice);
        }
        else
        {
            throw new IllegalArgumentException("La posicion de vertice no es valida");

        }
    }
    //___INSERTAR ARISTA____
    public void insertarArista(T Origen,T Destino,double peso)
    {
        this.validarVertice(Origen);
        this.validarVertice(Destino);

        if(this.existeAdyacencia(Origen, Destino))
        {
            throw new IllegalArgumentException("La arista ya existe");
        }

        int posOrigen=this.getPosicionDeVertice(Origen);
        int posDestino=this.getPosicionDeVertice(Destino);

        List<AdyacenteConPeso> adyOrigen=this.listaDeAdyacencia.get(posOrigen);
        adyOrigen.add(new AdyacenteConPeso(posDestino,peso));

        Collections.sort(adyOrigen);
        // el origen es diferente al destino
        if (posOrigen!=posDestino) {
            List<AdyacenteConPeso>adyDestino=this.listaDeAdyacencia.get(posDestino);
            adyDestino.add(new AdyacenteConPeso(posOrigen,peso));
            Collections.sort(adyDestino);
        }


    }
    //__ELIMINAR ARISTA____
    public void eliminarArista(T Origen,T Destino)
    {
        this.validarVertice(Origen);
        this.validarVertice(Destino);

        if(!this.existeAdyacencia(Origen, Destino))
        {
            throw new IllegalArgumentException("El Arista No existe");
        }

        int posOrigen=this.getPosicionDeVertice(Origen);
        int posDestino=this.getPosicionDeVertice(Destino);

        List<AdyacenteConPeso> adyOrigen=this.listaDeAdyacencia.get(posOrigen);
        adyOrigen.remove(new AdyacenteConPeso(posDestino));
        if(posOrigen!=posDestino)
        {
            List<AdyacenteConPeso> adyDestino=this.listaDeAdyacencia.get(posDestino);
            adyDestino.remove(new AdyacenteConPeso(posOrigen));
        }
    }
    //__ELIMINAR VERTICE__
    public void eliminarVertice(T unVertice)
    {
        this.validarVertice(unVertice);
        // verificar si existe

        //2 obtener posicion
        int posicion=this.getPosicionDeVertice(unVertice);
        //  3 eliminar las referencias al vertice
        //eliminar vertices y sus listas
        listaDeVertices.remove(posicion);
        listaDeAdyacencia.remove(posicion);
        for (List<AdyacenteConPeso> adyacentes : listaDeAdyacencia) {
            for (int j = adyacentes.size() - 1; j >= 0; j--) {
                AdyacenteConPeso adyacente = adyacentes.get(j);
                int indiceDelAdyacente = adyacente.getIndiceVertice();
                if(indiceDelAdyacente == posicion)
                {
                    adyacentes.remove(j);
                }
                else if(indiceDelAdyacente > posicion)
                {
                    adyacente.setIndiceVertice(indiceDelAdyacente - 1);
                }
            }
        }
    }
    //_CANTIDAD DE ARISTAS__
    public int cantidadArista()
    {
        int total=0;

        for (List<AdyacenteConPeso> lista:this.listaDeAdyacencia) {
            total=total+lista.size();
        }
        return total/2;

    }
    public int cantidadDeArista()
    {
        int total=0;
        for (List<AdyacenteConPeso>lista:this.listaDeAdyacencia) {
            total=total+lista.size();
        }
        return total;
    }

    private boolean existeVertice(T unVertice) {
        for (T vertice:listaDeVertices) {
            if(vertice.compareTo(unVertice)==0){
                return true;
            }
        }
        return false;
    }

}
