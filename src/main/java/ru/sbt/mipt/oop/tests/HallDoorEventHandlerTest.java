package ru.sbt.mipt.oop.tests;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.event_handlers.HallDoorEventHandler;
import ru.sbt.mipt.oop.home_elements.Door;
import ru.sbt.mipt.oop.home_elements.Light;
import ru.sbt.mipt.oop.home_elements.Room;
import ru.sbt.mipt.oop.iterator.Iterator;
import ru.sbt.mipt.oop.iterator.LightIterator;

import java.io.IOException;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

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
        Iterator iterator = new LightIterator(smartHome);
        while (iterator.hasNext()) {
            Assert.assertFalse(((LightIterator) iterator).getNext().isOn());
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