package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightEventHandler implements SensorEventHandler {
    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent event) {
        if (!isLightEvent(event)) return;

        for (Room room : smartHome.getRooms()) {
            Light light = room.getLightByEventId(event.getObjectId());
            changeLightState(room, light, event.getType() == LIGHT_ON);
        }
    }

    private boolean isLightEvent(SensorEvent event) {
        return event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF;
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
