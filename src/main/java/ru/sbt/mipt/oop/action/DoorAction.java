package ru.sbt.mipt.oop.action;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.home_elements.Door;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;
import static ru.sbt.mipt.oop.action.ActionType.DOOR;

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

    public ActionType getActionType() {
        return actionType;
    }
}
