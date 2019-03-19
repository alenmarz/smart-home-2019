package ru.sbt.mipt.oop.home;

import ru.sbt.mipt.oop.home.SmartHome;

public abstract class EventHandler {
    protected SmartHome smartHome;

    public EventHandler() {}

    public EventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }
    public abstract void handleEvent(Object object);

    public SmartHome getSmartHome() {
        return smartHome;
    }
}
