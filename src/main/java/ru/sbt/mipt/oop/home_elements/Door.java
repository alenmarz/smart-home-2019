package ru.sbt.mipt.oop.home_elements;

import ru.sbt.mipt.oop.action.Action;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.SensorEvent;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class Door {
    private final String id;
    private boolean isOpen;

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

}
