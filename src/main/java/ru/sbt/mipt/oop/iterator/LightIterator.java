package ru.sbt.mipt.oop.iterator;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.home_elements.Light;
import ru.sbt.mipt.oop.home_elements.Room;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LightIterator implements Iterator {
    private List<Light> lights;
    private int currentPosition = 0;

    public LightIterator(SmartHome smartHome) {
        Collection<Room> rooms = smartHome.getRooms();
        lights = new ArrayList<>();
        rooms.forEach(room -> lights.addAll(room.getLights()));
    }

    @Override
    public boolean hasNext() {
        return currentPosition < lights.size();
    }

    @Override
    public Light getNext() {
        if (!hasNext()) {
            return null;
        }

        Light next = lights.get(currentPosition);
        currentPosition++;

        return next;
    }
}
