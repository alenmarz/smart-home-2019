package ru.sbt.mipt.oop.home.loader;

import ru.sbt.mipt.oop.home.SmartHome;

import java.io.IOException;

public interface SmartHomeLoader {
    SmartHome load() throws IOException;
}

