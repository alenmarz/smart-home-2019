package ru.sbt.mipt.oop.home.event.sensor;

import ru.sbt.mipt.oop.home.event.EventProvider;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class RandomSensorEventProvider implements EventProvider {

    @Override
    public Collection<Object> getEvents() {
        Collection<Object> events = new ArrayList<>();
        SensorEvent event;
        while ((event = getNextEvent()) != null) {
            events.add(event);
        }
        return events;
    }

    private SensorEvent getNextEvent() {
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}