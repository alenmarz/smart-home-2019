package ru.sbt.mipt.oop.home.alarm;

public abstract class State {
    public enum StateType {
        ACTIVATED, DEACTIVATED, ALARM
    }

    protected Alarm alarm;

    public State(Alarm alarm) {
        this.alarm = alarm;
    }

    public abstract StateType getType();

    public abstract void activate(String code);
    public abstract void deactivate(String code);
    public abstract void setAlarmState();
}
