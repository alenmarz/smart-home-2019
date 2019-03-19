package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventHandler implements EventHandler {
    private SmartHome smartHome;

    public DoorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(Object event) {
        SensorEvent sensorEvent = (SensorEvent) event;

        if (!isDoorEvent(sensorEvent)) return;

        for (Room room : smartHome.getRooms()) {
            Door door = room.getDoorById(sensorEvent.getObjectId());
            if (door != null) {
                changeDoorState(room, door, sensorEvent.getType() == DOOR_OPEN);
            }
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
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was " + stateWord);
    }
}