package ru.sbt.mipt.oop.home.alarm;

public interface State {
    enum StateType {
        ACTIVATED, DEACTIVATED, ALARM
    }

    StateType getType();

    void activate(String code);
    void deactivate(String code);
    void alarm();
}
