package ru.sbt.mipt.oop.home.alarm.handlers;

import ru.sbt.mipt.oop.home.EventHandler;
import ru.sbt.mipt.oop.home.alarm.Alarm;
import ru.sbt.mipt.oop.home.alarm.AlarmSystem;
import ru.sbt.mipt.oop.home.alarm.event.AlarmEvent;
import ru.sbt.mipt.oop.home.alarm.event.AlarmEventType;


public class AlarmEventHandler implements EventHandler {
    private Alarm alarm;

    AlarmEventHandler(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void handleEvent(Object object) {
        AlarmEvent event = (AlarmEvent) object;
        AlarmEventType type = event.getType();

        if (type == AlarmEventType.ALARM_ACTIVATE) {
            alarm.activate(event.getCode());
        } else if (type == AlarmEventType.ALARM_DEACTIVATE) {
            alarm.deactivate(event.getCode());
        }
    }
}
