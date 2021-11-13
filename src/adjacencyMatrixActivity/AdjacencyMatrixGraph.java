package adjacencyMatrixActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class AdjacencyMatrixGraph {

    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;
    private int E;
    private int[][] matrix;

    /**
     * Initializes an empty graph with {@code V} vertices and 0 edges.
     * param V the number of vertices
     *
     * @param V number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public AdjacencyMatrixGraph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        matrix = new int[V][V];
        for (int v = 0; v < V; v++) {
            for(int v2 = 0; v2 < V; v2++) {
                matrix[v][v2] = 0;
            }
        }
    }

    /**
     * Initializes a graph from the specified input stream.
     * The format is the number of vertices <em>V</em>,
     * followed by the number of edges <em>E</em>,
     * followed by <em>E</em> pairs of vertices, with each entry separated by whitespace.
     *
     * @param in the input stream
     * @throws IllegalArgumentException if {@code in} is {@code null}
     * @throws IllegalArgumentException if the endpoints of any edge are not in prescribed range
     * @throws IllegalArgumentException if the number of vertices or edges is negative
     * @throws IllegalArgumentException if the input stream is in the wrong format
     */
    public AdjacencyMatrixGraph(Scanner in) {
        if (in == null) throw new IllegalArgumentException("argument is null");
        try {
            this.V = in.nextInt();
            if (V < 0) throw new IllegalArgumentException("number of vertices in a Graph must be nonnegative");

            matrix = new int[V][V];
            for (int v = 0; v < V; v++) {
                for(int v2 = 0; v2 < V; v2++) {
                    matrix[v][v2] = 0;
                }
            }

            int E = in.nextInt();
            if (E < 0) throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");
            for (int i = 0; i < E; i++) {
                int v = in.nextInt();
                int w = in.nextInt();
                validateVertex(v);
                validateVertex(w);
                addEdge(v, w);
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Graph constructor", e);
        }
    }


    /**
     * Returns the number of vertices in this graph.
     *
     * @return the number of vertices in this graph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in this graph.
     *
     * @return the number of edges in this graph
     */
    public int E() {
        return E;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    /**
     * Adds the undirected edge v-w to this graph.
     *
     * @param v one vertex in the edge
     * @param w the other vertex in the edge
     * @throws IllegalArgumentException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
     */
    public void addEdge(int v, int w) {
        //TODO: Complete the addEdge method. Check if both vertices are on the graph.
        // If so, increment the total number of edges
        // Mark that w is adjacent to v in the matrix
        // Mark that v is adjacent to w in the matrix

    }


    /**
     * Returns the vertices adjacent to vertex {@code v}.
     *
     * @param v the vertex
     * @return the vertices adjacent to vertex {@code v}, as an iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public List<Integer> adj(int v) {
        // TODO: Return the vertices adjacent to vertex v. Make sure that v exists
        return null;
    }

    /**
     * Returns the degree of vertex {@code v}.
     *
     * @param v the vertex
     * @return the degree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int degree(int v) {
        // TODO: Return the degree of vertex v. Make sure that v exists

    }


    /**
     * Returns a string representation of this graph.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     * followed by the <em>V</em> adjacency lists
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w = 0; w < V; w++) {
                if (matrix[v][w] == 1) {
                    s.append(w + " ");
                }
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    /**
     * Unit tests the {@code Graph} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        AdjacencyMatrixGraph G = new AdjacencyMatrixGraph(3);
        G.addEdge(1,2);
        G.addEdge(0,2);
        System.out.println(G);
    }
}
