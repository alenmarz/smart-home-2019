package ru.sbt.mipt.oop.home.alarm;

import ru.sbt.mipt.oop.configuration.Configuration;

public class AlarmSystem {
    private State state;
    private String securityCode;

    public AlarmSystem(Configuration configuration, Alarm alarm) {
        this.securityCode = configuration.getSecurityCode();
        this.state = new DeactivatedState(alarm);
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
