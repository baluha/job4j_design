package ru.job4j.serialization.jsonlooping;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.serialization.json.Car;
import ru.job4j.serialization.json.Owner;

import java.util.ArrayList;
import java.util.List;

public class B {
    private A a;

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        a.setB(b);
        b.setA(a);
        System.out.println(new JSONObject(b));

        JSONObject jsonOwner = new JSONObject("{\"name\":\"Name\","
                + "\"phone\":\"+71112222222\""
                + "}");

        List<String> prevOwners = new ArrayList<>();
        prevOwners.add("Ivanov");
        prevOwners.add("Petrov");
        JSONArray owners = new JSONArray(prevOwners);
        final Car car = new Car(true, 4, "Opel",
                new Owner("Sergey", "03"), new String[] {"Bla", "Bla"});

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isWorking", car.isWorking());
        jsonObject.put("numOfWheels", car.getNumOfWheels());
        jsonObject.put("manufacturer", car.getNumOfWheels());
        jsonObject.put("owner", jsonOwner);
        jsonObject.put("surnamesPrevOwners", prevOwners);

        System.out.println(jsonObject.toString());

        System.out.println(new JSONObject(car).toString());
    }
}
