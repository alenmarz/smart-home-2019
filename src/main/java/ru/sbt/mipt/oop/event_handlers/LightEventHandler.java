package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.action.LightAction;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightEventHandler implements EventHandler {
    private SmartHome smartHome;

    public LightEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(Object event) {
        if (!isLightEvent(event)) return;
        smartHome.execute(new LightAction(event));
    }

    private boolean isLightEvent(Object event) {
        SensorEvent sensorEvent = (SensorEvent) event;
        return sensorEvent.getType() == LIGHT_ON || sensorEvent.getType() == LIGHT_OFF;
    }
}
