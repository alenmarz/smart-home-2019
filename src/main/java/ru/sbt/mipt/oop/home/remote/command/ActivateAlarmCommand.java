package ru.sbt.mipt.oop.home.remote.command;

import ru.sbt.mipt.oop.home.SmartHome;

public class ActivateAlarmCommand implements Command {
    private SmartHome smartHome;
    private String securityCode;

    public ActivateAlarmCommand(SmartHome smartHome, String securityCode) {
        this.smartHome = smartHome;
        this.securityCode = securityCode;
    }

    @Override
    public void execute() {
        smartHome.getAlarm().activate(securityCode);
    }
}
