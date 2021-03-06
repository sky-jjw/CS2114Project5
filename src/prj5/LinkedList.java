/*
 * Virginia Tech Honor Code Pledge:
 *
 * As a Hokie, I will conduct myself with honor
 * and integrity at all times.
 * I will not lie, cheat, or steal, nor will I
 * accept the actions of those who do.
 * -- James Mullen (mullenj)
 */
package prj5;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * This class is an implementation of a doubly linked list adapted in part from
 * the doubly linked list used in lab 10. This list will hold all of the songs
 * and their data. It will also provide iterator and comparator functionality
 * for iterating and sorts
 * 
 * @param <E>
 * @author Mark Wiggans (mmw125)
 * @version 3/29/15
 * @author Eric Williamson
 * @version 10/30/15
 * @author maellis1
 * @version 11/1/15
 * @author James Mullen mullenj
 * @version 04/15/2019
 */
public class LinkedList<E> implements Iterable<E> {
    /**
     * This represents a node in a doubly linked list. This node stores data, a
     * pointer to the node before it in the list, and a pointer to the node
     * after it in the list
     *
     * @param <E>
     *            This is the type of object that this class will store
     * @author Mark Wiggans (mmw125)
     * @version 4/14/2015
     * @author mullenj
     * @version 04/15/2019
     */
    private static class Node<E> {
        private Node<E> next;
        private Node<E> previous;
        private E data;


        /**
         * Creates a new node with the given data
         *
         * @param d
         *            the data to put inside the node
         */
        public Node(E d) {
            data = d;
        }


        /**
         * Sets the node after this node
         *
         * @param n
         *            the node after this one
         */
        public void setNext(Node<E> n) {
            next = n;
        }


        /**
         * Sets the node before this one
         *
         * @param n
         *            the node before this one
         */
        public void setPrevious(Node<E> n) {
            previous = n;
        }


        /**
         * Gets the next node
         *
         * @return the next node
         */
        public Node<E> next() {
            return next;
        }


        /**
         * Gets the node before this one
         *
         * @return the node before this one
         */
        public Node<E> previous() {
            return previous;
        }


        /**
         * Gets the data in the node
         *
         * @return the data in the node
         */
        public E getData() {
            return data;
        }


        /**
         * setData will allow us to set the data in the node
         * 
         * @param newData
         *            the new data for the node
         */
        public void setData(E newData) {
            this.data = newData;
        }
    }

    /**
     * How many nodes are in the list
     */
    private int size;

    /**
     * The first node in the list. THIS IS A SENTINEL NODE AND AS SUCH DOES NOT
     * HOLD ANY DATA. REFER TO init()
     */
    private Node<E> head;

    /**
     * The last node in the list. THIS IS A SENTINEL NODE AND AS SUCH DOES NOT
     * HOLD ANY DATA. REFER TO init()
     */
    private Node<E> tail;


    /**
     * Create a new DLList object.
     */
    public LinkedList() {
        init();
    }


    /**
     * Initializes the object to have the head and tail nodes
     */
    private void init() {
        head = new LinkedList.Node<E>(null);
        tail = new LinkedList.Node<E>(null);
        head.setNext(tail);
        tail.setPrevious(head);
        size = 0;
    }


    /**
     * Checks if the array is empty
     *
     * @return true if the array is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * Gets the number of elements in the list
     *
     * @return the number of elements
     */
    public int size() {
        return size;
    }


    /**
     * Removes all of the elements from the list
     */
    public void clear() {
        init();
    }


    /**
     * Gets the object at the given position
     *
     * @param index
     *            where the object is located
     * @return The object at the given position
     * @throws IndexOutOfBoundsException
     *             if there no node at the given index
     */
    public E get(int index) {
        return getNodeAtIndex(index).getData();
    }


    /**
     * Adds a element to the end of the list
     *
     * @param newEntry
     *            the element to add to the end
     */
    public void add(E newEntry) {
        add(size(), newEntry);
    }


    /**
     * Adds the object to the position in the list
     *
     * @param index
     *            where to add the object
     * @param obj
     *            the object to add
     * @throws IndexOutOfBoundsException
     *             if index is less than zero or greater than size
     * @throws IllegalArgumentException
     *             if obj is null
     */
    public void add(int index, E obj) {
        if (index < 0 || size < index) {
            throw new IndexOutOfBoundsException();
        }
        if (obj == null) {
            throw new IllegalArgumentException("Cannot add null "
                + "objects to a list");
        }

        Node<E> nodeAfter;
        if (index == size) {
            nodeAfter = tail;
        }
        else {
            nodeAfter = getNodeAtIndex(index);
        }

        Node<E> addition = new Node<E>(obj);
        addition.setPrevious(nodeAfter.previous());
        addition.setNext(nodeAfter);
        nodeAfter.previous().setNext(addition);
        nodeAfter.setPrevious(addition);
        size++;

    }


