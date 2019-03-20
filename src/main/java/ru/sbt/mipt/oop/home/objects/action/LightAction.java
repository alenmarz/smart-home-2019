package ru.sbt.mipt.oop.home.objects.action;

import ru.sbt.mipt.oop.home.event.sensor.SensorEvent;
import ru.sbt.mipt.oop.home.objects.Light;

import static ru.sbt.mipt.oop.home.event.sensor.SensorEventType.LIGHT_ON;

public class LightAction implements Action {
    private SensorEvent event;

    public LightAction(Object event) {
        this.event = (SensorEvent) event;
    }

    @Override
    public void execute(Object object) {
        if (!(object instanceof Light)) return;

        Light light = (Light) object;
        if (light.getId().equals(event.getObjectId())) {
            changeLightState(light, event.getType() == LIGHT_ON);
        }
    }

    private void changeLightState(Light light, boolean turnedOn) {
        light.setOn(turnedOn);
        sayEventMessage(light, turnedOn);
    }

    private void sayEventMessage(Light light, boolean turnedOn) {
        String stateWord = turnedOn ? "on" : "off";
        System.out.println("Light " + light.getId() + " was turned " + stateWord);
    }
}
