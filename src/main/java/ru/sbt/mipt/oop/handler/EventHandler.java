package ru.sbt.mipt.oop.handler;

import ru.sbt.mipt.oop.model.SmartHome;
import ru.sbt.mipt.oop.model.event.SensorEvent;

public interface EventHandler {
    void handle(SmartHome smartHome, SensorEvent event);
}
