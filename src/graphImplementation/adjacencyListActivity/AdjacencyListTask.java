package graphImplementation.adjacencyListActivity;

import graphImplementation.Graph;

/**
 * A runnable task used to test the timing of a graph. The tasks in the run method are fairly arbitrary, but represent
 * calls to access edges and vertices in the graph.
 */
public class AdjacencyListTask implements Runnable {
    private final AdjacencyListSymbolGraph graph;

    public AdjacencyListTask(AdjacencyListSymbolGraph sgraph) {
        graph = sgraph;
    }

    @Override
    public void run() {
        Graph matrix = graph.graph();
        int s = graph.indexOf("DEN");
        for(Integer i : matrix.adj(s)){
            graph.nameOf(i);
        }
    }
}
