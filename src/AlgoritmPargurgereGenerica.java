import com.sun.istack.internal.localization.NullLocalizable;

import java.util.ArrayList;
import java.util.Arrays;

public class AlgoritmPargurgereGenerica {

    public static void main(String[] args)
    {
        Graph graph = new Graph();

        Node node1 = new Node(1, Arrays.asList(2,3));
        Node node2 = new Node(2, Arrays.asList(3,4));
        Node node3 = new Node(3, Arrays.asList(2,5));
        Node node4 = new Node(4, Arrays.asList());
        Node node5 = new Node(5, Arrays.asList(2,4));
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        graph.addNode(node5);

        graph.printGraph();


        graph.parcurgereGenerala(1);

  //      System.out.println(graph.getLinks(2));

    }
}
