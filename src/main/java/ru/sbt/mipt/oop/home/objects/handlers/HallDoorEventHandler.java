package ru.sbt.mipt.oop.home.objects.handlers;

import ru.sbt.mipt.oop.home.objects.action.LightAction;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.EventHandler;
import ru.sbt.mipt.oop.home.command.CommandExecutor;
import ru.sbt.mipt.oop.home.command.sensor.SensorCommandExecutor;
import ru.sbt.mipt.oop.home.event.sensor.SensorEvent;
import ru.sbt.mipt.oop.home.objects.Door;
import ru.sbt.mipt.oop.home.objects.Room;

import static ru.sbt.mipt.oop.home.event.sensor.SensorEventType.DOOR_CLOSED;

public class HallDoorEventHandler extends EventHandler {
    private SensorCommandExecutor executor;


    public HallDoorEventHandler(SmartHome smartHome, CommandExecutor executor) {
        super(smartHome);
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
