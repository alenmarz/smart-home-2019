package ru.sbt.mipt.oop.home_elements;

import javafx.util.Pair;
import ru.sbt.mipt.oop.action.Action;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.SensorEvent;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class Light {
    private boolean isOn;
    private final String id;

    public Light(String id, boolean isOn) {
        this.id = id;
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    public String getId() {
        return id;
    }

    public void setOn(boolean on) {
        isOn = on;
    }
}
