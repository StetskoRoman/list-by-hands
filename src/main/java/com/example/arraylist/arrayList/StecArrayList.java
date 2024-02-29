package com.example.arraylist.arrayList;

import com.example.arraylist.StecList;
import com.example.arraylist.collections.StecIterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

/**
 * Assume that Collections in java do not exist
 * @param <E> the type of elements in this list
 */
public class StecArrayList<E> implements StecList<E> {

    /**
     * work array with elements
     */
    private E[] array;

    /**
     * in case when you want to create an array with 0 size.
     */
    private static final Object[] EMPTY_ARRAY = {};

    /**
     * actual size of an array.
     */
    private int currentSize = 0;

    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * 1.616 with default capacity = 10 the last max index will be close enough to Integer.MAX_VALUE, and to the golden ratio rule
     */
    private final double EXTENDING_VALUE = 1.616;

    private int capacity;

    /**
     * Constructs an empty list with an initial capacity of ten.
     */
    public StecArrayList() {
        this.array = (E[]) new Object[DEFAULT_CAPACITY];
        this.capacity = DEFAULT_CAPACITY;
    }
    /**
     * Constructs an empty list with an initial capacity.
     */
    public StecArrayList(int capacity) {
        if (capacity > 0) {
            this.array = (E[]) new Object[capacity];
            this.capacity = capacity;
        } else if (capacity == 0) {
            this.array = (E[]) EMPTY_ARRAY;
            this.capacity = 0;
        } else {
            throw new IllegalArgumentException("Wrong capacity: " + capacity);
        }
    }

    /**
     * Adds the specified element to the end of the array list.
     * @param e the element to be added
     * @return true if the element is successfully added, false otherwise
     */
    @Override
    public boolean add(E e) {
        if (currentSize < capacity) {
            array[currentSize] = e;
            currentSize++;
            return true;
        }
        if (currentSize == capacity) {
            capacity = (int) (currentSize * EXTENDING_VALUE);
            E[] futureArray = getEs(e,0,0, currentSize);
            array = futureArray;
            currentSize++;
            return true;
        }
        return false;
    }

    /**
     * Inserts the specified element at the specified position in the array list.
     * @param index the index at which the specified element is to be inserted
     * @param e the element to be inserted
     * @throws IllegalArgumentException if the index is greater than the current size of the array list
     */
    @Override
    public void add(int index, E e) {
        if (index > currentSize) {
            throw new IllegalArgumentException("You try add element beyond array area, current area is: " + currentSize);
        }

        if (currentSize < capacity) {
            System.arraycopy(array, index, array, index + 1, currentSize - index);
            array[index] = e;
            currentSize++;
        } else if (currentSize == capacity) {
            capacity = (int) (currentSize * EXTENDING_VALUE);
            E[] futureArray = getEs(e,0,0, index);
            System.arraycopy(array, index, futureArray,index+1, currentSize -index);
            futureArray[index] = e;
            array = futureArray;
            currentSize++;
        }
    }

    /**
     * method getEs for increasing capacity (just for it!) of array, must be used no more than once in each method
     * @param e array in which we will work
     * @param start where in old array we start copy elements
     * @param startFuture where in new array we start put elements
     * @param len length, how many elements we will copy
     * @return array of elements with extended capacity
     */
    private E[] getEs(E e, int start, int startFuture, int len) {
//        в другом случае происходит ClassCastException, здесь же работает
        E[] futureArray = (E[]) new Object[capacity];
        System.arraycopy(array, start, futureArray, startFuture, len);
        futureArray[currentSize] = e;
        return futureArray;
    }


    /**
     * Removes the element at the specified position in the array list.
     * @param index the index of the element to be removed
     * @return the element that was removed from the array list
     * @throws IllegalArgumentException if the index is greater than or equal to the current size of the array list
     */
    @Override
    public E delete(int index) {
        if (index >= currentSize) {
            throw new IllegalArgumentException("you try to remove element by index that doesn't exist");
        }
        E deletedObject = array[index];
        System.arraycopy(array, index + 1, array, index, currentSize - index);
        currentSize--;
        return deletedObject;
    }

    /**
     * Returns the element at the specified position in the array list.
     * @param index the index of the element to be returned
     * @return the element at the specified position in the array list
     */
    @Override
    public E get(int index) {
        return array[index];
    }

    /**
     * Returns the number of elements in the array list.
     * @return the number of elements in the array list
     */
    @Override
    public int size() {
        return currentSize;
    }

    /**
     * Replaces the element at the specified position in the array list with the specified element.
     * @param index the index of the element to be replaced
     * @param e the element to be stored at the specified position
     */
    @Override
    public void set(int index, E e) {
        array[index] = e;
    }

    /**
     * Removes all of the elements from the array list.
     */
    @Override
    public void clean() {
        array = (E[]) new Object[DEFAULT_CAPACITY];
        currentSize = 0;
    }

    /**
     * sorting array.
     */
    @Override
    public void sort() {
        Arrays.sort(array, 0, currentSize);
    }

    /**
     * return custom iterator, with that forEach will work correctly.
     */
    @Override
    public Iterator iterator() {
        return new StecIterator(array);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < currentSize; i++) {
            if (i == currentSize - 1) {
                stringBuilder.append(array[i]+"]");
                break;
            }
            stringBuilder.append(array[i] + " ");
        }

        return stringBuilder.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StecArrayList<?> that)) return false;
        return Double.compare(that.EXTENDING_VALUE, EXTENDING_VALUE) == 0 && capacity == that.capacity && Arrays.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(EXTENDING_VALUE, capacity);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }
}
