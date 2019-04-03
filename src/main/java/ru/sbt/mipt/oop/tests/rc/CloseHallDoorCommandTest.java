package ru.sbt.mipt.oop.tests.rc;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import rc.RemoteControl;
import ru.sbt.mipt.oop.configuration.Configuration;
import ru.sbt.mipt.oop.configuration.JsonConfigurationLoader;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.alarm.Alarm;
import ru.sbt.mipt.oop.home.alarm.AlarmState;
import ru.sbt.mipt.oop.home.loader.JsonSmartHomeLoader;
import ru.sbt.mipt.oop.home.objects.Door;
import ru.sbt.mipt.oop.home.objects.Light;
import ru.sbt.mipt.oop.home.objects.Room;
import ru.sbt.mipt.oop.home.remote.RemoteController;
import ru.sbt.mipt.oop.home.remote.command.ActivateAlarmCommand;
import ru.sbt.mipt.oop.home.remote.command.CloseHallDoorCommand;

import java.io.IOException;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class CloseHallDoorCommandTest {
    @Test
    void Test() throws IOException {
        String DOOR_ID = "4";
        SmartHome smartHome = new JsonSmartHomeLoader("smart-home-1.js").load();
        RemoteControl rc = new RemoteController();
        ((RemoteController) rc).setCommand("A", new CloseHallDoorCommand(smartHome));
        rc.onButtonPressed("A");
        Collection<Room> rooms = smartHome.getRooms();
        for (Room room: rooms) {
            if (room.getName().equals("hall")) {
                Door door = room.getDoorById(DOOR_ID);
                Assert.assertFalse(door.isOpen());
                break;
            }
        }
    }
}