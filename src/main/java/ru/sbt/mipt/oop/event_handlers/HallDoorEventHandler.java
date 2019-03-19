package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.action.LightAction;
import ru.sbt.mipt.oop.home_elements.Door;
import ru.sbt.mipt.oop.home_elements.Room;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class HallDoorEventHandler implements EventHandler {
    private SensorCommandExecutor executor;
    private SmartHome smartHome;


    public HallDoorEventHandler(SmartHome smartHome, CommandExecutor executor) {
        this.smartHome = smartHome;
        this.executor = (SensorCommandExecutor) executor;
    }

    @Override
    public void handleEvent(Object event) {
        SensorEvent sensorEvent = (SensorEvent) event;
        if (!sensorEvent.getType().equals(DOOR_CLOSED)) return;

        for (Room room: smartHome.getRooms()) {
            Door door = room.getDoorById(sensorEvent.getObjectId());
            if (room.getName().equals("hall") && door != null) {
                smartHome.execute(new LightAction(sensorEvent, executor, smartHome));
            }
        }
    }
}
