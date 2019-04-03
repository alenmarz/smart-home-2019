package ru.sbt.mipt.oop.home.remote.command;

import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.remote.command.Command;

public class SetAlarmModeCommand implements Command {
    private SmartHome smartHome;

    public SetAlarmModeCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.getAlarm().alarm();
    }
}
