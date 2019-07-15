package ru.sbt.mipt.oop.home.alarm.notification;

public class SMS implements Notification {
    @Override
    public void send(String message) {
        System.out.println("SMS: " + message);
    }
}
