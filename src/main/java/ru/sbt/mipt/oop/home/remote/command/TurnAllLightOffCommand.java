package ru.sbt.mipt.oop.home.remote.command;

import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.command.CommandExecutor;
import ru.sbt.mipt.oop.home.event.sensor.SensorEvent;
import ru.sbt.mipt.oop.home.event.sensor.SensorEventType;
import ru.sbt.mipt.oop.home.objects.Light;
import ru.sbt.mipt.oop.home.objects.Room;
import ru.sbt.mipt.oop.home.objects.action.AllLightsOffAction;
import ru.sbt.mipt.oop.home.objects.action.LightAction;

import java.util.Collection;

public class TurnAllLightOffCommand implements Command {
    private SmartHome smartHome;

    public TurnAllLightOffCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        for (Room room: smartHome.getRooms()) {
            Collection<Light> lights = room.getLights();
            for (Light light: lights) {
                room.execute(new LightAction(
                        new SensorEvent(
                                SensorEventType.LIGHT_OFF,
                                light.getId()
                        )
                ));
            }
        }
    }
}
