package ru.sbt.mipt.oop.home.remote;

import ru.sbt.mipt.oop.home.remote.command.Command;

public class Button {
    private String code;
    private Command command;

    public Button(String code, Command command) {
        this.code = code;
        this.command = command;
    }

    public String getCode() {
        return code;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}
