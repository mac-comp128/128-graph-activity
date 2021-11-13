package adjacencyMatrixActivity;

import adjacencyListActivity.Graph;
import adjacencyListActivity.SymbolGraph;

/**
 * A runnable task used to test the timing of a graph. The tasks in the run method are fairly arbitrary, but represent
 * calls to access edges and vertices in the graph.
 */
public class AdjacencyListTask implements Runnable {
    private final SymbolGraph graph;

    AdjacencyListTask(SymbolGraph sgraph) {
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
