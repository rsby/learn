import com.google.caliper.BeforeExperiment;
import com.google.caliper.Benchmark;
import com.google.caliper.Param;
import datastructures.Heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * @author rees.byars
 */
public class InsertRemoveBenchmarks {

    @Param({"1000000"}) private int length;

    @Param({"RANDOM"}) private Distribution distribution;

    private List<Integer> list;
    private Heap<Integer> heap;
    private PriorityQueue<Integer> priorityQueue;
    private Random random = new Random();

    @BeforeExperiment void setUp() throws Exception {

        Integer[] values = distribution.create(length);

        list = new ArrayList<>(Arrays.asList(values));
        Collections.sort(list);

        heap = Heap.minHeap(values);

        priorityQueue = new PriorityQueue<>();
        priorityQueue.addAll(Arrays.asList(values));

    }

    @Benchmark long listRemove(long reps) {
        long dummy = 0;
        for (long i = 0; i < reps; i++) {
            list.remove(0);
            int asInt = random.nextInt();
            int index = Collections.binarySearch(list, asInt);
            if (index >= 0) {
                list.add(index, asInt);
            } else {
                list.add(Math.abs(index) - 1, asInt);
            }
            dummy |= list.get(0);
        }
        return dummy;
    }

    @Benchmark long customHeapRemove(long reps) {
        long dummy = 0;
        for (long i = 0; i < reps; i++) {
            heap.remove();
            heap.insert(random.nextInt());
            dummy |= heap.peek();
        }
        return dummy;
    }

    @Benchmark long javaPriorityQueueRemove(long reps) {
        long dummy = 0;
        for (long i = 0; i < reps; i++) {
            priorityQueue.remove();
            priorityQueue.add(random.nextInt());
            dummy |= priorityQueue.peek();
        }
        return dummy;
    }

}
