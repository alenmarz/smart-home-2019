package ru.sbt.mipt.oop.tests;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.event_handlers.LightEventHandler;
import ru.sbt.mipt.oop.home_elements.Light;
import ru.sbt.mipt.oop.home_elements.Room;

import java.io.IOException;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class LightEventHandlerTest {

    @Test
    void handleEventKitchenLightOn() throws IOException {
        String LIGHT_ID = "1";
        SmartHome smartHome = new JsonSmartHomeLoader("smart-home-1.js").load();
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ON, LIGHT_ID);
        EventHandler handler = new LightEventHandler(smartHome);
        handler.handleEvent(event);
        Collection<Room> rooms = smartHome.getRooms();
        for (Room room: rooms) {
            if (room.getName().equals("kitchen")) {
                Light light = room.getLightById(LIGHT_ID);
                Assert.assertTrue(light.isOn());
                break;
            }
        }
    }

    @Test
    void handleEventBathroomLightOff() throws IOException {
        String LIGHT_ID = "3";
        SmartHome smartHome = new JsonSmartHomeLoader("smart-home-1.js").load();
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_OFF, LIGHT_ID);
        EventHandler handler = new LightEventHandler(smartHome);
        handler.handleEvent(event);
        Collection<Room> rooms = smartHome.getRooms();
        for (Room room: rooms) {
            if (room.getName().equals("bathroom")) {
                Light light = room.getLightById(LIGHT_ID);
                Assert.assertFalse(light.isOn());
                break;
            }
        }
    }
}