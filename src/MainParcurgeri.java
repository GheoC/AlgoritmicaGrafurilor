import Model.Graph;
import Model.Node;
import Service.GraphService;

import java.util.Arrays;

public class MainParcurgeri {

    public static void main(String[] args) {
        Graph graph = new Graph();

        Node node1 = new Node(1, Arrays.asList(2, 3, 5));
        Node node2 = new Node(2, Arrays.asList(5));
        Node node3 = new Node(3, Arrays.asList(4, 7, 8));
        Node node4 = new Node(4, Arrays.asList(5, 6, 7));
        Node node5 = new Node(5, Arrays.asList());
        Node node6 = new Node(6, Arrays.asList(5));
        Node node7 = new Node(7, Arrays.asList(5));
        Node node8 = new Node(8, Arrays.asList(4));
        Node node9 = new Node(9, Arrays.asList(10));
        Node node10 = new Node(10, Arrays.asList(9));
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        graph.addNode(node5);
        graph.addNode(node6);
        graph.addNode(node7);
        graph.addNode(node8);
        graph.addNode(node9);
        graph.addNode(node10);

        GraphService graphService = new GraphService();
        graphService.parcurgereGenerica(graph,1);
        graphService.parcurgereGenericaTotala(graph, 1);
        graphService.breadthFirstSearch(graph, 1);
        graphService.depthFirstSearchTotal(graph,1);

    }

}
