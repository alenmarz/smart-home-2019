package ru.sbt.mipt.oop.home.alarm;

public class AlarmState implements State {
    private Alarm alarm;

    public AlarmState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
        if (alarm.getAlarmSystem().checkCode(code)) {
            alarm.getAlarmSystem().changeState(new ActivatedState(alarm));
        }
    }

    @Override
    public void deactivate(String code) {
        if (alarm.getAlarmSystem().checkCode(code)) {
            alarm.getAlarmSystem().changeState(new DeactivatedState(alarm));
        } else {
            alarm.getAlarmSystem().changeState(new AlarmState(alarm));
        }
    }

    @Override
    public void alarm() {}

    @Override
    public StateType getType() {
        return StateType.ALARM;
    }
}
