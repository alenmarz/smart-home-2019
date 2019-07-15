package ru.sbt.mipt.oop.home.event.sensor;

import com.coolcompany.smarthome.events.SensorEventsManager;
import ru.sbt.mipt.oop.home.event.EventProvider;

import java.util.ArrayList;
import java.util.Collection;

public class CCSensorEventsManagerAdapter implements EventProvider {
    private SensorEventsManager sensorEventsManager;

    public CCSensorEventsManagerAdapter(SensorEventsManager sensorEventsManager) {
        this.sensorEventsManager = sensorEventsManager;
    }

    @Override
    public Collection<Object> getEvents() {
        Collection<Object> events = new ArrayList<>();
        sensorEventsManager.registerEventHandler(event -> {
            if (getType(event.getEventType()) != null) {
                events.add(new SensorEvent(
                        getType(event.getEventType()),
                        event.getObjectId()
                ));
            }
        });
        sensorEventsManager.start();
        return events;
    }

    private SensorEventType getType(String ccEventType) {
        switch (ccEventType) {
            case "LightIsOn":
                return SensorEventType.LIGHT_ON;
            case "LightIsOff":
                return SensorEventType.LIGHT_OFF;
            case "DoorIsOpen":
                return SensorEventType.DOOR_OPEN;
            case "DoorIsClosed":
                return SensorEventType.DOOR_CLOSED;
        }
        return null;
    }
}
