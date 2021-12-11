import Model.Graph;
import Model.Node;
import Service.GraphService;
import Service.SortareTopologica;

import java.util.Arrays;

public class MainSortareTopologica
{

    public static void main(String[] args)
    {
        GraphService graphService = new GraphService();

        //Este graful din figura 2.38
        Node nodeSortTopo1 = new Node(1, Arrays.asList(2,3));
        Node nodeSortTopo2 = new Node(2, Arrays.asList(3));
        Node nodeSortTopo3 = new Node(3, Arrays.asList());
        Node nodeSortTopo4 = new Node(4, Arrays.asList(2,3,5));
        Node nodeSortTopo5 = new Node(5, Arrays.asList(2,3));
        Node nodeSortTopo6 = new Node(6, Arrays.asList(4,5));

        Graph graph = new Graph();
        graph.addNode(nodeSortTopo1);
        graph.addNode(nodeSortTopo2);
        graph.addNode(nodeSortTopo3);
        graph.addNode(nodeSortTopo4);
        graph.addNode(nodeSortTopo5);
        graph.addNode(nodeSortTopo6);

        Graph graphInversat = graphService.inversareGraph(graph);


        SortareTopologica sortareTopologica = new SortareTopologica();


        sortareTopologica.sortTopo(graph);

    }
}
