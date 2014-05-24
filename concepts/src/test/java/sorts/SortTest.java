package sorts;

import org.junit.Test;

/**
 * @author rees.byars
 */
public class SortTest {

    // TODO these tests aren't so great

    @Test
    public void testHeapDescending() {
        Integer[] ints = {3, 5, 1, 88, 2, 5, 111, 7};
        Sort.heapSortAscending(ints);
        assert ints[0] == 1;
    }

    @Test
    public void testHeapAscending() {
        Integer[] ints = {3, 5, 1, 88, 2, 5, 111, 7};
        Sort.heapSortDescending(ints);
        assert ints[0] == 111;
    }


}
