package ru.job4j.generics;

public class Animal {
    private int age;
    private String sex;


    public Animal(int age, String sex) {
        this.age = age;
        this.sex = sex;
    }


    @Override
    public String toString() {
        return "Animal{"
                + "age=" + age
                + ", sex='" + sex + '\''
                + '}';
    }
}