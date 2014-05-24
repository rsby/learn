package sorts;

import datastructures.Heap;

/**
 * @author rees.byars
 */
public final class Sort {

    private Sort() { }

    public static <T extends Comparable<T>> void heapSortAscending(T[] elements) {
        System.arraycopy(Heap.Binary.minHeap(elements).toArray(), 0, elements, 0, elements.length);
    }

    public static <T extends Comparable<T>> void heapSortDescending(T[] elements) {
        System.arraycopy(Heap.Binary.maxHeap(elements).toArray(), 0, elements, 0, elements.length);
    }

}
