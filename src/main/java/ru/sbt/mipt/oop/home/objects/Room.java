package ru.sbt.mipt.oop.home.objects;

import ru.sbt.mipt.oop.home.objects.action.Action;
import ru.sbt.mipt.oop.home.objects.action.Actionable;

import java.util.Collection;

public class Room implements Actionable {

    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public Collection<Light> getLights() {
        return lights;
    }

    public Light getLightById(String id) {
        for (Light light : getLights()) {
            if (light.getId().equals(id)) {
                return light;
            }
        }
        return null;
    }

    public Door getDoorById(String id) {
        for (Door door : getDoors()) {
            if (door.getId().equals(id)) {
                return door;
            }
        }
        return null;
    }

    public Collection<Door> getDoors() {
        return doors;
    }

    public String getName() {
        return name;
    }

    @Override
    public void execute(Action action) {
        doors.forEach(door -> action.execute(door));
        lights.forEach(light -> action.execute(light));
    }
}
