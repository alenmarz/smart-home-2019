package ru.sbt.mipt.oop.home.objects.action;

import ru.sbt.mipt.oop.home.event.sensor.SensorEvent;
import ru.sbt.mipt.oop.home.objects.Door;
import ru.sbt.mipt.oop.home.objects.Light;

import static ru.sbt.mipt.oop.home.event.sensor.SensorEventType.DOOR_OPEN;

public class DoorAction implements Action {
    private SensorEvent event;

    public DoorAction(Object event) {
        this.event = (SensorEvent) event;
    }

    @Override
    public void execute(Object object) {
        if (!(object instanceof Door)) return;

        Door door = (Door) object;
        if (door.getId().equals(event.getObjectId())) {
            changeDoorState(door, event.getType() == DOOR_OPEN);
        }
    }

    private void changeDoorState(Door door, boolean opened) {
        door.setOpen(opened);
        sayEventMessage(door, opened);
    }

    private void sayEventMessage(Door door,  boolean opened) {
        String stateWord = opened ? "opened" : "closed";
        System.out.println("Door " + door.getId() + " was " + stateWord);
    }
}
