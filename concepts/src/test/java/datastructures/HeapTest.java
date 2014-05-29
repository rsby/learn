package datastructures;

import org.junit.Test;
import testutility.Distribution;

import java.util.Arrays;

import static datastructures.Heap.maxHeap;
import static datastructures.Heap.minHeap;
import static datastructures.Heap.sortAscending;
import static datastructures.Heap.sortDescending;
import static org.junit.Assert.assertEquals;


/**
 * @author rees.byars
 */
public class HeapTest  {

    // TODO this is bare bones, no edge cases covered

    @Test(expected = NullPointerException.class)
    public void test_nullInsert() {

        minHeap(1, 2).insert(null, null);

    }

    @Test(expected = NullPointerException.class)
    public void test_nullCreate() {

        minHeap(1, 2, null);

    }

    @Test
    public void testPeek() {

        Heap<Integer> heap = minHeap(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);

        heap.insert(2, 3, 5, 4, 66, 87, 23, 3, 234, 85, 23554, 23, 456654, 2, 1, 4444, 9);

        assert heap.peek() == 1;

        heap = minHeap();

        heap.insert(2, 3, 5, 4, 66, 87, 23, 3, 234, 85, 23554, 23, 456654, 2, 1, 4444, 9);

        assert heap.peek() == 1;

        assert maxHeap().peek() == null;

    }

    @Test
    public void testRemove() {

        Heap<Integer> heap = minHeap(7, 3, 2, 6, 9, 12, 14, 11, 3, 22, 19, 4);

        assert heap.peek() == 2;

        assert heap.remove() == 2;

        assert heap.remove() == 3;

        assert heap.remove() == 3;

        assert heap.remove() == 4;

        assert heap.remove() == 6;

        assert heap.remove() == 7;

        assert heap.remove() == 9;

        assert heap.remove() == 11;

        assert heap.remove() == 12;

        assert heap.remove() == 14;

        assert heap.remove() == 19;

        assert heap.remove() == 22;

        assert heap.remove() == null;

        assert heap.peek() == null;

        assert heap.toArray().length == 0;

    }

    @Test
    public void testInsert() {

        Heap<Integer> heap = minHeap(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);

        heap.insert(2, 3, 5, 4, 66, 87, 23, 3, 234, 85, 23554, 23, 456654, 2, 1, 4444, 9);

        assert heap.peek() == 1;

        heap.insert(0);

        assert heap.peek() == 0;

    }

    @Test
    public void testPeek_max() {

        Heap<Integer> heap = maxHeap(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);

        heap.insert(2, 3, 5, 4, 66, 87, 23, 3, 234, 85, 23554, 23, 456654, 2, 1, 4444, 9);

        assert heap.peek() == 456654;

    }

    @Test
    public void testRemove_max() {

        Heap<Integer> heap = maxHeap(7, 3, 2, 6, 9, 12, 14, 11, 3, 22, 19, 4);

        assert heap.remove() == 22;

        assert heap.remove() == 19;

        assert heap.remove() == 14;

        assert heap.remove() == 12;

        assert heap.remove() == 11;

        assert heap.remove() == 9;

        assert heap.remove() == 7;

        assert heap.remove() == 6;

        assert heap.remove() == 4;

        assert heap.remove() == 3;

        assert heap.remove() == 3;

        assert heap.remove() == 2;

    }

    @Test
    public void testInsert_max() {

        Heap<Integer> heap = maxHeap(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);

        heap.insert(2, 3, 5, 4, 66, 87, 23, 3, 234, 85, 23554, 23, 456654, 2, 1, 4444, 9);

        assert heap.peek() == 456654;

        heap.insert(4566540);

        assert heap.peek() == 4566540;

    }

    @Test
    public void testToArray() {

        Heap<Integer> heap = minHeap(7, 3, 2, 6, 9, 12, 14, 11, 3, 22, 19, 4);

        Object[] first = heap.toArray();

        assert first.length == 12;

        assert heap.toArray()[0].equals(2);

        assert heap.remove() == 2;

        assert heap.toArray().length + 1 == first.length;

    }

    @Test
    public void testSize() {

        Heap<Integer> heap = minHeap(7, 3, 2, 6, 9, 12, 14, 11, 3, 22, 19, 4);

        assert heap.size() == 12;

    }

