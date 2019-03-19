package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.event_handlers.DoorEventHandler;
import ru.sbt.mipt.oop.event_handlers.LightEventHandler;
import ru.sbt.mipt.oop.event_handlers.HallDoorEventHandler;

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
        SensorEvent event = (SensorEvent) eventProvider.getNextEvent();
        Collection<EventHandler> eventHandlers = configureEventProcessors(smartHome, executor);

        while (event != null) {
            System.out.println("Got event: " + event);

            for (EventHandler eventHandler : eventHandlers) {
                eventHandler.handleEvent(event);
            }

            event = (SensorEvent) eventProvider.getNextEvent();
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
