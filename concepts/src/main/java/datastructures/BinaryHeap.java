package datastructures;

import utility.ComparisonAdapter;
import utility.ComparisonAdapterFactory;

import java.util.Arrays;

/**
 * @author rees.byars
 */
public class BinaryHeap<T extends Comparable<T>> implements Heap<T> {

    final ComparisonAdapterFactory comparisonAdapterFactory;

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

    @Override public Object[] toArray() {
        return Arrays.copyOfRange(queue, 1, currentNumberOfElements + 1);
    }

    @Override public <TT extends T> TT[] toArray(Class<TT[]> type) {
        return Arrays.copyOfRange(queue, 1, currentNumberOfElements + 1, type);
    }

    @Override public int size() {
        return currentNumberOfElements;
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
        return comparisonAdapterFactory.newAdapterFor(t, this::get);
    }

    ComparisonAdapter<T> valueOf(int index) {
        return comparisonAdapterFactory.newAdapterFor(get(index), this::get);
    }

}
