package ru.job4j.collection;

import ru.job4j.list.List;

import java.util.Iterator;

public class Test {
    public static void main(String[] args) {
        List<Integer> lst = new SimpleArrayList<>(5);
        lst.add(0);
        lst.add(1);
        lst.add(2);
        lst.add(3);
        lst.add(4);
        lst.add(5);
        lst.add(7);
        lst.add(8);
        lst.add(9);
        lst.add(10);
        lst.add(11);
        lst.add(12);
        lst.add(13);
        lst.set(0, 10);
        lst.remove(0);
        lst.remove(0);
        for (int i = 0; i < lst.size(); i++) {
            System.out.print(lst.get(i) + " ");
        }
        System.out.println();
        System.out.println(lst.get(10));
        Iterator<Integer> it = lst.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }

        System.out.println();
        for (Integer integer : lst) {
            System.out.print(integer + " ");
        }
    }
}
