package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class HallDoorEventHandler implements SensorEventHandler {

    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent event) {
        if (!event.getType().equals(DOOR_CLOSED)) return;

        for (Room room: smartHome.getRooms()) {
            Door door = room.getDoorByEventId(event.getObjectId());
            if (room.getName().equals(Room.HALL) && door != null) {
                smartHome.turnOffAllLights();
            }
        }
    }
}
