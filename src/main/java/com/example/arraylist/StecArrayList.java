package com.example.arraylist;

import java.util.*;

public class StecArrayList<E> implements StecList<E> {

    private E[] array;

    private static final Object[] EMPTY_ARRAY = {};

    /**
     * count mark first null value in array that have never filled.
     */
    protected int count = 0;


    /**
     *
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * 1.616 with default capacity = 10 the last max index will be close enough to Integer.MAX_VALUE, and to the golden ratio
     */
    private final double EXTENDING_VALUE = 1.616;

    private int capacity;

    /**
     * Constructors
     */
    public StecArrayList() {
        this.array = (E[]) new Object[DEFAULT_CAPACITY];
        this.capacity = DEFAULT_CAPACITY;
    }

    public StecArrayList(int capacity) {
        if (capacity > 0) {
            this.array = (E[]) new Object[capacity];
            this.capacity = capacity;
        } else if (capacity == 0) {
            this.array = (E[]) EMPTY_ARRAY;
            this.capacity = DEFAULT_CAPACITY;
        } else {
            throw new IllegalArgumentException("Wrong capacity: " + capacity);
        }
    }

    @Override
    public boolean add(E e) {
        if (count < capacity) {
            array[count] = e;
            count++;
            return true;
        }
        if (count == capacity) {
            capacity = (int) (count * EXTENDING_VALUE);
            E[] futureArray = getEs(e,0,0, count);
            array = futureArray;
            count++;
            return true;
        }
        return false;
    }

    /**
     * method getEs for increasing capacity (just for it!) of array, must be used no more than once in each method
     * @param e - array in which we will work
     * @param start - where in old array we start copy elements
     * @param startFuture - where in new array we start put elements
     * @param len - length, how many elements we will copy
     * @return array of elements with extended capacity
     */
    private E[] getEs(E e, int start, int startFuture, int len) {
        E[] futureArray = (E[]) new Object[capacity];
        System.arraycopy(array, start, futureArray, startFuture, len);
        futureArray[count] = e;
        return futureArray;
    }

    public void add(int index, E e) {
        if (index > count) {
            throw new IllegalArgumentException("You try add element beyond array area, current area is: " + count);
        }

        if (count < capacity) {
            System.arraycopy(array, index, array, index + 1, count - index);
            array[index] = e;
            count++;
        } else if (count == capacity) {
            capacity = (int) (count * EXTENDING_VALUE);
            E[] futureArray = getEs(e,0,0, index);
            System.arraycopy(array, index, futureArray,index+1, count-index);
            futureArray[index] = e;
            array = futureArray;
            count++;
        }

    }

    @Override
    public E delete(int index) {
        if (index >= count) {
            throw new IllegalArgumentException("you try to remove element by index that doesn't exist");
        }
        E deletedObject = array[index];
        System.arraycopy(array, index + 1, array, index, count - index);
        count--;
        return deletedObject;
    }

    @Override
    public E get(int index) {
        return array[index];
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public void set(int index, E e) {
        array[index] = e;
    }

    @Override
    public void clean() {
        array = (E[]) new Object[DEFAULT_CAPACITY];
        count = 0;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    /**
     * for debugging purpose I show all buckets including null.
     * @return
     */
    @Override
    public String toString() {
        return "StecArrayList{" +
                "array=" + Arrays.toString(array) +
                '}';
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
