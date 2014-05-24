package datastructures;

import org.junit.Test;

import static datastructures.Heap.Binary.createMinHeap;

/**
 * @author rees.byars
 */
public class HeapTest  {

    // TODO this is bare bones, no edge cases covered

    @Test
    public void testPeek() {

        Heap<Integer> heap = createMinHeap(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);

        heap.insert(2, 3, 5, 4, 66, 87, 23, 3, 234, 85, 23554, 23, 456654, 2, 1, 4444, 9);

        assert heap.peek() == 1;

    }

    @Test
    public void testRemove() {

        Heap<Integer> heap = createMinHeap(7, 3, 2, 6, 9, 12, 14, 11, 3, 22, 19, 4);

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

    }

    @Test
    public void testInsert() {

        Heap<Integer> heap = createMinHeap(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);

        heap.insert(2, 3, 5, 4, 66, 87, 23, 3, 234, 85, 23554, 23, 456654, 2, 1, 4444, 9);

        assert heap.peek() == 1;

        heap.insert(0);

        assert heap.peek() == 0;

    }

}
