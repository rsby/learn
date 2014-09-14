package datastructures;

import java.util.Arrays;
import java.util.Comparator;

/**
 * a basic, non-thread-safe binary heap intended for learning purposes
 *
 * @author rees.byars
 */
class BinaryHeap<T> implements Heap<T> {

    private final int offset;

    private final Comparator<T> comparator;

    private final double loadFactor = .75;

    private Object[] queue;

    private int loadLimit;

    private int currentNumberOfElements = 0;

    /**
     * @param elements the initial elements of the heap
     * @param offset the number of empty elements to include at the beginning of the heap queue.
     *               if set to zero, then the assumption is made that the elements are to be sorted
     *               in place, thereby enabling a heap sort on the given elements.  if the offset
     *               is non-zero, then the given elements are not sorted in-place
     * @param comparator the comparator used to (partially) order the heap
     */
    BinaryHeap(T[] elements, int offset, Comparator<T> comparator) {

        this.offset = offset;
        this.comparator = comparator;

        if (offset == 0 && elements.length > 0) {
            loadLimit = elements.length;
            queue = elements;
        } else {
            int queueLength = elements.length < 11 ?
                    22 + offset :
                    (elements.length + offset) * 2;
            loadLimit = (int) Math.round(queueLength * loadFactor);
            queue = new Object[queueLength];
        }

        insert(elements);

    }

    @Override public T peek() {
        return get(offset);
    }

    @Override public T remove() {
        return remove(offset);
    }

    @Override public T remove(T element) {

        if (element == null) {
            throw new NullPointerException("Null elements not allowed");
        }

        int index = findIndex(element, offset);

        if (index == -1) {
            return null;
        }

        return remove(index);
    }

    @Override public void insert(T element) {

        if (element == null) {
            throw new NullPointerException("Null elements not allowed");
        }

        growIfNecessary();

        int cursor = currentNumberOfElements + offset;

        // starting from last position, pull parents down while they come after new element
        for (int parentIndex = parentIndex(cursor);
             cursor > offset && comparator.compare(get(parentIndex), element) > 0;
             cursor = parentIndex, parentIndex = parentIndex(parentIndex)) {
            queue[cursor] = get(parentIndex);
        }

        // set our new element into the open slot
        queue[cursor] = element;

        currentNumberOfElements++;

    }

    @Override public void subsume(Heap<? extends T> other) {

        if (!(other instanceof BinaryHeap)) {
            throw new IllegalArgumentException(
                    "Heap does not meet conditions for subsumption. Reason:  heap not of same type"
            );

        } else if (comparator.getClass() != ((BinaryHeap) other).comparator.getClass()) {
            throw new IllegalArgumentException(
                    "Heap does not meet conditions for subsumption. Reason:  comparators not of same type"
            );
        }

        Object[] myQueue = toArray();
        Object[] otherQueue = other.toArray();

        if (myQueue.length < otherQueue.length) {
            merge(myQueue, otherQueue);
        } else {
            merge(otherQueue, myQueue);
        }

    }

    @Override public Object[] toArray() {
        return Arrays.copyOfRange(queue, offset, currentNumberOfElements + offset);
    }

    @Override public int size() {
        return currentNumberOfElements;
    }

    private void growIfNecessary() {
        if (currentNumberOfElements < loadLimit)
            return;
        int queueLength = (int) Math.round(
                queue.length < currentNumberOfElements ?
                        currentNumberOfElements : queue.length / loadFactor);
        loadLimit = queue.length;
        queue = Arrays.copyOf(queue, queueLength);
    }

    private int parentIndex(int childIndex) {
        return (childIndex - 1 + offset)/ 2;
    }

    private int leftChildIndex(int parentIndex) {
        return parentIndex * 2 + 1 - offset;
    }

    @SuppressWarnings("unchecked")
    private T get(int index) {
        return (T) queue[index];
    }

    private T remove(int index) {

        if (currentNumberOfElements == 0) {
            return null;
        }

        T removed = get(index);
        int cursor = index;
        int lastIndex = currentNumberOfElements - 1 + offset;
        T last = get(lastIndex);

        // starting from the first child, "percolate" down the heap
        for (int childIndex = leftChildIndex(cursor);
             childIndex < lastIndex;
             cursor = childIndex, childIndex = leftChildIndex(childIndex)) {

            // if right child "comes after" left child, then follow the right child path
            if (comparator.compare(get(childIndex), get(childIndex + 1)) > 0) {
                childIndex++;
            }

            // "sift" childIndex up if it should be parent of last
            if (comparator.compare(last, get(childIndex)) > 0) {
                queue[cursor] = get(childIndex);

                // looks like last has found a new home
            } else {
                break;
            }

        }

        queue[cursor] = last;
        queue[lastIndex] = null;
        currentNumberOfElements--;

        return removed;

    }

    private int findIndex(T element, int fromIndex) {

        // check the bounds
        int lastIndex = currentNumberOfElements - 1 + offset;
        if (fromIndex > lastIndex) {
            return -1;
        }

        // check the element at the current position
        T current = get(fromIndex);
        int comparison = comparator.compare(element, current);
        if (comparison == 0) {
            return fromIndex;
        } else if (comparison == -1) {
            return -1;
        }

        // not found, try left path
        int leftChildIndex = leftChildIndex(fromIndex);
        int leftPathResult = findIndex(element, leftChildIndex);
        if (leftPathResult != -1) {
            return leftPathResult;
        }

        // not found, try right path
        return findIndex(element, ++leftChildIndex);

    }

    @SuppressWarnings("unchecked")
    private void merge(Object[] shortQ, Object[] longQ) {

        if (longQ.length - shortQ.length > shortQ.length) {

            currentNumberOfElements = longQ.length;
            growIfNecessary();
            System.arraycopy(longQ, 0, queue, 1, longQ.length);
            for (Object e : shortQ) {
                insert((T) e);
            }

        } else if (longQ.length != 0) {

            currentNumberOfElements = shortQ.length * 2;
            growIfNecessary();

            Object[] first;
            Object[] second;
            if (comparator.compare((T) shortQ[0], (T) longQ[0]) > 0) {
                first = longQ;
                second = shortQ;
            } else {
                first = shortQ;
                second = longQ;
            }
            for (int i = 0; i < shortQ.length; i++) {
                int child = (i * 2) + offset;
                queue[child] = first[i];
                queue[++child] = second[i];
            }

            for (int i = shortQ.length; i < longQ.length; i++) {
                insert((T) longQ[i]);
            }

        }

    }

}
