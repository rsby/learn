package datastructures;

import org.junit.Test;

import static datastructures.Heap.maxHeap;
import static datastructures.Heap.minHeap;
import static datastructures.Heap.sortAscending;
import static datastructures.Heap.sortDescending;


/**
 * @author rees.byars
 */
public class HeapTest  {

    // TODO this is bare bones, no edge cases covered

    @Test
    public void testPeek() {

        Heap<Integer> heap = minHeap(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);

        heap.insert(2, 3, 5, 4, 66, 87, 23, 3, 234, 85, 23554, 23, 456654, 2, 1, 4444, 9);

        assert heap.peek() == 1;

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
    public void testToArrayOfType() {

        Heap<Integer> heap = minHeap(7, 3, 2, 6, 9, 12, 14, 11, 3, 22, 19, 4);

        Integer[] first = heap.toArray(Integer[].class);

        assert first.length == 12;

        assert heap.toArray(Integer[].class)[0] == 2;

        assert heap.remove() == 2;

        assert heap.toArray(Integer[].class).length + 1 == first.length;

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
    }

    @Test
    public void testSortDescending() {
        Integer[] ints = {3, 5, 1, 88, 2, 5, 111, 7, 7, 7, 124333, -9, 324, -9, -3, 2222};
        sortDescending(ints);
        assert ints[0] == 124333;
    }

}
