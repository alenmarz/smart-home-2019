package ru.sbt.mipt.oop.home.remote.command;

import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.event.sensor.SensorEvent;
import ru.sbt.mipt.oop.home.event.sensor.SensorEventType;
import ru.sbt.mipt.oop.home.objects.Light;
import ru.sbt.mipt.oop.home.objects.Room;
import ru.sbt.mipt.oop.home.objects.action.LightAction;
import ru.sbt.mipt.oop.home.remote.command.Command;

import java.util.ArrayList;
import java.util.Collection;

public class TurnHallLightsOnCommand implements Command {
    private SmartHome smartHome;

    public TurnHallLightsOnCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        for (Room room : smartHome.getRooms()) {
            if (room.getName().equals("hall")) {
                Collection<Light> lights = room.getLights();
                for (Light light : lights) {
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
}
