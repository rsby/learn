package datastructures;

import org.junit.Test;

/**
 * @author rees.byars
 */
public class HeapTest  {

    // TODO this is bare bones, no edge cases covered

    @Test
    public void testPeek() {

        Heap<Integer> heap = Heap.Binary.createMinHeap(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);

        heap.insert(2, 3, 5, 4, 66, 87, 23, 3, 234, 85, 23554, 23, 456654, 2, 1, 4444, 9);

        assert heap.peek() == 1;

    }

    @Test
    public void testRemove() {

        Heap<Integer> heap = Heap.Binary.createMinHeap(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);

        heap.insert(2, 3, 5, 4, 66, 87, 23, 3, 234, 85, 23554, 23, 456654, 2, 1, 4444, 9);

        assert heap.peek() == 1;

        assert heap.remove() == 1;

        assert heap.peek() == 2;

    }

    @Test
    public void testInsert() {

        Heap<Integer> heap = Heap.Binary.createMinHeap(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);

        heap.insert(2, 3, 5, 4, 66, 87, 23, 3, 234, 85, 23554, 23, 456654, 2, 1, 4444, 9);

        assert heap.peek() == 1;

        heap.insert(0);

        assert heap.peek() == 0;

    }

}
