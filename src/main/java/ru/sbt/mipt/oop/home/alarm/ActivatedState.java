package ru.sbt.mipt.oop.home.alarm;

public class ActivatedState implements State {
    private Alarm alarm;

    public ActivatedState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {}

    @Override
    public void deactivate(String code) {
        if (alarm.getAlarmSystem().checkCode(code)) {
            alarm.getAlarmSystem().changeState(new DeactivatedState(alarm));
        } else {
            alarm.getAlarmSystem().changeState(new AlarmState(alarm));
        }
    }

    @Override
    public void alarm() {
        alarm.getAlarmSystem().changeState(new AlarmState(alarm));
    }

    @Override
    public StateType getType() {
        return StateType.ACTIVATED;
    }
}
