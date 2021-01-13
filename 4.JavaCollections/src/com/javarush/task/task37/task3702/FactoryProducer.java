package com.javarush.task.task37.task3702;

import com.javarush.task.task37.task3702.female.FemaleFactory;
import com.javarush.task.task37.task3702.male.MaleFactory;

public class FactoryProducer {
    public static AbstractFactory getFactory(HumanFactoryType humanFactoryType) {
        AbstractFactory factory = null;
        if (humanFactoryType.equals(HumanFactoryType.MALE)) {
            factory = new MaleFactory();
        } else if (humanFactoryType.equals(HumanFactoryType.FEMALE)) {
            factory= new FemaleFactory();
        }
        return factory;
    }
    public static enum HumanFactoryType {
        MALE,
        FEMALE,
    }
}
