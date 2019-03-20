package ru.sbt.mipt.oop.home.objects.action;

import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.command.CommandExecutor;
import ru.sbt.mipt.oop.home.command.CommandType;
import ru.sbt.mipt.oop.home.command.sensor.SensorCommand;
import ru.sbt.mipt.oop.home.event.sensor.SensorEvent;
import ru.sbt.mipt.oop.home.event.sensor.SensorEventType;
import ru.sbt.mipt.oop.home.objects.Light;
import ru.sbt.mipt.oop.home.objects.Room;

import java.util.Collection;

public class AllLightsAction implements Action {
    private CommandExecutor executor;

    public AllLightsAction(CommandExecutor executor) {
        this.executor = executor;
    }

    @Override
    public void execute(Object object) {
        if (!(object instanceof SmartHome)) return;

        SmartHome smartHome = (SmartHome) object;
        for (Room room: smartHome.getRooms()) {
            Collection<Light> lights = room.getLights();
            for (Light light: lights) {
                room.execute(new LightAction(
                        new SensorEvent(
                                SensorEventType.LIGHT_OFF,
                                light.getId()
                        )
                ));
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                executor.executeCommand(command);
            }
        }
    }
}
