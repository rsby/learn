package datastructures;

import utility.ComparisonAdapter;
import utility.ComparisonAdapterFactory;
import utility.IndexToElementConverter;

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
        @SafeVarargs public static <T extends Comparable<T>> Heap<T> minHeap(T... elements) {
            return new BinaryHeap<>(elements, new ComparisonAdapterFactory.Min());
        }

        /**
         * create a binary heap out of given array of elements
         *
         * @param elements initial elements for the heap
         * @return a heap with the given elements
         */
        @SafeVarargs public static <T extends Comparable<T>> Heap<T> maxHeap(T... elements) {
            return new BinaryHeap<>(elements, new ComparisonAdapterFactory.Max());
        }

        private static class BinaryHeap<T extends Comparable<T>> implements Heap<T> {

            final ComparisonAdapterFactory comparisonAdapterFactory;

            final IndexToElementConverter<T> indexToElementConverter = this::get;

            Comparable[] queue;

            final double loadFactor = .75;

            int loadLimit;

            int currentNumberOfElements = 0;

            BinaryHeap(T[] elements, ComparisonAdapterFactory comparisonAdapterFactory) {
                this.comparisonAdapterFactory = comparisonAdapterFactory;
                int queueLength = elements.length * 2;
                loadLimit = (int) Math.round(queueLength * loadFactor);
                queue = new Comparable[queueLength];
                for (T element : elements) {
                    add(element);
                }
            }

            @Override public T peek() {
                return get(1);
            }

            @Override public T remove() {

                // store the top element so we can return it
                T removed = peek();

                // move the last element to the top
                int cursor = 1;
                T current = get(currentNumberOfElements);
                queue[1] = current;

                // move down the heap
                for (int child; leftChildIndex(cursor) < currentNumberOfElements; cursor = child) {

                    child = leftChildIndex(cursor);
                    int rightChild = rightChildIndex(cursor);

                    if (rightChild <= currentNumberOfElements && valueOf(child).comesAfter(rightChild)) {
                        child = rightChild;
                    }

                    // compare the child to our current element
                    if (valueOf(child).comesAfter(current)) {
                        queue[cursor] = current;
                        current = get(child);
                    } else {
                        queue[cursor] = get(child);
                    }

                }

                queue[cursor] = current;

                queue[currentNumberOfElements--] = null;

                return removed;

            }

            @SafeVarargs @Override public final void insert(T ... elements) {
                for (T element : elements) {
                    add(element);
                }
            }

            private void add(T element) {

                int cursor = ++currentNumberOfElements; // start from last child
                growIfNecessary();

                // push parents down if they come after our new element
                for (; cursor > 1 && valueOf(parent(cursor)).comesAfter(element); cursor = parentIndex(cursor)) {
                    queue[cursor] = parent(cursor);
                }

                // set our new element into the open slot
                queue[cursor] = element;

            }

            void growIfNecessary() {
                if (currentNumberOfElements < loadLimit)
                    return;
                int queueLength = (int) Math.round(queue.length / loadFactor);
                loadLimit = queue.length;
                queue = Arrays.copyOf(queue, queueLength);
            }

            int parentIndex(int childIndex) {
                return childIndex / 2;
            }

            int leftChildIndex(int parentIndex) {
                return parentIndex * 2;
            }

            int rightChildIndex(int parentIndex) {
                return leftChildIndex(parentIndex) + 1;
            }

            @SuppressWarnings("unchecked")
            T parent(int childIndex) {
                return (T) queue[parentIndex(childIndex)];
            }

            @SuppressWarnings("unchecked")
            T get(int index) {
                return (T) queue[index];
            }

            ComparisonAdapter<T> valueOf(T t) {
                return comparisonAdapterFactory.newAdapterFor(t, indexToElementConverter);
            }

            ComparisonAdapter<T> valueOf(int index) {
                return comparisonAdapterFactory.newAdapterFor(get(index), indexToElementConverter);
            }

        }

    }

}
