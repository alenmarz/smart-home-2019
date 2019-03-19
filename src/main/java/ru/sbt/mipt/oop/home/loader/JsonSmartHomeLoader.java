package ru.sbt.mipt.oop.home.loader;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.home.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonSmartHomeLoader implements SmartHomeLoader {
    private Gson gson;
    private String json;

    public JsonSmartHomeLoader(String fileName) throws IOException {
        gson = new Gson();
        json = new String(Files.readAllBytes(Paths.get(fileName)));
    }

    @Override
    public SmartHome load() {
        return gson.fromJson(json, SmartHome.class);
    }
}
