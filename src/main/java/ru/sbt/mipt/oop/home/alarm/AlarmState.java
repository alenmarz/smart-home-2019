package ru.sbt.mipt.oop.home.alarm;

public class AlarmState extends State {
    private StateType type = StateType.ALARM;

    public AlarmState(Alarm alarm) {
        super(alarm);
    }

    @Override
    public void activate(String code) {
        if (alarm.checkCode(code)) {
            alarm.changeState(new ActivatedState(alarm));
        }
    }

    @Override
    public void deactivate(String code) {
        if (alarm.checkCode(code)) {
            alarm.changeState(new DeactivatedState(alarm));
        } else {
            alarm.changeState(new AlarmState(alarm));
        }
    }

    @Override
    public void setAlarmState() {}

    @Override
    public StateType getType() {
        return type;
    }
}
