package datastructures;

/**
 *
 * A simple heap interface with a limited set of operations
 *
 * @author rees.byars
 */
public interface Heap<T extends Comparable<T>> {

    /**
     * find the maximum item of a max-heap or a minimum item of a min-heap, respectively
     *
     * @return T the top element of the heap
     */
    T peek();

    /**
     * remove the root node of the heap
     *
     * @return the root node that is being removed
     */
    T remove();

    /**
     * adds a new element to the heap
     * @param elements the elements to be added
     */
    void insert(T ... elements);

    /**
     *
     * @return a copy of the array backing this heap, with the elements beginning at zero and length equal to {@link #size()}
     */
    Object[] toArray();

    /**
     *
     * @return a copy of the array backing this heap, with the elements beginning at zero and length equal to {@link #size()}
     */
    <TT extends T> TT[] toArray(Class<TT[]> type);

    /**
     *
     * @return the number of elements currently in the heap
     */
    int size();

    /**
     * a factory for naive, slow, non-thread-safe binary heaps that should only be used for learning purposes
     */
    final class Binary {

        /**
         * create a binary heap out of given array of elements
         *
         * @param elements initial elements for the heap
         * @return a heap with the given elements
         */
        @SafeVarargs public static <T extends Comparable<T>> Heap<T> minHeap(T... elements) {
            return new BinaryHeap<>(elements, 1, T::compareTo);
        }

        /**
         * create a binary heap out of given array of elements
         *
         * @param elements initial elements for the heap
         * @return a heap with the given elements
         */
        @SafeVarargs public static <T extends Comparable<T>> Heap<T> maxHeap(T... elements) {
            return new BinaryHeap<>(elements, 1, (t, t2) -> t.compareTo(t2) * -1);
        }

        /**
         * performs a min heap sort on the given array
         *
         * @param elements array to heap sort
         * @param <T> the type of the elements
         */
        public static <T extends Comparable<T>> void sortAscending(T[] elements) {
            new BinaryHeap<>(elements, 0, T::compareTo);
        }

        /**
         * performs a max heap sort on the given array
         *
         * @param elements array to heap sort
         * @param <T> the type of the elements
         */
        public static <T extends Comparable<T>> void sortDescending(T[] elements) {
            new BinaryHeap<>(elements, 0, (t, t2) -> t.compareTo(t2) * -1);
        }

    }

}
