package Service;

import Model.Graph;
import Model.GraphData;
import Model.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class SortareTopologica {

    private Stack<Integer> analyzed = new Stack<>();
    private List<Integer> nodesInDegreeZero = new ArrayList<>();
    private List<Integer> unvisited = new ArrayList<>();
    private List<Integer> visited = new ArrayList<>();
    private Stack<Integer> analyzedGlobally = new Stack<>();


    //metoda de sortare topologica
    //
    public void sortTopo(Graph graph)
    {
        GraphService graphService = new GraphService();
        calculateIndegreeOfGraph(graph);

//        Pentru a testa faptul ca sortarea topologica genereaza alta solutie in functie de ordinea nodurile selectate la parcurgere
//        Collections.shuffle(nodesInDegreeZero);

        for (int i = 0; i < nodesInDegreeZero.size(); i++)
        {
            System.out.println("\n========================== Iteratia DFS pentru nodul start = "+nodesInDegreeZero.get(i)+" ==================");
            System.out.println("Se selecteaza nodul: "+ nodesInDegreeZero.get(i));
            DFS(graph, nodesInDegreeZero.get(i));
        }

        System.out.println("\nSortarea topologica cu parcurgerea nodurile " +nodesInDegreeZero+" in ordinea aceasta este: ");
        while (!analyzedGlobally.isEmpty())
        {
            System.out.print(analyzedGlobally.peek()+", ");
            analyzedGlobally.pop();
        }
    }

    //metoda care identifica nodurile care au 0 (zero) intrari
    public void calculateIndegreeOfGraph(Graph graph) {
        List<Integer> inDegree = new ArrayList<>();
        for (int i = 0; i < graph.getNodes().size(); i++) {
            inDegree.add(0);
        }
        for (int i = 0; i < graph.getNodes().size(); i++) {
            for (int j = 0; j < graph.getNodes().get(i).getLinks().size(); j++) {
                Integer index = graph.getNodes().get(i).getLinks().get(j) - 1;
                inDegree.set(index, inDegree.get(index) + 1);
            }
        }

        for (int i = 0; i < inDegree.size(); i++)
        {
            if (inDegree.get(i)==0)
            {
                nodesInDegreeZero.add((i+1));
            }
        }
    }


    //in mare parte copiat si adaptat din clasa GraphService
    public void DFS(Graph graph, Integer startNode)
    {
        List<Integer> p = new ArrayList<>();
        List<Integer> t1 = new ArrayList<>();
        List<Integer> t2 = new ArrayList<>();
        for (Node node : graph.getNodes()) {
            unvisited.add(node.getNodeNumber());
            p.add(0);
            t1.add(0);
            t2.add(0);
        }
        Integer t = 1;
        t1.set(startNode - 1, 1);
        unvisited.remove(startNode);
        unvisited.removeAll(analyzedGlobally);

        //pt DFS se foloseste "visited" de tip stiva
        Stack<Integer> visited = new Stack<>();
        visited.push(startNode);

        Integer selectedNode;
        System.out.print("Unvisited is: " + unvisited + "; ");
        System.out.print("Visited is: " + visited + "; ");
        System.out.println();

        List<Integer> curentNodeUnvisitedLinks = new ArrayList<>();

        //pentru componente conexe
        List<List<Integer>> componenteConexe = new ArrayList<>();

        while (!visited.isEmpty()) {

            selectedNode = visited.peek();

            List<Integer> selectedNodeLinks = graph.getLinksForNode(selectedNode);

            curentNodeUnvisitedLinks.clear();
            for (int i = 0; i < selectedNodeLinks.size(); i++) {
                if (unvisited.contains(selectedNodeLinks.get(i))) {
                    curentNodeUnvisitedLinks.add(selectedNodeLinks.get(i));

                    //se adauga doar primul not de legatura inca nevizitat;
                    break;
                }
            }

            System.out.print("\nNe-am pozitionat pe nodul: " + selectedNode + "  => urmatoarea legatura este: ");
            if (!curentNodeUnvisitedLinks.isEmpty()) {
                System.out.println(curentNodeUnvisitedLinks.get(0));
                System.out.print("Se adauga in visited urmatoarul nod: ");
                for (int i = 0; i < curentNodeUnvisitedLinks.size(); i++) {
                    unvisited.remove(curentNodeUnvisitedLinks.get(i));
                    visited.push(curentNodeUnvisitedLinks.get(i));
                    System.out.print(curentNodeUnvisitedLinks.get(i) + ", ");
                    p.set(curentNodeUnvisitedLinks.get(i) - 1, selectedNode);
                    t++;
                    t1.set(curentNodeUnvisitedLinks.get(i) - 1, t);
                }
                System.out.println("      => Visited is: " + visited + "; ");
            } else {
                System.out.println("Se elimina din visited nodul: " + visited.peek());
                t++;
                t2.set(visited.peek() - 1, t);
                analyzed.add(visited.peek());
                //pentru ca in metoda de calcul a componentelor Conexe este posibil ca metoda DFS sa fie
                // apelata de mai multe ori (practic de tot atatea oric cate componente conexe tari exista in graf)
                // s-a introdus aceasta variabila globala care salveaza toate Nodurile analizate dupa fiecare iteratie
                analyzedGlobally.push(visited.peek());
                visited.pop();
            }
            System.out.print("=> Visited is: " + visited + "; ");
            System.out.print("Unvisited is: " + unvisited + "; ");
            System.out.print("Analyzed is: " + analyzed + "; ");
            System.out.println();
        }

        List<Integer> temp = new ArrayList<>();
        temp.addAll(analyzed);
        componenteConexe.add(temp);
        analyzed.clear();


        System.out.println("\nSunt un numar de " + componenteConexe.size() + " componente conexe");
        for (int i = 0; i < componenteConexe.size(); i++) {
            System.out.print("- Componenta conexa "+(i+1)+": ");
            for (int j = 0; j < componenteConexe.get(i).size(); j++) {
                System.out.print(componenteConexe.get(i).get(j) + ", ");
            }
            System.out.println();
        }

        unvisited.clear();
        analyzed.clear();
    }

}
