
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kenji Fam
 */
public class Graph {
    
    private int nodes;
    private AdjList adjList[];
    
    private static class AdjList {
        private Node next;

        public AdjList() {
            next = null;
        }
    }
    
    private static class Node {
        private int V;
        private Node next;

        public Node(int V, Node next)  {
            this.V  = V;
            this.next = next;
        }
    }

    public Graph() {
        nodes = 0;
        adjList = new AdjList[nodes];
    }

    /* tell how many nodes a graph has */
    public int nodes() {
        return nodes;
    }

    /* read a graph from the file */
    public boolean readGraph(File file) {
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                nodes++;
                String[] str = reader.nextLine().split(" ");
                AdjList[] temp = new AdjList[nodes];
                System.arraycopy(adjList, 0, temp, 0, adjList.length);
                temp[nodes - 1] = new AdjList();
                if (str.length > 1) {
                    temp[nodes - 1].next = new Node(Integer.parseInt(str[1]), null);
                    Node node = temp[nodes - 1].next;
                    for (int i = 2; i < str.length; i++) {
                        node.next = new Node(Integer.parseInt(str[i]), null);
                        node = node.next;
                    }
                }
                adjList = temp;
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /* print a graph */
    public void printGraph() {
        for (int i = 0; i < nodes; i++) {
            Node node = adjList[i].next;
            System.out.print(i + ": ");
            while(node != null){
                System.out.print(node.V + " ");
                node = node.next;
            }
            System.out.println();
        }
    }

    /* Depth First Search
    * start is the node from where the search begins
    * visited is an array of all the visited nodes
    * pred is an describing the search path
     */
    public void dfs(int start, boolean visited[], int pred[]) {
        visited[start] = true;
        Node node = adjList[start].next;
        while (node != null) {
            if (!visited[node.V]) {
                pred[node.V] = start;
                dfs(node.V, visited, pred);
            }
            node = node.next;
        }
    }

    /* find a maze solution */
    private static int mazeSolution(int from, int to, int pred[], int steps[]) {
        int i, n, node;
        
        // first count how many edges between the end and the start
        node = to; n = 1;
        while ((node = pred[node]) != from) n++;
        
        // then reverse pred[] array to steps[] array
        node = to; i = n;
        while (i >= 0) {
            steps[i--] = node;
            node = pred[node];
        }
        
        // include also the end vertex
        return (n + 1);
    }
    
    private final static String FILE = "E:/Courses/ICT16SW/y3/p2/maze.grh";
    private final static int FROM = 0;
    private final static int TO = 15;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        Graph g = new Graph();
        
        // read the graph. and do the depth-first search
        System.out.println("Graph Adjacent list");
        g.readGraph(new File(FILE));
        g.printGraph();
        
        boolean visited[] = new boolean[g.nodes()];
        int pred[] = new int[g.nodes()];
        g.dfs(FROM, visited, pred);
        
        // then check if there is a solution by looking from the backwards to the start
        int steps[] = new int[g.nodes()];
        System.out.println("\nMaze solution from " + FROM + " to " + TO);
        int n = mazeSolution(FROM, TO, pred, steps);
        for (int i = 0; i < n; i++) System.out.print(steps[i] + " ");
        System.out.println();
    }
}
