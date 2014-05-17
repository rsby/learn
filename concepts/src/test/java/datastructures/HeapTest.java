package datastructures;

import org.junit.Test;

/**
 * @author rees.byars
 */
public class HeapTest  {

    @Test
    public void testPeek() {

        Heap<Integer> heap =
                Heap.Binary.heapify(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);

        heap.insert(2)
                .insert(3)
                .insert(4)
                .insert(5)
                .insert(5)
                .insert(5)
                .insert(5)
                .insert(5)
                .insert(5)
                .insert(5).insert(5).insert(5).insert(5).insert(5).insert(5).insert(5);

        assert heap.peek() == 2;

    }

    @Test
    public void testRemove() {

    }

    @Test
    public void testInsert() {

    }

}
