package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import ru.sbt.mipt.oop.home.EventHandler;
import ru.sbt.mipt.oop.home.command.CommandExecutor;
import ru.sbt.mipt.oop.home.command.sensor.SensorCommandExecutor;
import ru.sbt.mipt.oop.home.event.EventProvider;
import ru.sbt.mipt.oop.home.event.sensor.CCSensorEventsManagerAdapter;
import ru.sbt.mipt.oop.home.event.sensor.RandomSensorEventProvider;
import ru.sbt.mipt.oop.home.loader.JsonSmartHomeLoader;
import ru.sbt.mipt.oop.home.objects.handlers.DoorEventHandler;
import ru.sbt.mipt.oop.home.objects.handlers.LightEventHandler;
import ru.sbt.mipt.oop.home.objects.handlers.HallDoorEventHandler;
import ru.sbt.mipt.oop.home.SmartHome;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Application {

    public static void main(String... args) throws IOException {
        SmartHome smartHome = new JsonSmartHomeLoader("smart-home-1.js").load();
        CommandExecutor executor = new SensorCommandExecutor();
        EventProvider eventProvider = new RandomSensorEventProvider();
        startEventsCycle(smartHome, eventProvider, executor);
    }

    private static void startEventsCycle(SmartHome smartHome, EventProvider eventProvider, CommandExecutor executor) {
        Collection<Object> events = eventProvider.getEvents();
        Collection<EventHandler> eventHandlers = configureEventProcessors(smartHome, executor);

        for (Object event : events) {
            System.out.println("Got event: " + event);

            for (EventHandler eventHandler : eventHandlers) {
                eventHandler.handleEvent(event);
            }
        }
    }

    private static Collection<EventHandler> configureEventProcessors(SmartHome smartHome, CommandExecutor executor) {
        Collection<EventHandler> eventProcessors = new ArrayList<>();
        eventProcessors.add(new LightEventHandler(smartHome));
        eventProcessors.add(new DoorEventHandler(smartHome));
        eventProcessors.add(new HallDoorEventHandler(smartHome, executor));
        return eventProcessors;
    }
}
