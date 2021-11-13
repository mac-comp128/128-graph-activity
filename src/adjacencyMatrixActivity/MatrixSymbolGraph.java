package adjacencyMatrixActivity;
/**
 *  The {@code MatrixSymbolGraph} class represents an undirected graph, where the
 *  vertex names are arbitrary strings.
 *  By providing mappings between string vertex names and integers,
 *  it serves as a wrapper around the
 *  {@link AdjacencyMatrixGraph} data type, which assumes the vertex names are integers
 *  between 0 and <em>V</em> - 1.
 *  It also supports initializing a symbol graph from a file.
 *  <p>
 *  This implementation uses a TreeMap to map from strings to integers,
 *  an array to map from integers to strings, and a {@link AdjacencyMatrixGraph} to store
 *  the underlying graph.
 *  The <em>indexOf</em> and <em>contains</em> operations take time
 *  proportional to log <em>V</em>, where <em>V</em> is the number of vertices.
 *  The <em>nameOf</em> operation takes constant time.
 *  <p>
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *
 *  EK: Modified to use a TreeMap and Scanner
 */

import adjacencyListActivity.SymbolGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;

public class MatrixSymbolGraph {
    private TreeMap<String, Integer> st;  // string -> index
    private String[] keys;           // index  -> string
    private AdjacencyMatrixGraph graph;             // the underlying graph

    /**
     * Initializes a graph from a file using the specified delimiter.
     * Each line in the file contains
     * the name of a vertex, followed by a list of the names
     * of the vertices adjacent to that vertex, separated by the delimiter.
     *
     * @param filename  the name of the file
     * @param delimiter the delimiter between fields
     */
    public MatrixSymbolGraph(String filename, String delimiter) throws FileNotFoundException {
        st = new TreeMap<String, Integer>();

        // First pass builds the index by reading strings to associate
        // distinct strings with an index
        Scanner in = new Scanner(new File(filename));
        // while (in.hasNextLine()) {
        while (in.hasNext()) {
            String[] a = in.nextLine().split(delimiter);
            for (int i = 0; i < a.length; i++) {
                if (!st.containsKey(a[i]))
                    st.put(a[i], st.size());
            }
        }

        // inverted index to get string keys in an array
        keys = new String[st.size()];
        for (String name : st.keySet()) {
            keys[st.get(name)] = name;
        }

        // second pass builds the graph by connecting first vertex on each
        // line to all others
        graph = new AdjacencyMatrixGraph(st.size());
        in = new Scanner(new File(filename));
        while (in.hasNextLine()) {
            String[] a = in.nextLine().split(delimiter);
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                int w = st.get(a[i]);
                graph.addEdge(v, w);
            }
        }
    }

    /**
     * Does the graph contain the vertex named {@code s}?
     *
     * @param s the name of a vertex
     * @return {@code true} if {@code s} is the name of a vertex, and {@code false} otherwise
     */
    public boolean contains(String s) {
        return st.containsKey(s);
    }


    /**
     * Returns the integer associated with the vertex named {@code s}.
     *
     * @param s the name of a vertex
     * @return the integer (between 0 and <em>V</em> - 1) associated with the vertex named {@code s}
     */
    public int indexOf(String s) {
        return st.get(s);
    }


    /**
     * Returns the name of the vertex associated with the integer {@code v}.
     *
     * @param v the integer corresponding to a vertex (between 0 and <em>V</em> - 1)
     * @return the name of the vertex associated with the integer {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public String nameOf(int v) {
        validateVertex(v);
        return keys[v];
    }


    /**
     * Returns the graph associated with the symbol graph. It is the client's responsibility
     * not to mutate the graph.
     *
     * @return the graph associated with the symbol graph
     */
    public AdjacencyMatrixGraph graph() {
        return graph;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = graph.V();
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }
    /**
     * Unit tests the {@code MatrixSymbolGraph} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) throws FileNotFoundException  {
        String filepath = MatrixSymbolGraph.class.getResource("/routes.txt").getPath();
        MatrixSymbolGraph sg = new MatrixSymbolGraph(filepath, " ");
        AdjacencyMatrixGraph graph = sg.graph();
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter an airport code:");
        String source = scan.nextLine();
        while (!source.equalsIgnoreCase("quit")) {
            if (sg.contains(source)) {
                int s = sg.indexOf(source);
                for (int v : graph.adj(s)) {
                    System.out.println("   " + sg.nameOf(v));
                }
            }
            else {
                System.out.println("Graph does not contain '" + source + "'");
            }
            System.out.print("Enter an airport code:");
            source = scan.nextLine();

        }
    }
}