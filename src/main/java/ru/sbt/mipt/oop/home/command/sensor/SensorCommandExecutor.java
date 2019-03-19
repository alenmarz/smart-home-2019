package ru.sbt.mipt.oop.home.command.sensor;

import ru.sbt.mipt.oop.home.command.CommandExecutor;

public class SensorCommandExecutor implements CommandExecutor {

    @Override
    public void executeCommand(Object command) {
        System.out.println("Pretend we're sending command " + command);
    }
}
