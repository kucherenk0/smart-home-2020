package ru.sbt.mipt.oop.utils;

public class SmsMessageSender implements MessageSender {

    public void send(String text) {
        System.out.println("Sending message: " + text);
    }
}
