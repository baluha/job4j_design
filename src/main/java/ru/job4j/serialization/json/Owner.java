package ru.job4j.serialization.json;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "owner")
@XmlAccessorType(XmlAccessType.FIELD)
public class Owner {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String phone;

    public Owner(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Owner() {
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
