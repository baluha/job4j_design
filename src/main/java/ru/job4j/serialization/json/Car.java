package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Car {

    private final boolean isWorking;
    private final int numOfWheels;
    private final String manufacturer;
    private final Owner owner;
    private final String[] surnamesPrevOwners;

    public Car(boolean isWorking, int numOfWheels, String manufacturer, Owner owner, String[] surnamesPrevOwners) {
        this.isWorking = isWorking;
        this.numOfWheels = numOfWheels;
        this.manufacturer = manufacturer;
        this.owner = owner;
        this.surnamesPrevOwners = surnamesPrevOwners;
    }

    @Override
    public String toString() {
        return "Car{"
                + "isWorking=" + isWorking
                + ", numOfWheels=" + numOfWheels
                + ", manufacturer='" + manufacturer + '\''
                + ", owner=" + owner
                + ", surnamesPrevOwners=" + Arrays.toString(surnamesPrevOwners)
                + '}';
    }

    public static void main(String[] args) {
        Owner owner = new Owner("Name", "+71112222222");
        String[] surnames = {"Ivanov", "Petrov"};
        Car car = new Car(true, 4, "ВАЗ", owner, surnames);

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car));

        final String carJson =
                "{"
                        + "\"isWorking\":true,"
                        + "\"numOfWheels\":4,"
                        + "\"manufacturer\":\"ВАЗ\","
                        + "\"owner\":"
                        + "{"
                        + "\"name\":\"Name\","
                        + "\"phone\":\"+71112222222\""
                        + "},"
                        + "\"surnamesPrevOwners\":"
                        + "[\"Ivanov\",\"Petrov\"]"
                        + "}";
        final Car carMod = gson.fromJson(carJson, Car.class);
        System.out.println(carMod);
    }
}
