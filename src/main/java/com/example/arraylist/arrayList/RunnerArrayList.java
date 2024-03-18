package com.example.arraylist.arrayList;

import com.example.arraylist.collections.StecCollections;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Objects;

public class RunnerArrayList {

    public static void main(String[] args) {

        StecArrayList<Integer> list = new StecArrayList<>();

        list.add(15);
        list.add(12);
        list.add(10);
        list.add(11);
        list.add(10);
        list.add(100);
        list.add(150);
        list.add(200);
        list.add(1050);
        list.add(25);
        list.add(101);

        System.out.println("add in the end checked, with extending capacity: \n" + list + " size = " + list.size());
        System.out.println("\nget checked (index 1): " + list.get(1));

        list.set(1, 555);
        System.out.println("set checked (index 1): " + list.get(1));

        StecArrayList<Ways> waysStecArrayList = new StecArrayList<>(6);
        waysStecArrayList.add(new Ways(0l, 0));
        waysStecArrayList.add(1, new Ways(1l, 15));
        waysStecArrayList.add(0, new Ways(10l, 1050));
        waysStecArrayList.add(new Ways( 1l,11));
        waysStecArrayList.add(2, new Ways(10000l, 11111));

        System.out.println("\nadding in the middle with capacity increasing checked, custom Class realised\n" +  waysStecArrayList);

        StecCollections.sort(waysStecArrayList);
        System.out.println("\ncustom sort checked\n" +  waysStecArrayList + " size = " + waysStecArrayList.size());

        waysStecArrayList.delete(3);
        System.out.println("\ndeleting checked \n" + waysStecArrayList + " size = " + waysStecArrayList.size());

        waysStecArrayList.clean();
        System.out.println("\nclean checked: " + waysStecArrayList + " size = " + waysStecArrayList.size());

        System.out.println("\nCustom sort array");
        StecCollections.sort(list);
        System.out.println("sorted = " + list);

        System.out.println("\nforEach checked, without null");
        for (Integer integer : list) {
            System.out.print(integer + "  ");
        }

        Ways waysStrong = new Ways(1, 5);
//        Ways other = waysStrong;
        WeakReference<Ways> weakReference = new WeakReference<>(waysStrong);
        SoftReference<Ways> softReference = new SoftReference<>(waysStrong);
//        ReferenceQueue<Ways> referenceQueue = new ReferenceQueue<>();
//        PhantomReference<Ways> phantomReference = new PhantomReference<Ways>(waysStrong, referenceQueue);
        waysStrong = null;
        System.out.println("\n strong " + waysStrong);
        System.out.println("weak " + weakReference.get() + "  " + weakReference.getClass());

//        System.out.println(" weak " + weakReference.get());
        System.out.println(" soft " + softReference.get());
//        System.out.println(" phantom " + phantomReference.get() + "  " + phantomReference + "   " );

    }

    /**
     * Custom class for checking how it works
     */
    static class Ways implements Comparable<Ways> {

        long cost;
        int town;

        public Ways(long cost, int town) {
            this.cost = cost;
            this.town = town;
        }

        @Override
        public String toString() {
            return "Ways{" +
                    "cost=" + cost +
                    ", town=" + town +
                    '}';
        }


        @Override
        public int compareTo(Ways o) {
            if (this.cost == o.cost) {
                if (this.town == o.town) {
                    return 0;
                }
                if (this.town < o.town) {
                    return -1;
                } else {
                    return 1;
                }
            }
            if (this.cost < o.cost) {
                return -1;
            } else {
                return 1;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Ways ways)) return false;
            return cost == ways.cost && town == ways.town;
        }

        @Override
        public int hashCode() {
            return Objects.hash(cost, town);
        }
    }

}
