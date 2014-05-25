package datastructures;

import java.util.Comparator;

/**
 *
 * A simple heap interface with a limited set of operations
 *
 * @author rees.byars
 */
public interface Heap<T> {

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
     */
    void insert(T element);

    /**
     * adds new elements to the heap
     * @param elements the elements to be added
     */
    default void insert(T ... elements) {
        for (T t : elements) {
            insert(t);
        }
    }

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
     * create a binary heap out of given array of elements
     *
     * @param elements initial elements for the heap
     * @return a heap with the given elements
     */
    @SafeVarargs public static <T extends Comparable<T>> Heap<T> minHeap(T... elements) {
        return heap(T::compareTo, elements);
    }

    /**
     * create a binary heap out of given array of elements
     *
     * @param elements initial elements for the heap
     * @return a heap with the given elements
     */
    @SafeVarargs public static <T extends Comparable<T>> Heap<T> maxHeap(T... elements) {
        return heap((t, t2) -> t.compareTo(t2) * -1, elements);
    }

    /**
     * create a binary heap out of given array of elements
     *
     * @param comparator a comparator to compare two elements
     * @param elements initial elements for the heap
     * @return a heap with the given elements
     */
    @SafeVarargs public static <T> Heap<T> heap(Comparator<T> comparator, T... elements) {
        return new BinaryHeap<>(elements, 1, comparator);
    }

    /**
     * performs a min heap sort on the given array
     *
     * @param elements array to heap sort
     * @param <T> the type of the elements
     */
    public static <T extends Comparable<T>> void sortAscending(T[] elements) {
        sort(T::compareTo, elements);
    }

    /**
     * performs a max heap sort on the given array
     *
     * @param elements array to heap sort
     * @param <T> the type of the elements
     */
    public static <T extends Comparable<T>> void sortDescending(T[] elements) {
        sort((t, t2) -> t.compareTo(t2) * -1, elements);
    }

    /**
     * performs a max heap sort on the given array
     *
     * @param comparator a comparator to compare two elements
     * @param elements array to heap sort
     * @param <T> the type of the elements
     */
    public static <T> void sort(Comparator<T> comparator, T[] elements) {
        new BinaryHeap<>(elements, 0, comparator);
    }

}
