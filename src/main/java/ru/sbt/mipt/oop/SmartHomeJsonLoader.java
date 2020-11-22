package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.nio.file.Files;
import java.nio.file.Paths;

public class SmartHomeJsonLoader implements SmartHomeLoader {
    private Gson gson = new Gson();
    private String jsonFileName;

    public SmartHomeJsonLoader(String jsonFileName) {
        this.jsonFileName = jsonFileName;
    }

    @Override
    public SmartHome load() {
        try {
            String json = new String(Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource(jsonFileName).getFile())));
            return gson.fromJson(json, SmartHome.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
