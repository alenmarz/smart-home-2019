package ru.sbt.mipt.oop;

public class SensorCommandExecutor implements CommandExecutor {

    @Override
    public void executeCommand(Object command) {
        System.out.println("Pretend we're sending command " + command);
    }
}
