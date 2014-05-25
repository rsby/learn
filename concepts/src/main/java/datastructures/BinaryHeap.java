package datastructures;

import utility.ComparisonAdapter;

import java.util.Arrays;

/**
 * @author rees.byars
 */
class BinaryHeap<T extends Comparable<T>> implements Heap<T> {

    final int offset;

    final ComparisonAdapter<T> comparisonAdapter;

    Comparable[] queue;

    final double loadFactor = .75;

    int loadLimit;

    int currentNumberOfElements = 0;

    BinaryHeap(T[] elements, int offset, ComparisonAdapter<T> comparisonAdapter) {

        this.offset = offset;
        this.comparisonAdapter = comparisonAdapter;

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
        int lastIndex = currentNumberOfElements - 1 + offset;
        T last = get(lastIndex);

        // move down the heap
        for (int childIndex = leftChildIndex(cursor); childIndex < lastIndex;) {

            if (comparisonAdapter.compare(get(childIndex), get(childIndex + 1)) > 0) {
                childIndex++;
            }

            // move childIndex up if it should be parent of last
            if (comparisonAdapter.compare(last, get(childIndex)) > 0) {
                queue[cursor] = get(childIndex);
            } else {
                break;
            }

            cursor = childIndex;
            childIndex = leftChildIndex(cursor);

        }

        queue[cursor] = last;
        queue[lastIndex] = null;
        currentNumberOfElements--;

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
        for (int parentIndex = parentIndex(cursor);
             cursor > offset && comparisonAdapter.compare(get(parentIndex), element) > 0;) {
            queue[cursor] = get(parentIndex);
            cursor = parentIndex;
            parentIndex = parentIndex(cursor);
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

    @SuppressWarnings("unchecked")
    T get(int index) {
        return (T) queue[index];
    }

}
