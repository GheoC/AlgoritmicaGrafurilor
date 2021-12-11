import Model.Graph;
import Model.Node;
import Service.GraphService;

import java.util.Arrays;

public class MainComponenteConexeTari
{
    public static void main(String[] args)
    {
        GraphService graphService = new GraphService();

        //Este graful din figura 2.33
        Node n1 = new Node(1, Arrays.asList(2, 3, 5));
        Node n2 = new Node(2, Arrays.asList(3, 5));
        Node n3 = new Node(3, Arrays.asList(4, 5));
        Node n4 = new Node(4, Arrays.asList(2, 5));
        Node n5 = new Node(5, Arrays.asList());
        Node n6 = new Node(6, Arrays.asList(4, 7));
        Node n7 = new Node(7, Arrays.asList(4, 5, 6));

        Graph graph2 = new Graph();
        graph2.addNode(n1);
        graph2.addNode(n2);
        graph2.addNode(n3);
        graph2.addNode(n4);
        graph2.addNode(n5);
        graph2.addNode(n6);
        graph2.addNode(n7);

        System.out.println("\nGraful este: ");
        graph2.printGraph();

        System.out.println("\nGraful inversat este: ");
        Graph g2Inversat = graphService.inversareGraph(graph2);
        g2Inversat.printGraph();

        graphService.calculateComponenteConexeTari(graph2,1);

    }
}