    /**
     * gets the node at that index
     * 
     * @param index
     * @return node at index
     */
    private Node<E> getNodeAtIndex(int index) {
        if (index < 0 || size() <= index) {
            throw new IndexOutOfBoundsException("No element exists at "
                + index);
        }
        Node<E> current = head.next(); // as we have a sentinel node
        for (int i = 0; i < index; i++) {
            current = current.next();
        }
        return current;
    }


    /**
     * Returns a string representation of the list If a list contains A, B, and
     * C, the following should be returned "{A, B, C}" (Without the quotations)
     *
     * @return a string representing the list
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{");
        if (!isEmpty()) {
            Node<E> currNode = head.next();
            while (currNode != tail) {
                E element = currNode.getData();
                builder.append(element.toString());
                if (currNode.next != tail) {
                    builder.append(", ");
                }
                currNode = currNode.next();
            }
        }

        builder.append("}");
        return builder.toString();
    }


    /**
     * swap will allow us to swap the data in two nodes to allow for easy sort
     * functionality
     * 
     * @param index1
     *            index of the first node to swap
     * @param index2
     *            index of the second node to swap
     */
    public void swap(int index1, int index2) {
        if (Math.max(index1, index2) < this.size() && Math.min(index1,
            index2) >= 0) {
            E data1 = this.getNodeAtIndex(index1).getData();
            E data2 = this.getNodeAtIndex(index2).getData();
            this.getNodeAtIndex(index1).setData(data2);
            this.getNodeAtIndex(index2).setData(data1);
        }
        else {
            throw new IllegalArgumentException(
                "Index is outside of allowed range");
        }
    }


    /**
     * 
     * DLListIterator will allow us to move forward and backwards through the
     * linked list
     *
     * @author James Mullen mullenj
     * @version 04/17/2019
     * @param <A> some data type
     */
    private class DLListIterator<A> implements ListIterator<E> {
        private Node<E> nextNode;
        private boolean nextCall;
        private int currIdx;


        /**
         * Creates a new DLListIterator
         */
        public DLListIterator() {
            nextNode = head.next();
            currIdx = 0;
        }


        /**
         * Checks if there are more elements in the list
         *
         * @return true if there are more elements in the list
         */
        @Override
        public boolean hasNext() {
            return nextNode.getData() != null;
        }
        
        /**
         * Checks if there are previous elements in the list
         *
         * @return true if there are previous elements in the list
         */
        @Override
        public boolean hasPrevious() {
            return nextNode.previous().getData() != null;
        }

        /**
         * Gets the next value in the list
         *
         * @return the next value
         * @throws NoSuchElementException
         *             if there are no nodes left in the list
         */
        @Override
        public E next() {
            if (this.hasNext()) {
                Node<E> returnNode = nextNode;
                nextNode = nextNode.next();
                nextCall = true;
                currIdx++;
                return returnNode.getData();
            }
            else {
                throw new NoSuchElementException(
                    "Illegal call to next() there are "
                        + "no more elements in the list");
            }
        }

        /**
         * Gets the previous value in the list
         *
         * @return the previous value
         * @throws NoSuchElementException
         *             if there are no nodes left in the list
         */
        @Override
        public E previous() {
            if (this.hasPrevious()) {
                nextNode = nextNode.previous();
                currIdx--;
                return nextNode.getData();
            }
            else {
                throw new NoSuchElementException(
                    "Illegal call to previous() there are "
                        + "no more elements in the list");
            }
        }


        /**
         * Removes the last object returned with next() from the list
         *
         * @throws IllegalStateException
         *             if next has not been called yet
         *             and if the element has already been removed
         */
        @Override
        public void remove() {
            if (nextCall) {
                nextCall = false;
                Node<E> tempNode = nextNode.previous();
                Node<E> prevNode = tempNode.previous();
                prevNode.setNext(nextNode);
                nextNode.setPrevious(prevNode);
                size--;
                currIdx--;
            }
            else {
                throw new IllegalStateException("Next has not been called");
            }
        }

        /**
         * @return the current index.
         */
        @Override
        public int nextIndex() {
            return currIdx;
        }

        /**
         * @return the previous index.
         */
        @Override
        public int previousIndex() {
            return currIdx - 1;
        }

        /**
         * Not going to be of use.
         */
        @Override
        public void set(E e) {
            // Not going to be implemented.            
        }

        /**
         * Not going to be of use.
         */
        @Override
        public void add(E e) {
            // Not going to be implemented.
            
        }
    }


    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Iterable#iterator()
     */
    @Override
    public ListIterator<E> iterator() {
        return new DLListIterator<E>();
    }
}
