package ru.job4j;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Double> lst = new ArrayList<>();

        lst.add(3.15);
        lst.add(3.15);
        lst.add(3.15);
        System.out.println(sum(lst));
    }

    public static double sum(List<? extends Number> list) {
        double sum = 0;
        for (Number num : list) {
            sum += num.doubleValue();
        }
        return sum;
    }
}
