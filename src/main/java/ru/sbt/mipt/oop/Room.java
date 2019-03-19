package ru.sbt.mipt.oop;

import java.util.Collection;

public class Room {
    public static final String HALL = "hall";

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
}
