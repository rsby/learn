package datastructures;

import java.util.ArrayList;

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
     * @param element the element to be added
     * @return the heap
     */
    Heap<T> insert(T element);

    /**
     * a factory for naive, slow, non-thread-safe binary heaps that should only be used for learning purposes
     */
    final class Binary {

        /**
         * create an empty binary heap
         *
         * @return a new heap
         */
        public static <T extends Comparable<T>> Heap<T> create() {
            return new BinaryHeap<>();
        }

        /**
         * create a binary heap out of given array of elements
         *
         * @param elements initial elements for the heap
         * @return a heap with the given elements
         */
        public static <T extends Comparable<T>> Heap<T> heapify(T[] elements) {
            return new BinaryHeap<>(elements);
        }

        private static class BinaryHeap<T extends Comparable<T>> implements Heap<T> {

            /**
             * the underlying queue.  used instead of an array for simplicity (don't have to manage growth), since
             * this is by design a naive implementation for learning sake only
             */
            ArrayList<T> queue = new ArrayList<>();

            BinaryHeap() { }

            BinaryHeap(T[] elements) {
                throw new UnsupportedOperationException();
            }

            @Override public T peek() {
                throw new UnsupportedOperationException();
            }

            @Override public T remove() {
                throw new UnsupportedOperationException();
            }

            @Override public Heap<T> insert(T element) {
                throw new UnsupportedOperationException();
            }
        }

    }

}
