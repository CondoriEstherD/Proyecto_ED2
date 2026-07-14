package GRAFOS;

import java.util.ArrayList;
import java.util.List;

public class ControlMarcados  {
    private List<Boolean> marcados;
    public ControlMarcados( int nroVertices)
    {
        if(nroVertices<=0)
        {
            throw new IllegalArgumentException("cantidad de los verticees debe ser mayor a cero");
        }
        marcados=new ArrayList<>();
        for (int i = 0; i < nroVertices ; i++) {
            marcados.add(false);
        }
    }
    public void marcadosVertices(int posVertices)
    {
        marcados.set(posVertices,true);

    }
    public void desmarcadosVertices(int posVertices)
    {
        marcados.set(posVertices,false);

    }
    public void desmarcarTodos()
    {
        for (int i = 0; i < marcados.size(); i++) {
            marcados.set(i,false);
        }

    }
    public boolean estaVerticeMarcado(int posVerice)
    {

        return marcados.get(posVerice);
    }
    public boolean estanTodosMarcados()
    {
        return !marcados.contains(false);
    }
}
