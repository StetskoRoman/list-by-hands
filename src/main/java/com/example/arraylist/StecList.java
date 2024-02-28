package com.example.arraylist;

public interface StecList<E> extends Iterable<E> {

    boolean add(E e);

    void add(int index, E e);

    void set(int index, E e);

    E delete(int index);

    E get(int index);

    int size();

    void clean();
}
