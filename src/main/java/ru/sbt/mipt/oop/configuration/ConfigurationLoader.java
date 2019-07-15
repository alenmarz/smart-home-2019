package ru.sbt.mipt.oop.configuration;

import java.io.IOException;

public interface ConfigurationLoader {
    Configuration load() throws IOException;
}
