package ru.job4j.serialization.xml;

import ru.job4j.serialization.json.Car;
import ru.job4j.serialization.json.Owner;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        Car car = new Car(true, 4, "Opel",
                new Owner("Ivan", "03"), new String[]{"Petrov", "Sidorov"});
        JAXBContext context = JAXBContext.newInstance(Car.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";

        try (StringWriter stringWriter = new StringWriter()) {
            marshaller.marshal(car, stringWriter);
            xml = stringWriter.getBuffer().toString();
            System.out.println(xml);
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader stringReader = new StringReader(xml)) {
            Car result = (Car) unmarshaller.unmarshal(stringReader);
            System.out.println(result);
        }
    }
}
