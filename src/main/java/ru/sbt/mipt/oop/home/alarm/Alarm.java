package ru.sbt.mipt.oop.home.alarm;

import ru.sbt.mipt.oop.configuration.Configuration;

public class Alarm {
    private State state;
    private String securityCode;

    public Alarm(Configuration configuration) {
        this.securityCode = configuration.getSecurityCode();
        this.state = new DeactivatedState(this);
    }

    public State getState() {
        return state;
    }

    public void changeState(State state) {
        this.state = state;
    }

    public boolean checkCode(String code) {
        return code.equals(securityCode);
    }
}
