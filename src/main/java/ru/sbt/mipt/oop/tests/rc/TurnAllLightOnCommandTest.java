package ru.sbt.mipt.oop.tests.rc;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import rc.RemoteControl;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.loader.JsonSmartHomeLoader;
import ru.sbt.mipt.oop.home.objects.Light;
import ru.sbt.mipt.oop.home.objects.Room;
import ru.sbt.mipt.oop.home.remote.RemoteController;
import ru.sbt.mipt.oop.home.remote.command.TurnAllLightOffCommand;
import ru.sbt.mipt.oop.home.remote.command.TurnAllLightOnCommand;

import java.io.IOException;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class TurnAllLightOnCommandTest {
    @Test
    void Test() throws IOException {
        SmartHome smartHome = new JsonSmartHomeLoader("smart-home-1.js").load();
        RemoteControl rc = new RemoteController();
        ((RemoteController) rc).setCommand("A", new TurnAllLightOnCommand(smartHome));
        rc.onButtonPressed("A");
        Collection<Room> rooms = smartHome.getRooms();
        for (Room room: rooms) {
            for (Light light: room.getLights()) {
                Assert.assertTrue(light.isOn());
            }
        }
    }
}