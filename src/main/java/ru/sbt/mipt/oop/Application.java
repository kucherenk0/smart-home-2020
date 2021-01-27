package ru.sbt.mipt.oop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String... args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SmartHomeConfiguration.class);
        EventListener eventListener = context.getBean(EventListener.class);
        eventListener.run();
    }
}

