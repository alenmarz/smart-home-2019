package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventHandler implements SensorEventHandler {
    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent event) {
        if (!isDoorEvent(event)) return;

        for (Room room : smartHome.getRooms()) {
            Door door = room.getDoorByEventId(event.getObjectId());
            changeDoorState(room, door, event.getType() == DOOR_OPEN);
        }
    }


    public boolean isDoorEvent(SensorEvent event) {
        return event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED;
    }

    private void changeDoorState(Room room, Door door, boolean opened) {
        door.setOpen(opened);
        sayEventMessage(door, room, opened);
    }

    private void sayEventMessage(Door door, Room room, boolean opened) {
        String stateWord = opened ? "opened" : "closed";
        System.out.println("Light " + door.getId() + " in room " + room.getName() + " was " + stateWord);
    }
}