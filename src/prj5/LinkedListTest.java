package prj5;

import java.util.Iterator;
import java.util.NoSuchElementException;
import student.TestCase;

/**
 * @author Eric
 * @author maellis1
 * @author mullenj
 * @version 11-2-15
 * @version 4.16.2019
 *
 */
public class LinkedListTest extends TestCase {
    /**
     * the list we will use
     */
    private LinkedList<String> list;


    /**
     * run before every test case
     */
    @Override
    public void setUp() {
        list = new LinkedList<String>();
    }


    /**
     * Tests the add method. Ensures that it adds the object is added at the end
     * and the size is increased
     */
    public void testAdd() {
        assertEquals(0, list.size());
        list.add("A");
        assertEquals(1, list.size());
        list.add("B");
        assertEquals(2, list.size());
        assertEquals("B", list.get(1));

    }


    /**
     * Tests that objects can be added at the beginning and end and that they
     * are placed correctly
     */
    public void testAddIndex() {
        list.add("B");
        list.add(0, "A");
        assertEquals("A", list.get(0));
        assertEquals(2, list.size());
        list.add(2, "D");
        assertEquals("D", list.get(2));
        list.add(2, "C");
        assertEquals("C", list.get(2));
    }


    /**
     * This tests that the add method throws a null pointer exception when
     * adding null data to the list
     */
    public void testAddNullException() {
        Exception e = null;
        try {
            list.add(null);
        }
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IllegalArgumentException);
    }


    /**
     * This tests that the add method throws a Invalid argument when adding null
     * data to the list
     */
    public void testAddIndexNullException() {
        Exception e = null;
        try {
            list.add(0, null);
        }
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IllegalArgumentException);
    }


    /**
     * This tests when the add method is called and the index is greater than
     * size or less than zero
     */
    public void testAddException() {
        list.add("A");
        Exception e = null;
        try {
            list.add(2, "B");
        }
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IndexOutOfBoundsException);
        e = null;
        try {
            list.add(-1, "B");
        }
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IndexOutOfBoundsException);
    }


    /**
     * Tests isEmpty when empty and full
     */
    public void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.add("A");
        assertFalse(list.isEmpty());
    }


    /**
     * Ensures that all of the objects are cleared and the size is changed
     */
    public void testClear() {
        list.add("A");
        list.clear();
        assertEquals(0, list.size());
    }


    /**
     * Tests the toString when there are 0, 1, and 2 objects in the list
     */
    public void testToString() {
        assertEquals("{}", list.toString());
        list.add("A");
        assertEquals("{A}", list.toString());
        list.add("B");
        assertEquals("{A, B}", list.toString());
    }


    /**
     * test the iterator next and has next methods for functionality
     */
    public void testIterNext() {
        list.add("James");
        list.add("Jason");
        Iterator<String> iter = list.iterator();
        assertTrue(iter.hasNext());
        assertEquals(iter.next(), "James");
        assertTrue(iter.hasNext());
        assertEquals(iter.next(), "Jason");
        assertFalse(iter.hasNext());
        Exception exception = null;
        try {
            iter.next();
        }
        catch (NoSuchElementException e) {
            exception = e;
        }
        assertTrue(exception instanceof NoSuchElementException);
    }


    /**
     * test the iterator remove method for functionality
     */
    public void testIterRemove() {
        list.add("James");
        list.add("Jason");
        list.add("John");
        Iterator<String> iter = list.iterator();
        Exception exception = null;
        try {
            iter.remove();
        }
        catch (IllegalStateException e) {
            exception = e;
        }
        assertTrue(exception instanceof IllegalStateException);
        iter.next();
        iter.remove();
        assertEquals(list.size(), 2);
        iter.next();
        iter.remove();
        assertEquals(list.size(), 1);
        iter.next();
        iter.remove();
        assertEquals(list.size(), 0);
        assertTrue(list.isEmpty());
    }


    /**
     * Tests get when the index is greater than or equal to size and when the
     * index is less than zero
     */
    public void testGetException() {
        Exception exception = null;
        try {
            list.get(-1);
        }
        catch (Exception e) {
            exception = e;
        }
        assertTrue(exception instanceof IndexOutOfBoundsException);
        exception = null;
        list.add("A");
        try {
            list.get(1);
        }
        catch (IndexOutOfBoundsException e) {
            exception = e;
        }
        assertTrue(exception instanceof IndexOutOfBoundsException);
    }


    /**
     * testSwap will test the swap method to make sure it acts correctly
     */
    public void testSwap() {
        list.add("James");
        list.add("Jason");
        list.add("John");
        list.swap(0, 2);
        assertEquals(list.get(0), "John");
        assertEquals(list.get(1), "Jason");
        assertEquals(list.get(2), "James");
        Exception exception = null;
        try {
            list.swap(-1, 0);
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }
        assertTrue(exception instanceof IllegalArgumentException);
        exception = null;
        try {
            list.swap(-1, 3);
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }
        assertTrue(exception instanceof IllegalArgumentException);
        exception = null;
        try {
            list.swap(0, 3);
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }
        assertTrue(exception instanceof IllegalArgumentException);
    }

}
