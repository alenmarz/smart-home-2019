package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightEventHandler implements EventHandler {
    private SmartHome smartHome;

    public LightEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(Object event) {
        SensorEvent sensorEvent = (SensorEvent) event;
        if (!isLightEvent(sensorEvent)) return;

        for (Room room : smartHome.getRooms()) {
            Light light = room.getLightById(sensorEvent.getObjectId());
            if (light != null) {
                changeLightState(room, light, sensorEvent.getType() == LIGHT_ON);
            }
        }
    }

    private boolean isLightEvent(Object event) {
        SensorEvent sensorEvent = (SensorEvent) event;
        return sensorEvent.getType() == LIGHT_ON || sensorEvent.getType() == LIGHT_OFF;
    }

    private void changeLightState(Room room, Light light, boolean turnedOn) {
        light.setOn(turnedOn);
        sayEventMessage(light, room, turnedOn);
    }

    private void sayEventMessage(Light light, Room room, boolean turnedOn) {
        String stateWord = turnedOn ? "on" : "off";
        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned " + stateWord);
    }
}
