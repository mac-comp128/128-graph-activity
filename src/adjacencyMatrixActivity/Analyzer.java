package adjacencyMatrixActivity;

import adjacencyListActivity.Graph;
import adjacencyListActivity.SymbolGraph;

import java.io.FileNotFoundException;

/**
 * Class used to analyze the time and space requirements of different graph implementations
 */
public class Analyzer {
    private static long SLEEP_INTERVAL = 100;
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


    /**
     * Returns the amount of memory currently used by all Java programs.
     * @return currently used memory in bytes.
     */
    public long getMemoryUse(){
        putOutTheGarbage();
        long totalMemory = Runtime.getRuntime().totalMemory();
        putOutTheGarbage();
        long freeMemory = Runtime.getRuntime().freeMemory();
        return (totalMemory - freeMemory);
    }

    /**
     * Run the java garbage collector to delete unused memory
     */
    private void putOutTheGarbage() {
        collectGarbage();
        collectGarbage();
    }

    private void collectGarbage() {
        try {
            System.gc();
            Thread.sleep(SLEEP_INTERVAL);
            System.runFinalization();
            Thread.sleep(SLEEP_INTERVAL);
        }
        catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }

    public static void main(String[] args)  throws FileNotFoundException {
        String filepath = MatrixSymbolGraph.class.getResource("/routes.txt").getPath();
        Analyzer analyzer = new Analyzer();

        //TODO: 1. Get the current number of bytes of memory currently used from the analyzer.
        // 2. Create a Matrix Symbol graph.
        // 3. Get the new current amount of memory used.
        // 4. Subtract this amount from the original amount to get the memory size of the graph. Print this result.
        // Do the same steps 1-4 with a SymbolGraph (adjacency list implementation). Discuss the memory size results with your partner.


        //TODO: Create an instance of Adjacency Matrix Task and call timeTask with it.
        // Do the same thing with an Adjacency List Task. Discuss the timing results with your partner

    }
}
