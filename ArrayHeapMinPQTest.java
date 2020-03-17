package bearmaps;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQTest {
    @Test
    public void testingAdd() {
        ArrayHeapMinPQ<Integer> testingHeap = new ArrayHeapMinPQ();
        testingHeap.add(3, 5.0);
        testingHeap.add(4, 4.9);
        testingHeap.add(5, 4.8);
        testingHeap.add(6, 4.7);
        testingHeap.add(7, 4.6);
        testingHeap.add(8, 4.5);
        Assert.assertTrue(testingHeap.contains(4));
    }

    @Test
    public void testingGetSmallest() {
        ArrayHeapMinPQ<Integer> testingHeap = new ArrayHeapMinPQ();
        testingHeap.add(3, 5.0);
        testingHeap.add(4, 4.1);
        testingHeap.add(6, 4.7);
        testingHeap.add(7, 4.01);
        testingHeap.add(8, 4.5);
        int smallest = 7;
        int actual = testingHeap.getSmallest();
        assertEquals(smallest, actual);
    }

    @Test
    public void testingRemoveSmallest() {
        ArrayHeapMinPQ<Integer> testingHeap = new ArrayHeapMinPQ();
        testingHeap.add(3, 5.0);
        testingHeap.add(4, 4.1);
        testingHeap.add(6, 4.7);
        testingHeap.add(7, 4.2);
        testingHeap.add(8, 4.5);
        testingHeap.add(11, 1.2);
        testingHeap.add(14, 6.7);
        testingHeap.add(21, 1.1);
        testingHeap.removeSmallest();
        testingHeap.removeSmallest();
        int smallest = 4;
        int actual = testingHeap.getSmallest();
        assertEquals(smallest, actual);
    }

    @Test
    public void testingChangePriority() {
        ArrayHeapMinPQ<Integer> testingHeap = new ArrayHeapMinPQ();
        testingHeap.add(3, 5.0);
        testingHeap.add(4, 4.1);
        testingHeap.add(6, 4.7);
        testingHeap.add(7, 4.2);
        testingHeap.add(8, 4.5);
        testingHeap.add(11, 1.2);
        testingHeap.add(14, 6.7);
        testingHeap.add(21, 1.1);
        testingHeap.changePriority(14, 0.9);
        testingHeap.changePriority(8, 7.7);
        int smallest = 14;
        int actual = testingHeap.getSmallest();
        assertEquals(smallest, actual);

    }

    @Test
    public void testingAddRemoveSmallest() {
        ArrayHeapMinPQ<Character> testingHeap = new ArrayHeapMinPQ();
        testingHeap.add('d', 5.0);
        testingHeap.add('r', 4.1);
        testingHeap.add('f', 4.7);
        testingHeap.add('k', 4.2);
        testingHeap.add('v', 4.5);
        testingHeap.add('e', 1.2);
        testingHeap.add('j', 6.7);
        testingHeap.add('n', 1.1);
        testingHeap.removeSmallest();
        testingHeap.removeSmallest();
        testingHeap.add('l', 0.01);
        testingHeap.add('x', 2.5);
        char smallest = 'l';
        char actual = testingHeap.getSmallest();
        assertEquals(smallest, actual);
    }

    @Test
    public void testingAddRemoveSmallestChangePriority() {
        ArrayHeapMinPQ<Character> testingHeap = new ArrayHeapMinPQ();
        testingHeap.add('d', 5.0);
        testingHeap.add('r', 4.1);
        testingHeap.add('f', 4.7);
        testingHeap.add('k', 4.2);
        testingHeap.add('v', 4.5);
        testingHeap.add('e', 1.2);
        testingHeap.add('j', 6.7);
        testingHeap.add('n', 1.1);
        testingHeap.removeSmallest();
        testingHeap.removeSmallest();
        testingHeap.add('l', 0.02);
        testingHeap.add('x', 2.5);
        testingHeap.changePriority('x', 1.4);
        testingHeap.changePriority('r', 0.01);
        char smallest = 'r';
        char actual = testingHeap.getSmallest();
        assertEquals(smallest, actual);
        testingHeap.removeSmallest();
        testingHeap.removeSmallest();
        char smallest2 = 'x';
        char actual2 = testingHeap.getSmallest();
        assertEquals(smallest2, actual2);
    }

    @Test
    public void testingAddRemoveWithManyElements() {
        ArrayHeapMinPQ<String> testingHeap = new ArrayHeapMinPQ();
        for (int i = 0; i < 1000; i++) {
            testingHeap.add("hi" + i, i = i++);
        }
        for (int i = 0; i < 999; i++) {
            testingHeap.removeSmallest();
        }
        String expected = "hi999";
        String actual = testingHeap.getSmallest();

        assertEquals(expected, actual);



    }


}



