package ru.sbt.mipt.oop.utils.event;

import ru.sbt.mipt.oop.model.event.SensorEvent;

public interface EventGenerator {

    SensorEvent getNextEvent();
}
