package sorts;

import datastructures.Heap;

/**
 * @author rees.byars
 */
public final class Sort {

    private Sort() { }

    public static <T extends Comparable<T>> void heapSortAscending(T[] elements) {
        Heap.Binary.sortAscending(elements);
    }

    public static <T extends Comparable<T>> void heapSortDescending(T[] elements) {
        Heap.Binary.sortDescending(elements);
    }

}
