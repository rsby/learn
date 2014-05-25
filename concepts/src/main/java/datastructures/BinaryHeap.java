package datastructures;

import utility.ComparisonAdapter;
import utility.ComparisonAdapterFactory;

import java.util.Arrays;

/**
 * @author rees.byars
 */
class BinaryHeap<T extends Comparable<T>> implements Heap<T> {

    final ComparisonAdapterFactory comparisonAdapterFactory;

    Comparable[] queue;

    final double loadFactor = .75;

    int loadLimit;

    int currentNumberOfElements = 0;

    final int offset;

    BinaryHeap(T[] elements, int offset, ComparisonAdapterFactory comparisonAdapterFactory) {
        this.offset = offset;
        this.comparisonAdapterFactory = comparisonAdapterFactory;

        if (offset == 0) {
            loadLimit = elements.length;
            queue = elements;
        } else {
            int queueLength = elements.length * 2;
            loadLimit = (int) Math.round(queueLength * loadFactor);
            queue = new Comparable[queueLength];
        }

        for (T element : elements) {
            add(element);
        }
    }

    @Override public T peek() {
        return get(offset);
    }

    @Override public T remove() {

        // store the top element so we can return it
        T removed = peek();

        int cursor = offset;
        T last = get(currentNumberOfElements - (1 - offset));

        // move down the heap
        for (int child; leftChildIndex(cursor) < currentNumberOfElements - (1 - offset); cursor = child) {

            child = leftChildIndex(cursor);
            int rightChild = rightChildIndex(cursor);

            if (rightChild <= currentNumberOfElements - (1 - offset) && valueOf(child).comesAfter(rightChild)) {
                child = rightChild;
            }

            // move child up if it should be parent of last
            if (valueOf(last).comesAfter(child)) {
                queue[cursor] = get(child);
            } else {
                break;
            }

        }

        queue[cursor] = last;

        queue[offset + currentNumberOfElements--] = null;

        return removed;

    }

    @SafeVarargs @Override public final void insert(T ... elements) {
        for (T element : elements) {
            add(element);
        }
    }

    @Override public Object[] toArray() {
        return Arrays.copyOfRange(queue, offset, currentNumberOfElements + offset);
    }

    @Override public <TT extends T> TT[] toArray(Class<TT[]> type) {
        return Arrays.copyOfRange(queue, offset, currentNumberOfElements + offset, type);
    }

    @Override public int size() {
        return currentNumberOfElements;
    }

    private void add(T element) {

        int cursor = currentNumberOfElements + offset;
        growIfNecessary();

        // push parents down if they come after our new element
        for (; cursor > offset && valueOf(parent(cursor)).comesAfter(element); cursor = parentIndex(cursor)) {
            queue[cursor] = parent(cursor);
        }

        // set our new element into the open slot
        queue[cursor] = element;

        currentNumberOfElements++;

    }

    void growIfNecessary() {
        if (currentNumberOfElements < loadLimit)
            return;
        int queueLength = (int) Math.round(queue.length / loadFactor);
        loadLimit = queue.length;
        queue = Arrays.copyOf(queue, queueLength);
    }

    int parentIndex(int childIndex) {
        return (childIndex - 1 + offset)/ 2;
    }

    int leftChildIndex(int parentIndex) {
        return parentIndex * 2 + 1 - offset;
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
