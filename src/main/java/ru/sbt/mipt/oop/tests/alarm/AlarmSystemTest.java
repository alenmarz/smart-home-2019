package ru.sbt.mipt.oop.tests.alarm;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.configuration.Configuration;
import ru.sbt.mipt.oop.configuration.JsonConfigurationLoader;
import ru.sbt.mipt.oop.home.EventHandler;
import ru.sbt.mipt.oop.home.alarm.Alarm;
import ru.sbt.mipt.oop.home.event.sensor.SensorEvent;
import ru.sbt.mipt.oop.home.event.sensor.SensorEventType;
import ru.sbt.mipt.oop.home.loader.JsonSmartHomeLoader;
import ru.sbt.mipt.oop.home.objects.handlers.DoorEventHandler;
import ru.sbt.mipt.oop.home.objects.handlers.decorator.AlarmDecorator;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.objects.Door;
import ru.sbt.mipt.oop.home.objects.Room;
import ru.sbt.mipt.oop.home.alarm.AlarmSystem;
import ru.sbt.mipt.oop.home.alarm.State;

import java.io.IOException;
import java.util.Collection;

class AlarmSystemTest {
    @Test
    void handleEventWhenActivated() throws IOException {
        SmartHome smartHome = new JsonSmartHomeLoader("smart-home-1.js").load();
        Configuration configuration = new JsonConfigurationLoader("config.js").load();
        smartHome.setAlarm(new Alarm(configuration));
        SensorEvent event1 = new SensorEvent(SensorEventType.DOOR_OPEN, "1");
        EventHandler handler = new AlarmDecorator(new DoorEventHandler(smartHome), smartHome.getAlarm());
        smartHome.getAlarm().activate("SOME_SECRET_CODE");
        handler.handleEvent(event1);
        Assert.assertEquals(smartHome.getAlarm().getType(), State.StateType.ALARM);
        SensorEvent event2 = new SensorEvent(SensorEventType.DOOR_OPEN, "2");
        handler.handleEvent(event2);
        Collection<Room> rooms = smartHome.getRooms();
        for (Room room: rooms) {
            if (room.getName().equals("bathroom")) {
                Door door = room.getDoorById("2");
                Assert.assertFalse(door.isOpen());
                break;
            }
        }
    }

    @Test
    void handleEventWhenDeactivated() throws IOException {
        SmartHome smartHome = new JsonSmartHomeLoader("smart-home-1.js").load();
        Configuration configuration = new JsonConfigurationLoader("config.js").load();
        smartHome.setAlarm(new Alarm(configuration));
        SensorEvent event1 = new SensorEvent(SensorEventType.DOOR_OPEN, "1");
        EventHandler handler = new AlarmDecorator(new DoorEventHandler(smartHome), smartHome.getAlarm());
        handler.handleEvent(event1);
        Assert.assertEquals(smartHome.getAlarm().getType(), State.StateType.DEACTIVATED);
        Collection<Room> rooms = smartHome.getRooms();
        for (Room room: rooms) {
            if (room.getName().equals("kitchen")) {
                Door door = room.getDoorById("1");
                Assert.assertTrue(door.isOpen());
                break;
            }
        }
    }

    @Test
    void handleEventWhenAlarm() throws IOException {
        SmartHome smartHome = new JsonSmartHomeLoader("smart-home-1.js").load();
        Configuration configuration = new JsonConfigurationLoader("config.js").load();
        smartHome.setAlarm(new Alarm(configuration));
        SensorEvent event1 = new SensorEvent(SensorEventType.DOOR_OPEN, "1");
        EventHandler handler = new AlarmDecorator(new DoorEventHandler(smartHome), smartHome.getAlarm());
        smartHome.getAlarm().alarm();
        handler.handleEvent(event1);
        Assert.assertEquals(smartHome.getAlarm().getType(), State.StateType.ALARM);
        Collection<Room> rooms = smartHome.getRooms();
        for (Room room: rooms) {
            if (room.getName().equals("kitchen")) {
                Door door = room.getDoorById("1");
                Assert.assertFalse(door.isOpen());
                break;
            }
        }
    }
}