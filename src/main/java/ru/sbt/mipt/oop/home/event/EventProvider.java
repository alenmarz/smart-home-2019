package ru.sbt.mipt.oop.home.event;

import java.util.Collection;

public interface EventProvider {
    Collection<Object> getEvents();
}
