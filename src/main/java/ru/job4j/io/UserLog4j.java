package ru.job4j.io;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class UserLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        BasicConfigurator.configure();
        char sex = 'm';
        short age = 30;
        double high = 1.9;
        byte course = 2;
        long salary = 120000;
        float something = 1.2f;
        int jumpHigh = 3;
        boolean isWorking = true;

        LOG.debug("User info : sex : {}, age {}, high {}, course {},"
                        + " salary {}, something {}, jumpHigh {}, isWorking {}", sex, age, high, course, salary,
                something, jumpHigh, isWorking);
    }
}
