package ru.sbt.mipt.oop.home.alarm.event;

public class AlarmEvent {
    private final AlarmEventType type;
    private final String code;

    AlarmEvent(AlarmEventType type, String code) {
        this.type = type;
        this.code = code;
    }

    public AlarmEventType getType() {
        return type;
    }

    public String getCode() {
        return code;
    }
}
