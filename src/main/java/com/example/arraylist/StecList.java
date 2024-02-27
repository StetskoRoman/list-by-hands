package com.example.arraylist;

public interface StecList<E> extends Iterable {
    /**
     * add
     * @param e
     * @return
     */
    boolean add(E e);

//    boolean addInMiddle(int index, E e);

    void set(int index, E e);

    E delete(int index);

    E get(int index);

    int size();

    void clean();
}
