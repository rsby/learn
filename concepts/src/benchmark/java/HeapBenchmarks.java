import com.google.caliper.BeforeExperiment;
import com.google.caliper.Benchmark;
import com.google.caliper.Param;
import datastructures.Heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author rees.byars
 */
public class HeapBenchmarks {

    @Param({"100000"}) private int length;

    @Param({"SAWTOOTH"}) private Distribution distribution;

    private Integer[] values;

    @BeforeExperiment void setUp() throws Exception {
        values = distribution.create(length);
    }

    @Benchmark long customHeap(long reps) {
        long dummy = 0;
        for (long i = 0; i < reps; i++) {
            Heap<Integer> heap = Heap.Binary.minHeap(values);
            dummy |= heap.peek();
        }
        return dummy;
    }

    @Benchmark long customHeapSort(long reps) {
        long dummy = 0;
        for (long i = 0; i < reps; i++) {
            Heap.Binary.sortAscending(values);
            dummy |= values[0];
        }
        return dummy;
    }

    @Benchmark long javaPriorityQueue(long reps) {
        long dummy = 0;
        for (long i = 0; i < reps; i++) {
            PriorityQueue<Integer> queue = new PriorityQueue<>();
            queue.addAll(Arrays.asList(values));
            dummy |= queue.peek();
        }
        return dummy;
    }

    @Benchmark long listSort(long reps) {
        long dummy = 0;
        for (long i = 0; i < reps; i++) {
            List<Integer> list = new ArrayList<>(Arrays.asList(values));
            Collections.sort(list);
            dummy |= list.get(0);
        }
        return dummy;
    }

    @Benchmark long arraysSort(long reps) {
        long dummy = 0;
        for (long i = 0; i < reps; i++) {
            Arrays.sort(values);
            dummy |= values[0];
        }
        return dummy;
    }

}
