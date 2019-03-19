package ru.sbt.mipt.oop.home.objects.action;

import ru.sbt.mipt.oop.home.event.sensor.SensorEvent;
import ru.sbt.mipt.oop.home.objects.Door;

import static ru.sbt.mipt.oop.home.event.sensor.SensorEventType.DOOR_OPEN;
import static ru.sbt.mipt.oop.home.objects.action.ActionType.DOOR;

public class DoorAction extends Action<Door> {
    protected ActionType actionType = DOOR;

    public DoorAction(Object event) {
        super(event);
    }

    @Override
    public void execute(Door door, String roomName) {
        SensorEvent event = (SensorEvent) getEvent();

        if (door.getId().equals(event.getObjectId())) {
            changeDoorState(door, event.getType() == DOOR_OPEN);
        }
    }

    private void changeDoorState(Door door, boolean opened) {
        door.setOpen(opened);
        sayEventMessage(door, opened);
    }

    private void sayEventMessage(Door door,  boolean opened) {
        String stateWord = opened ? "opened" : "closed";
        System.out.println("Door " + door.getId() + " was " + stateWord);
    }

    @Override
    public ActionType getActionType() {
        return actionType;
    }
}
