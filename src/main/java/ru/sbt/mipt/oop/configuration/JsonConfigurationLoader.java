package ru.sbt.mipt.oop.configuration;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonConfigurationLoader implements ConfigurationLoader {
    private Gson gson;
    private String json;

    public JsonConfigurationLoader(String fileName) throws IOException {
        gson = new Gson();
        json = new String(Files.readAllBytes(Paths.get(fileName)));
    }

    @Override
    public Configuration load() {
        return gson.fromJson(json, Configuration.class);
    }
}
