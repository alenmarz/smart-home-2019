package ru.sbt.mipt.oop.home.alarm;

import ru.sbt.mipt.oop.configuration.Configuration;

public class Alarm implements State {
    private AlarmSystem alarmSystem;

    public Alarm(Configuration configuration) {
        alarmSystem = new AlarmSystem(configuration, this);
    }

    @Override
    public void activate(String code) {
        alarmSystem.getState().activate(code);
    }

    @Override
    public void deactivate(String code) {
        alarmSystem.getState().deactivate(code);
    }

    @Override
    public void alarm() {
        getAlarmSystem().getState().alarm();
    }

    @Override
    public StateType getType() {
        return getAlarmSystem().getState().getType();
    }

    public AlarmSystem getAlarmSystem() {
        return alarmSystem;
    }
}
