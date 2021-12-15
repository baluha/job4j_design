package ru.job4j.serialization.json;

public class Owner {
    private final String name;
    private final String phone;

    public Owner(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Owner{"
                + "name='" + name + '\''
                + ", phone='" + phone + '\''
                + '}';
    }
}
