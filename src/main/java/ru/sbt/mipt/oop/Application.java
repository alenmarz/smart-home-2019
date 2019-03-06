package ru.sbt.mipt.oop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Application {

    public static void main(String... args) throws IOException {
        SmartHomeLoader smartHomeLoader = new JsonSmartHomeLoader();
        SmartHome smartHome = smartHomeLoader.load("smart-home-1.js");
        startEventsCycle(smartHome);
    }

    private static void startEventsCycle(SmartHome smartHome) {
        SensorEvent event = RandomSensorEventProvider.getNextSensorEvent();
        Collection<SensorEventHandler> eventHandlers = configureEventProcessors();

        while (event != null) {
            System.out.println("Got event: " + event);

            for (SensorEventHandler eventHandler : eventHandlers) {
                eventHandler.handleEvent(smartHome, event);
            }

            event = RandomSensorEventProvider.getNextSensorEvent();
        }
    }

    private static Collection<SensorEventHandler> configureEventProcessors() {
        Collection<SensorEventHandler> eventProcessors = new ArrayList<>();
        eventProcessors.add(new LightEventHandler());
        eventProcessors.add(new DoorEventHandler());
        eventProcessors.add(new HallDoorEventHandler());
        return eventProcessors;
    }
}
