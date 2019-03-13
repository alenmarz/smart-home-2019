package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class HallDoorEventHandler implements EventHandler {
    private SensorCommandExecutor executor;
    private SmartHome smartHome;


    public HallDoorEventHandler(SmartHome smartHome, CommandExecutor executor) {
        this.smartHome = smartHome;
        this.executor = (SensorCommandExecutor) executor;
    }

    @Override
    public void handleEvent(Object event) {
        SensorEvent sensorEvent = (SensorEvent) event;
        if (!sensorEvent.getType().equals(DOOR_CLOSED)) return;

        for (Room room: smartHome.getRooms()) {
            Door door = room.getDoorById(sensorEvent.getObjectId());
            if (room.getName().equals(Room.HALL) && door != null) {
                for (Room homeRoom : smartHome.getRooms()) {
                    for (Light light : homeRoom.getLights()) {
                        light.setOn(false);
                        SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                        executor.executeCommand(command);
                    }
                }
            }
        }
    }
}
