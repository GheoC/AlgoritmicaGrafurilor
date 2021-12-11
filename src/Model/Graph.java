package Model;

import java.util.ArrayList;
import java.util.List;

public class Graph
{

    private List<Node> nodeList = new ArrayList<>();

    public Graph() {
    }

    public Graph(List<Node> nodeList)
    {
        this.nodeList = nodeList;
    }

    public List<Node> getNodes() {
        return nodeList;
    }

    public void setNodeList(List<Node> nodeList) {
        this.nodeList = nodeList;
    }

    public void addNode(Node node)
    {
        nodeList.add(node);
    }

    public List<Integer> getLinksForNode(Integer node)
    {
        for (int i = 0; i <nodeList.size(); i++)
        {
            if (node== nodeList.get(i).getNodeNumber())
            {
             return nodeList.get(i).getLinks();
            }
        }
    return null;
    }

    public void printGraph()
    {
        for (Node node:nodeList)
        {
            System.out.print(node.getNodeNumber()+": ");
            System.out.println(node.getLinks());
        }
        System.out.println("-------------------");
    }
}
