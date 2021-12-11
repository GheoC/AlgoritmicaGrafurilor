package Model;

import java.util.ArrayList;
import java.util.List;

public class Node
{
    private Integer nodeNumber;
    private List<Integer> links = new ArrayList<>();

    public Node()
    {
    }

    public Node(Integer nodeNumber, List<Integer> links)
    {
        this.nodeNumber = nodeNumber;
        this.links = links;
    }

    public Integer getNodeNumber()
    {
        return nodeNumber;
    }

    public Node setNodeNumber(Integer nodeNumber) {
        this.nodeNumber = nodeNumber;
        return this;
    }

    public List<Integer> getLinks() {
        return links;
    }

    public Node setLinks(List<Integer> links) {
        this.links = links;
        return this;
    }
}
