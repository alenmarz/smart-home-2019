package ru.sbt.mipt.oop.home;

import ru.sbt.mipt.oop.home.objects.action.Actionable;
import ru.sbt.mipt.oop.home.objects.action.Action;
import ru.sbt.mipt.oop.home.objects.Room;
import ru.sbt.mipt.oop.home.alarm.Alarm;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {
    private Collection<Room> rooms;
    private Alarm alarm;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    public Alarm getAlarm() {
        return alarm;
    }

    public void setAlarm(Alarm alarm) {
        this.alarm = alarm;
    }

    public void execute(Action action) {
        for (Room room: rooms) {
            room.execute(action);
        }
    }
}
