import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Graph
{

    private List<Node> nodeList = new ArrayList<>();

    public Graph() {
    }

    public Graph(List<Node> nodeList)
    {
        this.nodeList = nodeList;
    }

    public void addNode(Node node)
    {
        nodeList.add(node);

    }

    public List<Integer> getLinks(Integer node)
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
            System.out.println("-------------------");
        }
    }

    public void parcurgereGenerala(Integer startNode)
    {
        List<Integer> unvizited = new ArrayList<>();
        List<Integer> p = new ArrayList<>();
        List<Integer> o = new ArrayList<>();
        for (Node node:nodeList)
        {
            unvizited.add(node.getNodeNumber());
            p.add(0);
            o.add(-1);
        }
        unvizited.remove(startNode);
    //    System.out.println(unvizited);

        List<Integer> vizited = new ArrayList<>();
        vizited.add(startNode);
      //  System.out.println(vizited);
        List<Integer> analized = new ArrayList<>();
        System.out.println(p);

        Integer k;
        k=1; o.set(startNode-1,1);

        Integer selectedNode;
        while (!vizited.isEmpty())
        {
            selectedNode = vizited.get(0);

            List<Integer> selectedNodeLinks= getLinks(selectedNode);


            if (!selectedNodeLinks.isEmpty())
            {
                k++;
                for (int i = 0; i < selectedNodeLinks.size(); i++)
                {
                    if (unvizited.contains(selectedNodeLinks.get(i)))
                    {
                    unvizited.remove(selectedNodeLinks.get(i));
                    vizited.add(selectedNodeLinks.get(i));
                    p.set(selectedNodeLinks.get(i)-1,selectedNode);
                    o.set(selectedNodeLinks.get(i)-1,k);
                    }

                }
            }
            vizited.remove(selectedNode);
            analized.add(selectedNode);
        }

        System.out.println("And the result is: ");
        System.out.println(p);
        System.out.println("order is: ");
        System.out.println(o);
    }

}
