package ru.job4j.serialization.jsonlooping;

import org.json.JSONPropertyIgnore;

public class A {
    private B b;

    @JSONPropertyIgnore
    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }
}
