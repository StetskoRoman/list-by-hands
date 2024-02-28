package com.example.arraylist.arrayList;

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

        System.out.println("add in the end checked, with extending capacity: " + list + " size = " + list.size());
        System.out.println("\nget checked: " + list.get(1));

        list.set(1, 555);
        System.out.println("\nset checked: " + list.get(1));

        list.clean();
        System.out.println("\nclean checked: " + list + " size = " + list.size() + " size = " + list.size());

        StecArrayList<Ways> waysStecArrayList = new StecArrayList<>();
        waysStecArrayList.add(new Ways(0l, 0));
        waysStecArrayList.add(1, new Ways(1l, 15));
        waysStecArrayList.add(0, new Ways(10l, 1050));
        waysStecArrayList.add(new Ways(9l, 0));
        waysStecArrayList.add(2, new Ways(10000l, 11111));

        System.out.println("\n Add in the middle with capacity increasing checked, custom Class realised");
        System.out.println(waysStecArrayList + "  size = " + waysStecArrayList.size() + "\n");

        waysStecArrayList.delete(3);
        System.out.println("deleting checked \n" + waysStecArrayList + " size = " + waysStecArrayList.size());

    }


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
