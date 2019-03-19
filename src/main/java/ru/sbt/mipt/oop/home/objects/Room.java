package ru.sbt.mipt.oop.home.objects;

import ru.sbt.mipt.oop.home.objects.action.Action;
import ru.sbt.mipt.oop.home.objects.action.Actionable;
import ru.sbt.mipt.oop.home.objects.action.ActionType;

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
        for (Light light: getLights()) {
            if (light.getId().equals(id)) {
                return light;
            }
        }
        return null;
    }

    public Door getDoorById(String id) {
        for (Door door: getDoors()) {
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
        if (action.getActionType() == ActionType.DOOR) {
            for (Door door : doors) {
                action.execute(door, name);
            }
        } else if (action.getActionType() == ActionType.LIGHT) {
            for (Light light : lights) {
                action.execute(light, name);
                if (name.equals("hall")) {
                    break;
                }
            }
        }
    }
}
