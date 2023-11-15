package graphImplementation.adjacencyListActivity;

import graphImplementation.*;

import java.io.FileNotFoundException;

/**
 *  
 */
public class AdjacencyListSymbolGraph extends SymbolGraph {

    public AdjacencyListSymbolGraph(String filename, String delimiter) throws FileNotFoundException {
        super(filename, delimiter);
    }

    public Graph createGraph(int v) {
        return new AdjacencyListGraph(v);
    }
    }
