package com.example.arraylist.collections;

import java.util.Iterator;

public class StecIterator<E> implements Iterator<E> {

    private int index = 0;
    private E[] array;


    public StecIterator(E[] array) {
        this.array = array;
    }


    @Override
    public boolean hasNext() {
        return index < array.length;
    }

    @Override
    public E next() {
        return array[index++];
    }

}
