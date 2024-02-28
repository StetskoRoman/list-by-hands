package com.example.arraylist.linkedList;

public class RunnerLinkedList {

    public static void main(String[] args) {

        StecLinkedList<Integer> linkedList = new StecLinkedList<>();

        linkedList.add(5);
        linkedList.add(3);
        linkedList.add(9);
        linkedList.set(1, 15);
        linkedList.set(0,0);

        for (Integer integer : linkedList) {
            System.out.print(integer + " ");
        }

        System.out.println( "\nchecked add, set, and get. get(2) = " + linkedList.get(2));
        System.out.println("\nchecked size = " + linkedList.size());

        linkedList.clean();
        System.out.println("\nchecked clean(): size = " + linkedList.size());

        linkedList.add(10);
        linkedList.add(12);
        linkedList.add(0, 15);
        linkedList.add(2, 115);
        linkedList.addFirst(11111);

        System.out.println("\nadd in middle and 0 checked: " );
        for (Integer integer : linkedList) {
            System.out.print(integer + " ");
        }

        linkedList.delete(2);
        System.out.println("\n\ndeleting for index 2 checked: " );
        for (Integer integer : linkedList) {
            System.out.print(integer + " ");
        }

        linkedList.sort();
        System.out.println("\n\nsorted: ");
        for (Integer integer : linkedList) {
            System.out.print(integer + " ");
        }

    }
}
