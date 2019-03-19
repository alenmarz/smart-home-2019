package ru.sbt.mipt.oop.home.alarm;

public class ActivatedState extends State {
    private StateType type = StateType.ACTIVATED;

    public ActivatedState(Alarm alarm) {
        super(alarm);
    }

    @Override
    public void activate(String code) {}

    @Override
    public void deactivate(String code) {
        if (alarm.checkCode(code)) {
            alarm.changeState(new DeactivatedState(alarm));
        } else {
            alarm.changeState(new AlarmState(alarm));
        }
    }

    @Override
    public void setAlarmState() {
        alarm.changeState(new AlarmState(alarm));
    }

    @Override
    public StateType getType() {
        return type;
    }
}
