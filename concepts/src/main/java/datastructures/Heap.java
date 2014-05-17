package datastructures;

import java.util.Arrays;

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
     * a factory for naive, slow, non-thread-safe binary heaps that should only be used for learning purposes
     */
    final class Binary {

        /**
         * create a binary heap out of given array of elements
         *
         * @param elements initial elements for the heap
         * @return a heap with the given elements
         */
        @SafeVarargs public static <T extends Comparable<T>> Heap<T> createMinHeap(T... elements) {
            return new BinaryMinHeap<>(elements);
        }

        private static class BinaryMinHeap<T extends Comparable<T>> implements Heap<T> {

            Comparable[] queue;

            static final double loadFactor = .75;

            int loadLimit;

            int currentNumberOfElements = 0;

            BinaryMinHeap(T[] elements) {
                int queueLength = elements.length * 2;
                loadLimit = (int) Math.round(queueLength * loadFactor);
                queue = new Comparable[queueLength];
                for (T element : elements) {
                    add(element);
                }
            }

            @Override public T peek() {
                return (T) queue[1];
            }

            @Override public T remove() {
                throw new UnsupportedOperationException();
            }

            @SafeVarargs @Override public final void insert(T ... elements) {
                for (T element : elements) {
                    add(element);
                }
            }

            private void add(T element) {
                currentNumberOfElements++;
                growIfNecessary();
                queue[currentNumberOfElements] = element;
                // TODO
            }

            void growIfNecessary() {
                if (currentNumberOfElements < loadLimit)
                    return;
                int queueLength = (int) Math.round(queue.length / loadFactor);
                loadLimit = queue.length;
                queue = Arrays.copyOf(queue, queueLength);
            }
        }

    }

}