    @Test
    public void testSortAscending() {
        Integer[] ints = {3, 5, 1, 88, 2, 5, 111, 7, 7, 7, 124333, -9, 324, -9, -3, 2222};
        sortAscending(ints);
        assert ints[0] == -9;
        sortAscending(new Integer[0]);
    }

    @Test
    public void testSortDescending() {
        Integer[] ints = {3, 5, 1, 88, 2, 5, 111, 7, 7, 7, 124333, -9, 324, -9, -3, 2222};
        sortDescending(ints);
        assert ints[0] == 124333;
        sortDescending(new Integer[0]);
    }

    @Test
    public void testOrder() {
        // NOTE: this test will FAIL if the offset is set to 0 (in which case the
        // passed in array is used for the heap queue, which in the case of this test
        // means the "values" array becomes the heap queue
        for (Distribution distribution : Distribution.values()) {
            Integer[] values = distribution.create(1000);
            Heap<Integer> heap = minHeap(values);
            Arrays.sort(values);
            for (Integer i : values) {
                assertEquals("Distribution [" + distribution + "]", i, heap.remove());
            }
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSubsume_exception_comparator_mismatch() {

        Heap<Integer> heap = minHeap(7, 3, 2, 6, 9, 12, 14, 11, 3, 22, 19, 4);

        // min heap cannot consume max heap
        heap.subsume(maxHeap(5, 2, 54, 9, 0));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testSubsume_exception_heap_type_mismatch() {

        Heap<Integer> heap = minHeap(7, 3, 2, 6, 9, 12, 14, 11, 3, 22, 19, 4);

        // binary heap cannot consume delegating heap (so it says, really it could, but it doesn't know that)
        heap.subsume(new DelegatingHeap<>(minHeap(1)));

    }

    @Test
    public void testSubsume() {

        Heap<Integer> heap = minHeap(7, 3, 2, 6, 9, 12, 14, 11, 3, 22, 19, 4);

        assert heap.size() == 12;

        heap.subsume(minHeap(5, 2, 54, 9, 0));

        assert heap.remove() == 0;

        assert heap.remove() == 2;

        assert heap.size() == 15;

        assert heap.remove() == 2;

        assert heap.remove() == 3;

        assert heap.remove() == 3;

        assert heap.remove() == 4;

        assert heap.remove() == 5;

        assert heap.remove() == 6;

        assert heap.remove() == 7;

        assert heap.remove() == 9;

        assert heap.remove() == 9;

        assert heap.remove() == 11;

        assert heap.remove() == 12;

        assert heap.remove() == 14;

        assert heap.remove() == 19;

        assert heap.remove() == 22;

        assert heap.remove() == 54;

        assert heap.remove() == null;

        heap = minHeap(7);

        heap.subsume(minHeap(5, 2, 54, 9, 0));

        assert heap.remove() == 0;

        heap = minHeap(2);

        heap.subsume(minHeap());

        assert heap.remove() == 2;

        assert heap.remove() == null;

        heap = minHeap();

        heap.subsume(minHeap());

        assert heap.remove() == null;

        heap = minHeap(2);

        heap.subsume(minHeap(-1));

        assert heap.remove() == -1;

        assert heap.remove() == 2;

        assert heap.remove() == null;

        heap = minHeap(7, 2);

        heap.subsume(minHeap(5, 0));

        assert heap.remove() == 0;

        assert heap.remove() == 2;

        assert heap.remove() == 5;

        assert heap.remove() == 7;

        assert heap.remove() == null;

        heap = minHeap();

        heap.subsume(minHeap(5, 0));

        assert heap.remove() == 0;

        assert heap.remove() == 5;

        assert heap.remove() == null;

        heap = minHeap(2, 1);

        heap.subsume(minHeap(0));

        assert heap.remove() == 0;

        assert heap.remove() == 1;

        assert heap.remove() == 2;

    }

    static class DelegatingHeap<T> implements Heap<T> {

        final Heap<T> delegate;

        DelegatingHeap(Heap<T> delegate) {
            this.delegate = delegate;
        }

        @Override public T peek() {
            return delegate.peek();
        }

        @Override public T remove() {
            return delegate.remove();
        }

        @Override public void insert(T element) {
            delegate.insert(element);
        }

        @Override public void subsume(Heap<? extends T> heap) {
            delegate.subsume(heap);
        }

        @Override public Object[] toArray() {
            return delegate.toArray();
        }

        @Override public int size() {
            return delegate.size();
        }

    }

}
