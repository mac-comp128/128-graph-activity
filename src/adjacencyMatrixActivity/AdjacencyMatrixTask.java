package adjacencyMatrixActivity;

/**
 * A runnable task used to test the timing of a graph. The tasks in the run method are fairly arbitrary, but represent
 * calls to access edges and vertices in the graph.
 */
public class AdjacencyMatrixTask implements Runnable {

    private final MatrixSymbolGraph graph;

    AdjacencyMatrixTask(MatrixSymbolGraph mgraph) {
        graph = mgraph;
    }

    @Override
    public void run() {
        AdjacencyMatrixGraph matrix = graph.graph();
        int s = graph.indexOf("DEN");
        for(Integer i : matrix.adj(s)){
            graph.nameOf(i);
        }
    }
}
