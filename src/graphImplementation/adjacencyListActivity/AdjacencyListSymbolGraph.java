package graphImplementation.adjacencyListActivity;

import graphImplementation.*;

import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

/**
 *  
 */
public class AdjacencyListSymbolGraph extends SymbolGraph {

    /**
     * Initializes a graph from a file using the specified delimiter.
     * Each line in the file contains
     * the name of a vertex, followed by a list of the names
     * of the vertices adjacent to that vertex, separated by the delimiter.
     *
     * @param filename  the name of the file
     * @param delimiter the delimiter between fields
     */
    public AdjacencyListSymbolGraph(String filename, String delimiter) throws FileNotFoundException {
        super(filename, delimiter);
    }

    /**
     * Generates the internal AdjacencyListGraph representation. 
     *
     * @param v the number of vertices in the graph
     */
    public Graph createGraph(int v) {
        return new AdjacencyListGraph(v);
    }

    /**
     * Tests the {@code AdjacencyListSymbolGraph} data type in the command line.
     *
     * @param args command line arguments
     * @throws FileNotFoundException
     * @throws URISyntaxException
     */
     public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
        URI uri = AdjacencyListSymbolGraph.class.getResource("/routes.txt").toURI();
        String path = Paths.get(uri).toAbsolutePath().toString();
        SymbolGraph sg = new AdjacencyListSymbolGraph(path, " ");
        test(sg);
    }
}
