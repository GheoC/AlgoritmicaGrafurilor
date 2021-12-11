package Service;

import Model.Graph;
import Model.GraphData;
import Model.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class GraphService {

    private List<Integer> unvisited = new ArrayList<>();
    private List<Integer> visited = new ArrayList<>();
    private List<Integer> analyzed = new ArrayList<>();
    private List<Integer> analyzedForComponenteConexeTari = new ArrayList<>();

    public GraphService() {
    }
//  Parcurgere Generica:

    public void parcurgereGenerica(Graph graph, Integer startNode) {
        List<Integer> p = new ArrayList<>();
        List<Integer> o = new ArrayList<>();
        for (Node node : graph.getNodes()) {
            unvisited.add(node.getNodeNumber());
            p.add(0);
            o.add(-1);
        }
        unvisited.remove(startNode);
        visited.add(startNode);

        Integer k = 1;
        o.set(startNode - 1, k);
        Integer selectedNode;
        List<Integer> curentNodeUnvisitedLinks = new ArrayList<>();

        while (!visited.isEmpty()) {
            Collections.shuffle(visited);
            selectedNode = visited.get(0);

            List<Integer> listOfLinkedNodes = graph.getLinksForNode(selectedNode);

            //In acest for se selecteaza doar nodurile nevizitate
            curentNodeUnvisitedLinks.clear();
            for (int i = 0; i < graph.getLinksForNode(selectedNode).size(); i++) {
                if (!(visited.contains(listOfLinkedNodes.get(i)) || analyzed.contains(listOfLinkedNodes.get(i)))) {
                    curentNodeUnvisitedLinks.add(listOfLinkedNodes.get(i));
                }
            }

            System.out.print("Ne-am pozitionat pe nodul: " + selectedNode + " => legaturile nevizitate sunt: " + curentNodeUnvisitedLinks + "; ");

            if (!curentNodeUnvisitedLinks.isEmpty()) {
                Collections.shuffle(curentNodeUnvisitedLinks);
                visited.add(curentNodeUnvisitedLinks.get(0));
                System.out.println("Din lista de adiacenta l-am selectat pe: " + curentNodeUnvisitedLinks.get(0) + " =>");
                unvisited.remove(curentNodeUnvisitedLinks.get(0));
                p.set(curentNodeUnvisitedLinks.get(0) - 1, selectedNode);
                k++;
                o.set(curentNodeUnvisitedLinks.get(0) - 1, k);
                System.out.println("Lista de precendenta este " + p);
                System.out.println("Lista de ordine este: " + o);
            } else {
                visited.remove(selectedNode);
                analyzed.add(selectedNode);
            }
            System.out.print("Vizited: " + visited + "; ");
            System.out.print("Unvisited: " + unvisited + "; ");
            System.out.println("Analyzed: " + analyzed + "; ");
            System.out.println();
        }
        System.out.println("Ordinea parcursa in graph este: ");
        System.out.println(o);
        System.out.println("Lista de predecesori este: ");
        System.out.println(p);

        unvisited.clear();
        visited.clear();
        analyzed.clear();
    }

    //  Parcurgere Totala Generica :
    public void parcurgereGenericaTotala(Graph graph, Integer startNode) {

        List<Integer> p = new ArrayList<>();
        List<Integer> o = new ArrayList<>();
        for (Node node : graph.getNodes()) {
            unvisited.add(node.getNodeNumber());
            p.add(0);
            o.add(-1);
        }
        unvisited.remove(startNode);
        visited.add(startNode);

        Integer k = 1;
        o.set(startNode - 1, k);
        Integer selectedNode;
        List<Integer> curentNodeUnvisitedLinks = new ArrayList<>();

        //pentru componente conexe
        List<List<Integer>> componenteConexe = new ArrayList<>();

        Integer analyseCounter = 0;
        while (analyseCounter != graph.getNodes().size()) {
            while (!visited.isEmpty()) {
                Collections.shuffle(visited);
                selectedNode = visited.get(0);

                List<Integer> listOfLinkedNodes = graph.getLinksForNode(selectedNode);

                //In acest for se selecteaza doar nodurile nevizitate
                curentNodeUnvisitedLinks.clear();
                for (int i = 0; i < graph.getLinksForNode(selectedNode).size(); i++) {
                    if (!(visited.contains(listOfLinkedNodes.get(i)) || analyzed.contains(listOfLinkedNodes.get(i)))) {
                        curentNodeUnvisitedLinks.add(listOfLinkedNodes.get(i));
                    }
                }

                System.out.print("Ne-am pozitionat pe nodul: " + selectedNode + " => legaturile nevizitate sunt: " + curentNodeUnvisitedLinks + "; ");

                if (!curentNodeUnvisitedLinks.isEmpty()) {
                    Collections.shuffle(curentNodeUnvisitedLinks);
                    visited.add(curentNodeUnvisitedLinks.get(0));
                    System.out.println("Din lista de adiacenta l-am selectat pe: " + curentNodeUnvisitedLinks.get(0) + " =>");
                    unvisited.remove(curentNodeUnvisitedLinks.get(0));
                    p.set(curentNodeUnvisitedLinks.get(0) - 1, selectedNode);
                    k++;
                    o.set(curentNodeUnvisitedLinks.get(0) - 1, k);
                    System.out.println("Lista de precendenta este " + p);
                    System.out.println("Lista de ordine este: " + o);
                } else {
                    visited.remove(selectedNode);
                    analyzed.add(selectedNode);
                    analyseCounter++;
                }
                System.out.print("Vizited: " + visited + "; ");
                System.out.print("Unvisited: " + unvisited + "; ");
                System.out.println("Analyzed: " + analyzed + "; ");
                System.out.println();
            }

            //adaugam componentul conex in lista de componente conexe
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < analyzed.size(); i++) {
                temp.add(analyzed.get(i));
            }
            componenteConexe.add(temp);
            analyzed.clear();

            if (!unvisited.isEmpty()) {
                selectedNode = unvisited.get(0);
                unvisited.remove(selectedNode);
                visited.add(selectedNode);
                k++;
                o.set(selectedNode - 1, k);
            }
        }
        System.out.println("Ordinea parcursa in graph este: ");
        System.out.println(o);
        System.out.println("Lista de predecesori este: ");
        System.out.println(p);
        System.out.println("Sunt un numar de " + componenteConexe.size() + " componente conexe");

        for (int i = 0; i < componenteConexe.size(); i++) {
            for (int j = 0; j < componenteConexe.get(i).size(); j++) {
                System.out.print(componenteConexe.get(i).get(j) + ", ");
            }
            System.out.println();
        }
        unvisited.clear();
        visited.clear();
        analyzed.clear();
    }

    //parcurgere totala Breadth-first search
    public void breadthFirstSearch(Graph graph, Integer startNode) {

        List<Integer> p = new ArrayList<>();
        List<Integer> l = new ArrayList<>();
        for (Node node : graph.getNodes()) {
            unvisited.add(node.getNodeNumber());
            p.add(0);
            l.add(0);
        }
        unvisited.remove(startNode);
        visited.add(startNode);

        l.set(startNode - 1, 0);
        Integer selectedNode;
        System.out.print("Unvisited is: " + unvisited + "; ");
        System.out.print("Visited is: " + visited + "; ");
        System.out.println();
        Integer analyseCounter = 0;
        List<List<Integer>> componenteConexe = new ArrayList<>();

        while (analyseCounter != graph.getNodes().size()) {
            while (!visited.isEmpty()) {

                //pentru ca tot timpul selectam primul element din visited, nu a fost nevoie sa folosesc o coada
                selectedNode = visited.get(0);
                List<Integer> selectedNodeLinks = graph.getLinksForNode(selectedNode);
                System.out.println("\nNe-am pozitionat pe nodul: " + selectedNode + "  => legaturile sunt:" + selectedNodeLinks);
                if (!selectedNodeLinks.isEmpty()) {
                    System.out.print("Se adauga in visited urmatoarele noduri: ");
                    for (int i = 0; i < selectedNodeLinks.size(); i++) {
                        if (unvisited.contains(selectedNodeLinks.get(i))) {
                            unvisited.remove(selectedNodeLinks.get(i));
                            visited.add(selectedNodeLinks.get(i));
                            System.out.print(selectedNodeLinks.get(i) + ", ");
                            p.set(selectedNodeLinks.get(i) - 1, selectedNode);
                            l.set(selectedNodeLinks.get(i) - 1, (l.get(selectedNode - 1) + 1));
                        }
                    }
                    System.out.println("      => Visited is: " + visited + "; ");
                }
                visited.remove(selectedNode);
                System.out.println("Se elimina " + selectedNode + " din visited si se adauga in analysed");
                System.out.print("=> Visited is: " + visited + "; ");
                analyzed.add(selectedNode);
                analyseCounter++;
                System.out.print("Unvisited is: " + unvisited + "; ");
                System.out.print("Analyzed is: " + analyzed + "; ");
                System.out.println();
            }
            //adaugam componentul conex in lista de componente conexe
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < analyzed.size(); i++) {
                temp.add(analyzed.get(i));
            }
            componenteConexe.add(temp);
            analyzed.clear();
            if (!unvisited.isEmpty()) {
                selectedNode = unvisited.get(0);
                unvisited.remove(selectedNode);
                visited.add(selectedNode);
                l.set(selectedNode - 1, 0);
            }
        }
        System.out.println("Lista de predecesori este: ");
        System.out.println(p);
        System.out.println("Tabloul de lungime este: ");
        System.out.println(l);

        System.out.println("\nSunt un numar de " + componenteConexe.size() + " componente conexe");
        for (int i = 0; i < componenteConexe.size(); i++) {
            System.out.print("- Componenta conexa "+(i+1)+": ");
            for (int j = 0; j < componenteConexe.get(i).size(); j++) {
                System.out.print(componenteConexe.get(i).get(j) + ", ");
            }
            System.out.println();
        }
        unvisited.clear();
        visited.clear();
        analyzed.clear();
    }

    //parcurgere totala Depth-first search + componente Conexe
    public GraphData depthFirstSearchTotal(Graph graph, Integer startNode)
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

        Integer analyseCounter = 0;

        while (analyseCounter != graph.getNodes().size()) {
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
                    analyseCounter++;
                    visited.pop();
                }
                System.out.print("=> Visited is: " + visited + "; ");
                System.out.print("Unvisited is: " + unvisited + "; ");
                System.out.print("Analyzed is: " + analyzed + "; ");
                System.out.println();
            }
            //adaugam nodurile analizate in lista de componente conexe
            List<Integer> temp = new ArrayList<>();
            temp.addAll(analyzed);
            componenteConexe.add(temp);
            analyzed.clear();

            if (!unvisited.isEmpty()) {
                selectedNode = unvisited.get(0);
                unvisited.remove(selectedNode);
                visited.push(selectedNode);
                t++;
                t1.set(selectedNode - 1, t);
            }
        }
        System.out.println("Lista de predecesori este: ");
        System.out.println(p);
        System.out.println("Tabloul t1 este: ");
        System.out.println(t1);
        System.out.println("Tabloul t2 este: ");
        System.out.println(t2);

        System.out.println("\nSunt un numar de " + componenteConexe.size() + " componente conexe");
        for (int i = 0; i < componenteConexe.size(); i++) {
            System.out.print("- Componenta conexa "+(i+1)+": ");
            for (int j = 0; j < componenteConexe.get(i).size(); j++) {
                System.out.print(componenteConexe.get(i).get(j) + ", ");
            }
            System.out.println();
        }
        System.out.println("******************************************************************************");

        unvisited.clear();
        analyzed.clear();

        GraphData graphData = new GraphData();
        graphData.setPredecesor(p);
        graphData.setT1(t1);
        graphData.setT2(t2);
        graphData.setComponenteConexe(componenteConexe);
        return graphData;
    }

    public Graph inversareGraph(Graph graph) {
        Graph graphInversat = new Graph();

        for (int i = 0; i < graph.getNodes().size(); i++) {
            Node node = new Node();
            node.setNodeNumber(graph.getNodes().get(i).getNodeNumber());
            graphInversat.getNodes().add(node);
        }

        for (int i = 0; i < graph.getNodes().size(); i++) {
            for (int j = 0; j < graph.getNodes().get(i).getLinks().size(); j++) {
                Integer currentNode = graph.getNodes().get(i).getLinks().get(j);
                graphInversat.getNodes().get(currentNode - 1).getLinks().add(i + 1);
            }
        }
        return graphInversat;
    }

    public void calculateComponenteConexeTari(Graph graph, Integer startNode) {

        //se extrage tabela T2 din datele furnizate de metoda depthFirstSearch(graph, startNode)
        System.out.println("Aflam tabela t2 a graficului original: ");
        List<Integer> t2 = depthFirstSearchTotal(graph, startNode).getT2();

        //se inverseaza graficul
        Graph graphInvers = inversareGraph(graph);
        analyzedForComponenteConexeTari.clear();

        List<Integer> nodes = new ArrayList<>();
        for (int i = 0; i < graph.getNodes().size(); i++) {
            nodes.add(graph.getNodes().get(i).getNodeNumber());
        }

        //Se creaza o lista pentru a stoca separat listele nodurile ce formeaza componente conexe tari
        List<List<Integer>> componenteConexeTari = new ArrayList<>();

        //pentru a pastra intacta ordinea indicilor din tabela t2 se creaza o tabela ce va fi in continuare manipulata,
        List<Integer> tempT2 = new ArrayList<>();
        tempT2.addAll(t2);

        //aplicam DPS cat timp mai sunt inca noduri pentur care nu am identificat Componenta Conexe

        Integer iteratia = 1;
        while (!nodes.isEmpty()) {

            System.out.println("\n================ Iteratia DFS numarul "+iteratia+" ===================");
            iteratia++;

            //se identifica valoarea maxima din tabela tempT2
            Integer maxNumber = getMaxValueFrom(tempT2);

            //se identifica indicele din tabela originala t2 pentru numarul maxim gasit anterior
            Integer s = returnIndexOfNumber(t2, maxNumber);
            System.out.println("\nIndicele cu valoarea maxima din tabela T2 este: " + s+ " cu valoarea "+maxNumber);

            //se ruleaza DFS (simplu) pentru a gasi subgraful cu pornire din s (s fiind indicele cu valoarea maxima
            // din tabela t2)
            GraphData graphDataForInverseGraph = DFSComponenteConexeTari(graphInvers, s);

            // dupa ce am extras Componenta Conexa Tare cu plecare din nodul S, se elimina toate nodurile
            // ce formeaza aceasta Componenta Conexa Tare;
            // OBS: mai jos fiind un DFS simplu, in lista de de Componente Conexe este doar un subgraf si anume cel cu pornire din s
            nodes.removeAll(graphDataForInverseGraph.getComponenteConexe().get(0));

            //se stocheza Componenta Conexa Tare in lista de definita anterior
            componenteConexeTari.add(graphDataForInverseGraph.getComponenteConexe().get(0));
            Integer index;

            //se elimina din tabele tempT2 acei indici stocati in componenta Conexa cu plecare din s
            // t2 ramane nemodificat pentru a pastra ordinea indicilor intacta

            System.out.print("Se elimina din tabela T2 urmatorii indici: ");
            for (int i = 0; i < graphDataForInverseGraph.getComponenteConexe().get(0).size(); i++)
            {
                index = graphDataForInverseGraph.getComponenteConexe().get(0).get(i) - 1;
                Integer value = t2.get(index);
                tempT2.remove(value);
                System.out.print((index+1)+", ");
            }
            System.out.println("\n******************************************************************************");
        }

        analyzedForComponenteConexeTari.clear();

        System.out.println("\nComponentele Conexe Tari sunt:");
        for (int i = 0; i < componenteConexeTari.size(); i++) {
            System.out.println(" - Componenta nr."+(i+1)+": "+componenteConexeTari.get(i));
        }
    }

    private Integer getMaxValueFrom(List<Integer> numbers) {
        Integer max = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i) > max) {
                max = numbers.get(i);
            }
        }
        return max;
    }

    private Integer returnIndexOfNumber(List<Integer> numbers, Integer number) {
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) == number) {
                return (i + 1);
            }
        }
        return null;
    }

    private GraphData DFSComponenteConexeTari(Graph graph, Integer startNode)
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
        unvisited.removeAll(analyzedForComponenteConexeTari);

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

        Integer analyseCounter = 0;

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
                    analyzedForComponenteConexeTari.add(visited.peek());
                    analyseCounter++;
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

        GraphData graphData = new GraphData();
        graphData.setPredecesor(p);
        graphData.setT1(t1);
        graphData.setT2(t2);
        graphData.setComponenteConexe(componenteConexe);
        return graphData;
    }


}
