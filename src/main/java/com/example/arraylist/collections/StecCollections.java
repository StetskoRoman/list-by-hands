package com.example.arraylist.collections;

import com.example.arraylist.StecList;

import java.lang.reflect.Array;

public class StecCollections<E extends Comparable<E>> {
//не преобразовывается потому что это интерфейс? или дело в конструкторе, или в Comparable - Object Comparable не имеет же вроде, мб поэтому?
    private StecList<E> list;

    public StecCollections(StecList<E> list) {
        this.list = list;
    }

    public static <E extends Comparable<E>> void sort(StecList<E> list) {
        StecCollections<E> stecCollections = new StecCollections<>(list);
        E[] arr = stecCollections.listToArray();
        E[] sortArr = stecCollections.sortArray(arr);
        for (int i = 0; i < list.size(); i++) {
            list.set(i, sortArr[i]);
        }
    }

    /**
     * Sorts an array of elements in ascending order by recursive call mergeArray.
     * @param arrayA the array to be sorted
     * @return the sorted array in ascending order, or null if the input array is null
     */
    public E[] sortArray(E[] arrayA) {

        if (arrayA == null) {
            return null;
        }

        if (arrayA.length < 2) {
            return arrayA;
        }
        E[] arrayB = (E[]) Array.newInstance(list.get(0).getClass(), arrayA.length / 2);
//        do not understand why it works in StecArrayList, but not here, E opened in constructor, but how could I say that I need in method the same E that I have in constructor
//        E[] arrayB = (E[]) new Object[arrayA.length / 2];
        System.arraycopy(arrayA, 0, arrayB, 0, arrayA.length / 2);

//        E[] arrayC = (E[]) new Object[arrayA.length - arrayA.length / 2];
        E[] arrayC =(E[]) Array.newInstance(list.get(0).getClass(), arrayA.length - arrayA.length / 2);
        System.arraycopy(arrayA, arrayA.length / 2, arrayC, 0, arrayA.length - arrayA.length / 2);

        arrayB = sortArray(arrayB);
        arrayC = sortArray(arrayC);

        return mergeArray(arrayB, arrayC);
    }

    /**
     * Merges two sorted arrays into a single sorted array.
     * @param n the first sorted array
     * @param m the second sorted array
     * @return the merged sorted array
     */
    E[] mergeArray(E[] n, E[] m) {
        int size = n.length + m.length;
//        E[] nums = (E[]) new Object[size];
        E[] nums = (E[]) Array.newInstance(list.get(0).getClass(), size);
        int nCount = 0;
        int mCount = 0;
        int i = 0;
        while (i < size) {
            if (nCount == n.length) {
                for (int j = i; j < size; j++) {
                    nums[j] = m[mCount];
                    mCount++;
                }
                break;
            }
            if (mCount == m.length) {
                for (int j = i; j < size; j++) {
                    nums[j] = n[nCount];
                    nCount++;
                }
                break;
            }

            if (n[nCount].compareTo(m[mCount]) < 0) {
                nums[i] = n[nCount];
                nCount++;
                i++;

            } else {
                nums[i] = m[mCount];
                mCount++;
                i++;
            }

        }
        return nums;
    }

    /**
     * Converts the list to an array.
     * @return an array containing the elements of the list
     */
    public E[] listToArray() {
        E[] array = (E[]) Array.newInstance(list.get(0).getClass(), list.size());
//        E[] array = (E[]) new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

}
