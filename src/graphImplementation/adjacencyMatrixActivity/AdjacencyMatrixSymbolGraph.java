package graphImplementation.adjacencyMatrixActivity;

import graphImplementation.*;

import java.io.FileNotFoundException;

/**
 *  
 */
public class AdjacencyMatrixSymbolGraph extends SymbolGraph {

    public AdjacencyMatrixSymbolGraph(String filename, String delimiter) throws FileNotFoundException {
        super(filename, delimiter);
    }

    public Graph createGraph(int v) {
        return new AdjacencyMatrixGraph(v);
    }
    }
