package graphImplementation;

import java.io.FileNotFoundException;
import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;

import graphImplementation.adjacencyListActivity.*;
import graphImplementation.adjacencyMatrixActivity.*;

/**
 * Class used to analyze the time and space requirements of different graph implementations
 */
public class Analyzer {
    private static final double MIN_TASK_TEST_TIME = 0.1;  // seconds
    private static final int MIN_REPS = 10;
    private static final int MAX_REPS = 1000000;

    /**
     * Runs the given task repeatedly and times it. This method will initially call task.run() MIN_REPS times,
     * then keep running it in larger and larger groups of repetitions until either MIN_TASK_TEST_TIME has elapsed,
     * or we have run it MAX_REPS. This helps ensure that we get a good average to help eliminate measurement error
     * for very fast tasks, while not making the performance test take inordinate amounts of time for slow ones.
     *
     * @return The average time per call to task.run(), in microseconds.
     */
    private double timeTask(Runnable task) {
        long start = System.nanoTime(), lastClockMeasurement = start;

        long reps = 0;
        long timingChunk = MIN_REPS;
        long minTaskTestTimeNanos = Math.round(MIN_TASK_TEST_TIME * 1_000_000_000);

        while(lastClockMeasurement - start <= minTaskTestTimeNanos && reps < MAX_REPS) {
            for(long n = 0; n < timingChunk; n++ ) {
                task.run();
            }
            lastClockMeasurement = System.nanoTime();
            reps += timingChunk;
            timingChunk = timingChunk * 3 / 2;  // Think: why shouldn’t we do this?  timingChunk *= 3 / 2
        }
        double time = (double) (lastClockMeasurement - start) / reps / 1000;
        System.out.format("    %9.3f µs  (%d reps)\n", time, reps);
        return time;
    }



    public static void main(String[] args)  throws FileNotFoundException {
        String filepath = AdjacencyMatrixSymbolGraph.class.getResource("/routes.txt").getPath();
        Analyzer analyzer = new Analyzer();

        VM.current().details();
        System.out.println();
        System.out.println("================= Adjacency Matrix Graph ================");
        AdjacencyMatrixSymbolGraph msg = new AdjacencyMatrixSymbolGraph(filepath, " ");
        System.out.println(GraphLayout.parseInstance(msg).toFootprint());
   
        AdjacencyMatrixTask mTask = new AdjacencyMatrixTask(msg);
        System.out.print("Time:");
        analyzer.timeTask(mTask);

        System.out.println();
        System.out.println("================= Adjacency List Graph ================");
        AdjacencyListSymbolGraph alsg = new AdjacencyListSymbolGraph(filepath, " ");
        System.out.println(GraphLayout.parseInstance(alsg).toFootprint());
   
        AdjacencyListTask lTask = new AdjacencyListTask(alsg);
        System.out.print("Time:");
        analyzer.timeTask(lTask);

    }
}
