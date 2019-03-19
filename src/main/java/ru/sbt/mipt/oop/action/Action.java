package ru.sbt.mipt.oop.action;


abstract public class Action<T> {
    protected ActionType actionType;

    protected Object event;


    public Action(Object event) {
        this.event = event;
    }

    public Object getEvent() {
        return event;
    }

    public abstract void execute(T object, String roomName);

    public ActionType getActionType() {
        return actionType;
    }
}
