package com.example.arraylist.collections;

import java.util.Iterator;
import java.util.function.Consumer;

public class StecIterator<E> implements Iterator<E> {

    private int index = 0;
    private E[] array;


    public StecIterator(E[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return (index < array.length && array[index]!=null) ? true : false;
    }

    @Override
    public E next() {
        return array[index++];
    }

    @Override
    public void forEachRemaining(Consumer<? super E> action) {
        final int currentSize = array.length;
        final E[] elements = (E[]) new Object[currentSize];
        for (int i = 0; i < currentSize; i++) {
            elements[i] = array[i];
        }
        for (int i = 0; i < currentSize; i++) {
            action.accept(elementAt(elements, i));
        }
    }


    static <E> E elementAt(Object[] es, int index) {
        return (E) es[index];
    }
}
