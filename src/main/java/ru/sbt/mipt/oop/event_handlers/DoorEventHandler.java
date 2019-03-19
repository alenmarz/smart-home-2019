package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.action.DoorAction;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventHandler implements EventHandler {
    private SmartHome smartHome;

    public DoorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(Object event) {
        if (!isDoorEvent(event)) return;

        smartHome.execute(new DoorAction(event));
    }


    public boolean isDoorEvent(Object event) {
        SensorEvent sensorEvent = (SensorEvent) event;
        return sensorEvent.getType() == DOOR_OPEN || sensorEvent.getType() == DOOR_CLOSED;
    }
}