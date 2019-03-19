package ru.sbt.mipt.oop.home.alarm;

public class DeactivatedState extends State {
    private StateType type = StateType.DEACTIVATED;

    public DeactivatedState(Alarm alarm) {
        super(alarm);
    }

    @Override
    public void activate(String code) {
        if (alarm.checkCode(code)) {
            alarm.changeState(new ActivatedState(alarm));
        }
    }

    @Override
    public void deactivate(String code) {}

    @Override
    public void setAlarmState() {
        alarm.changeState(new AlarmState(alarm));
    }

    @Override
    public StateType getType() {
        return type;
    }
}
