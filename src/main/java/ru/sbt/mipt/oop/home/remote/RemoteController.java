package ru.sbt.mipt.oop.home.remote;

import rc.RemoteControl;
import ru.sbt.mipt.oop.home.remote.command.Command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class RemoteController implements RemoteControl {
    private String id;
    private Collection<Button> buttons;

    public RemoteController() {
        id = UUID.randomUUID().toString();
        buttons = new ArrayList<>();
    }

    public void setCommand(String buttonCode, Command command) {
        Button button = getButton(buttonCode);
        if (button != null) {
            buttons.remove(button);
        }
        buttons.add(new Button(buttonCode, command));
    }

    @Override
    public void onButtonPressed(String buttonCode) {
        Button button = getButton(buttonCode);
        if (button != null) {
            button.getCommand().execute();
        }
    }

    private Button getButton(String buttonCode) {
        for (Button button: buttons) {
            if (button.getCode().equals(buttonCode)) {
                return button;
            }
        }
        return null;
    }

    public String getId() {
        return id;
    }
}
