package ru.sbt.mipt.oop.home.objects.handlers;

import ru.sbt.mipt.oop.home.objects.action.LightAction;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.EventHandler;
import ru.sbt.mipt.oop.home.event.sensor.SensorEvent;

import static ru.sbt.mipt.oop.home.event.sensor.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.home.event.sensor.SensorEventType.LIGHT_ON;

public class LightEventHandler extends EventHandler {

    public LightEventHandler(SmartHome smartHome) {
        super(smartHome);
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
