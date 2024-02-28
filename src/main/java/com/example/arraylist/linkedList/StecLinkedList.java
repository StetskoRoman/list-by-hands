package com.example.arraylist.linkedList;

import com.example.arraylist.StecList;
import com.example.arraylist.arrayList.StecArrayList;
import com.example.arraylist.collections.StecIterator;

import java.util.Collection;
import java.util.Objects;

/**
 * I guess through the writing of this class we can understand how useless and broken linkedList is
 *
 * @param <E>
 */
public class StecLinkedList<E> implements StecList<E> {

    /**
     * Pointer to the first node.
     */
    private Node<E> first;
    /**
     * Pointer to the last node.
     */
    private Node<E> last;
    /**
     * Size of list.
     */
    private int currentSize = 0;

    public StecLinkedList() {
    }

    /**
     * Adds the specified element to the beginning of the linked list.
     * @param e the element to be added
     */
    public void addFirst(E e) {
        final Node<E> firstNode = first;
        final Node<E> newNode = new Node<>(null, e, firstNode);
        first = newNode;
        if (firstNode == null) {
            last = newNode;
        } else {
            firstNode.prev = newNode;
        }

        currentSize++;
    }

    /**
     * Appends the specified element to the end of the linked list.
     * @param e the element to be added
     * @return {@code true} (as specified by the {@link Collection#add} method)
     */
    @Override
    public boolean add(E e) {
        final Node<E> prevNode = last;
        final Node<E> newNode = new Node<>(prevNode, e, null);
        last = newNode;
        if (prevNode == null) {
            first = newNode;
        } else {
            prevNode.next = newNode;
        }
        currentSize++;
        return true;
    }
    /**
     * Inserts the specified element at the specified position in the linked list.
     * @param index the index at which the specified element is to be inserted
     * @param e     the element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public void add(int index, E e) {
        checkIndex(index);

        Node<E> current = getNode(index);
        Node<E> pred = current.prev;
        Node<E> newNode = new Node<>(pred, e, current);
        current.prev = newNode;
        if (pred == null) {
            first = newNode;
        } else {
            pred.next = newNode;
        }

        currentSize++;
    }

    /**
     * Replaces the element at the specified position in the linked list with the specified element.
     * @param index the index of the element to be replaced
     * @param e     the element to be stored at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public void set(int index, E e) {
        checkIndex(index);
        Node<E> current = getNode(index);
        current.element = e;
    }

    /**
     * Deletes the element at the specified position in the linked list.
     * @param index the index of the element to be deleted
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public E delete(int index) {
        checkIndex(index);
        Node<E> currentNode = getNode(index);
        E e = get(index);
        Node<E> nextNode = currentNode.next;
        Node<E> prevNode = currentNode.prev;
        if (prevNode == null) {
            first = nextNode;
        } else {
            prevNode.next = nextNode;
            currentNode.prev = null;
        }

        if (nextNode == null) {
            last = prevNode;
        } else {
            nextNode.prev = prevNode;
            currentNode.next = null;
        }

        currentNode.element = null;
        currentSize--;
        return e;
    }

    /**
     * Returns the element at the specified position in the linked list.
     * @param index the index of the element to be retrieved
     * @return the element at the specified position in the linked list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public E get(int index) {
        checkIndex(index);
        return getNode(index).element;
    }

    /**
     * Returns the node at the specified position in the linked list.
     * @param index the index of the node to be retrieved
     * @return the node at the specified position in the linked list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public Node<E> getNode(int index) {
        checkIndex(index);
        if (index < currentSize / 2) {
            Node<E> currentNode = first;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.next;
            }
            return currentNode;
        } else {
            Node<E> currentNode = last;
            for (int i = currentSize - 1; i > index; i--) {
                currentNode = currentNode.prev;
            }
            return currentNode;
        }
    }

    /**
     * Supportive method. Checks if the specified index is within the range of the linked list.
     * @param index the index to be checked
     * @return {@code true} if the index is within the range of the linked list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    private boolean checkIndex(int index) {
        if (index < 0 || index >= currentSize) {
            throw new IndexOutOfBoundsException("Index " + index + " out of size " + currentSize);
        }
        return true;
    }

    /**
     * Returns the number of elements in the linked list.
     * @return the number of elements in the linked list
     */
    @Override
    public int size() {
        return currentSize;
    }

    /**
     * Removes all elements from the linked list.
     */
    @Override
    public void clean() {

        for (Node<E> current = first; current != null; ) {
            Node<E> next = current.next;
            current.element = null;
            current.next = null;
            current.prev = null;
            current = next;
        }
        first = last = null;
        currentSize = 0;
    }

    /**
     * sorting array.
     */
    @Override
    public void sort() {
        StecList<E> stecArrayList = new StecArrayList(currentSize);
        for (int i = 0; i < currentSize; i++) {
            stecArrayList.add(getNode(i).element);
        }
        stecArrayList.sort();

        int count = 0;
        for (int i = 0; i < currentSize; i++) {
            this.set(count, stecArrayList.get(count));
            count++;
        }
    }

    /**
     * Just for working forEach
     * looks like not good realisation
     * @return custom iterator
     */
    @Override
    public StecIterator<E> iterator() {
        E[] array = (E[]) new Object[currentSize];
        for (int i = 0; i < currentSize; i++) {
            array[i] = getNode(i).element;
        }
        return new StecIterator<>(array);
    }


    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node<?> node)) return false;
            return Objects.equals(element, node.element) && Objects.equals(next, node.next) && Objects.equals(prev, node.prev);
        }

        @Override
        public int hashCode() {
            return Objects.hash(element, next, prev);
        }
    }
}
