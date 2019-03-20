package ru.sbt.mipt.oop.home.alarm;

public class DeactivatedState implements State {
    private Alarm alarm;

    public DeactivatedState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
        if (alarm.getAlarmSystem().checkCode(code)) {
            alarm.getAlarmSystem().changeState(new ActivatedState(alarm));
        }
    }

    @Override
    public void deactivate(String code) {}

    @Override
    public void alarm() {
        alarm.getAlarmSystem().changeState(new AlarmState(alarm));
    }

    @Override
    public StateType getType() {
        return StateType.DEACTIVATED;
    }
}
