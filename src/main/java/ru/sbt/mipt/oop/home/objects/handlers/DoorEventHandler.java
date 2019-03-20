package ru.sbt.mipt.oop.home.objects.handlers;

import ru.sbt.mipt.oop.home.objects.action.DoorAction;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.EventHandler;
import ru.sbt.mipt.oop.home.event.sensor.SensorEvent;

import static ru.sbt.mipt.oop.home.event.sensor.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.home.event.sensor.SensorEventType.DOOR_OPEN;

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