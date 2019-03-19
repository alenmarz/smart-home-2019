package ru.sbt.mipt.oop.action;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.home_elements.Light;
import ru.sbt.mipt.oop.iterator.Iterator;
import ru.sbt.mipt.oop.iterator.LightIterator;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;
import static ru.sbt.mipt.oop.action.ActionType.LIGHT;

public class LightAction extends Action<Light> {
    private enum RoomType {
        HALL, ORDINARY
    }

    protected ActionType actionType = LIGHT;
    private RoomType roomType;

    private CommandExecutor executor;
    private SmartHome smartHome;

    public LightAction(Object event) {
        super(event);
        this.roomType = RoomType.ORDINARY;
    }

    public LightAction(Object event, CommandExecutor executor, SmartHome smartHome) {
        super(event);
        this.executor = executor;
        this.smartHome = smartHome;
        this.roomType = RoomType.HALL;
    }

    @Override
    public void execute(Light light, String roomName) {
        if (roomType == RoomType.ORDINARY) {
            executeOrdinary(light);
        } else {
            executeHall(roomName);
        }
    }

    private void executeOrdinary(Light light) {
        SensorEvent event = (SensorEvent) getEvent();

        if (light.getId().equals(event.getObjectId())) {
            changeLightState(light, event.getType() == LIGHT_ON);
        }
    }

    private void executeHall(String roomName) {
        if (!roomName.equals("hall")) {
            return;
        }

        Iterator iterator = new LightIterator(smartHome);
        while (iterator.hasNext()) {
            Light light = (Light) iterator.getNext();
            light.setOn(false);
            SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
            executor.executeCommand(command);
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

    public ActionType getActionType() {
        return actionType;
    }
}
