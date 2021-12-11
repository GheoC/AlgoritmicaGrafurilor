package Model;

import java.util.ArrayList;
import java.util.List;

public class GraphData
{
    private List<Integer> predecesor = new ArrayList<>();
    private List<Integer> t1 = new ArrayList<>();
    private List<Integer> t2 = new ArrayList<>();
    private List<List<Integer>> componenteConexe = new ArrayList<>();

    public List<Integer> getPredecesor() {
        return predecesor;
    }

    public void setPredecesor(List<Integer> predecesor) {
        this.predecesor = predecesor;
    }

    public List<Integer> getT1() {
        return t1;
    }

    public void setT1(List<Integer> t1) {
        this.t1 = t1;
    }

    public List<Integer> getT2() {
        return t2;
    }

    public void setT2(List<Integer> t2) {
        this.t2 = t2;
    }

    public List<List<Integer>> getComponenteConexe() {
        return componenteConexe;
    }

    public void setComponenteConexe(List<List<Integer>> componenteConexe) {
        this.componenteConexe = componenteConexe;
    }
}
