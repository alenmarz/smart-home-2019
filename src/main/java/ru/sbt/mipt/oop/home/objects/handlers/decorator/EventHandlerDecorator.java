package ru.sbt.mipt.oop.home.objects.handlers.decorator;

import ru.sbt.mipt.oop.home.EventHandler;

public class EventHandlerDecorator implements EventHandler {
    protected EventHandler wrappee;

    public EventHandlerDecorator(EventHandler handler) {
        this.wrappee = handler;
    }

    @Override
    public void handleEvent(Object object) { wrappee.handleEvent(object); }
}
