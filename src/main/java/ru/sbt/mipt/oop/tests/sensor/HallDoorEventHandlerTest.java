package ru.sbt.mipt.oop.tests.sensor;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.home.EventHandler;
import ru.sbt.mipt.oop.home.command.CommandExecutor;
import ru.sbt.mipt.oop.home.command.sensor.SensorCommandExecutor;
import ru.sbt.mipt.oop.home.event.sensor.SensorEvent;
import ru.sbt.mipt.oop.home.event.sensor.SensorEventType;
import ru.sbt.mipt.oop.home.loader.JsonSmartHomeLoader;
import ru.sbt.mipt.oop.home.objects.handlers.HallDoorEventHandler;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.objects.Door;
import ru.sbt.mipt.oop.home.objects.Light;
import ru.sbt.mipt.oop.home.objects.Room;

import java.io.IOException;
import java.util.Collection;

class HallDoorEventHandlerTest {

    @Test
    void handleEventHallDoorClosed() throws IOException {
        String DOOR_ID = "4";
        SmartHome smartHome = new JsonSmartHomeLoader("smart-home-1.js").load();
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, DOOR_ID);
        CommandExecutor executor = new SensorCommandExecutor();
        EventHandler handler = new HallDoorEventHandler(smartHome, executor);
        handler.handleEvent(event);
        Collection<Room> rooms = smartHome.getRooms();
        for (Room room: rooms) {
            if (room.getName().equals("hall")) {
                Door door = room.getDoorById(DOOR_ID);
                Assert.assertFalse(door.isOpen());
                break;
            }
        }
        for (Room room: rooms) {
            Collection<Light> lights = room.getLights();
            for (Light light: lights) {
                Assert.assertFalse(light.isOn());
            }
        }
    }

    @Test
    void handleEventHallDoorOpen() throws IOException {
        String DOOR_ID = "4";
        SmartHome smartHome = new JsonSmartHomeLoader("smart-home-1.js").load();
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, DOOR_ID);
        CommandExecutor executor = new SensorCommandExecutor();
        EventHandler handler = new HallDoorEventHandler(smartHome, executor);
        handler.handleEvent(event);
        Collection<Room> rooms = smartHome.getRooms();
        for (Room room: rooms) {
            if (room.getName().equals("hall")) {
                Door door = room.getDoorById(DOOR_ID);
                Assert.assertFalse(door.isOpen());
            }
            if (room.getName().equals("kitchen")) {
                Light light = room.getLightById("2");
                Assert.assertTrue(light.isOn());
            }
        }
    }
}