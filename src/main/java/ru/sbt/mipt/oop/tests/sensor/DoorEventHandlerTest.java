package ru.sbt.mipt.oop.tests.sensor;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.home.EventHandler;
import ru.sbt.mipt.oop.home.event.sensor.SensorEvent;
import ru.sbt.mipt.oop.home.event.sensor.SensorEventType;
import ru.sbt.mipt.oop.home.loader.JsonSmartHomeLoader;
import ru.sbt.mipt.oop.home.objects.handlers.DoorEventHandler;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.objects.Door;
import ru.sbt.mipt.oop.home.objects.Light;
import ru.sbt.mipt.oop.home.objects.Room;

import java.io.IOException;
import java.util.Collection;

class DoorEventHandlerTest {

    @Test
    void handleEventKitchenOpenDoor() throws IOException {
        String DOOR_ID = "1";
        SmartHome smartHome = new JsonSmartHomeLoader("smart-home-1.js").load();
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, DOOR_ID);
        EventHandler handler = new DoorEventHandler(smartHome);
        handler.handleEvent(event);
        Collection<Room> rooms = smartHome.getRooms();
        for (Room room: rooms) {
            if (room.getName().equals("kitchen")) {
                Door door = room.getDoorById(DOOR_ID);
                Assert.assertTrue(door.isOpen());
                break;
            }
        }
    }

    @Test
    void handleEventKitchenLightOn() throws IOException {
        String LIGHT_ID = "1";
        SmartHome smartHome = new JsonSmartHomeLoader("smart-home-1.js").load();
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ON, LIGHT_ID);
        EventHandler handler = new DoorEventHandler(smartHome);
        handler.handleEvent(event);
        Collection<Room> rooms = smartHome.getRooms();
        for (Room room: rooms) {
            if (room.getName().equals("kitchen")) {
                Light light = room.getLightById(LIGHT_ID);
                Assert.assertFalse(light.isOn());
                break;
            }
        }
    }

}