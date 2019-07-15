package ru.sbt.mipt.oop.home.remote.command;

import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.command.CommandType;
import ru.sbt.mipt.oop.home.command.sensor.SensorCommand;
import ru.sbt.mipt.oop.home.event.sensor.SensorEvent;
import ru.sbt.mipt.oop.home.event.sensor.SensorEventType;
import ru.sbt.mipt.oop.home.objects.Light;
import ru.sbt.mipt.oop.home.objects.Room;
import ru.sbt.mipt.oop.home.objects.action.LightAction;
import ru.sbt.mipt.oop.home.remote.command.Command;

import java.util.Collection;

public class TurnAllLightOnCommand implements Command {
    private SmartHome smartHome;

    public TurnAllLightOnCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        for (Room room: smartHome.getRooms()) {
            Collection<Light> lights = room.getLights();
            for (Light light: lights) {
                room.execute(new LightAction(
                        new SensorEvent(
                                SensorEventType.LIGHT_ON,
                                light.getId()
                        )
                ));
            }
        }
    }
}
