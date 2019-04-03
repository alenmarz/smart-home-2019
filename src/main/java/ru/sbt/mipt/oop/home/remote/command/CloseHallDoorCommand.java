package ru.sbt.mipt.oop.home.remote.command;

import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.event.sensor.SensorEvent;
import ru.sbt.mipt.oop.home.event.sensor.SensorEventType;
import ru.sbt.mipt.oop.home.objects.Door;
import ru.sbt.mipt.oop.home.objects.Light;
import ru.sbt.mipt.oop.home.objects.Room;
import ru.sbt.mipt.oop.home.objects.action.DoorAction;
import ru.sbt.mipt.oop.home.objects.action.LightAction;

import java.util.Collection;

public class CloseHallDoorCommand implements Command {
    private SmartHome smartHome;

    public CloseHallDoorCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        for (Room room : smartHome.getRooms()) {
            if (room.getName().equals("hall")) {
                Collection<Door> doors = room.getDoors();
                for (Door door: doors) {
                    room.execute(new DoorAction(
                            new SensorEvent(
                                    SensorEventType.DOOR_CLOSED,
                                    door.getId()
                            )
                    ));
                }
            }
        }
    }
}
