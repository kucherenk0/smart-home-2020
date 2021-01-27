package ru.sbt.mipt.oop.model.event;

public interface SmartHomeEvent {

     SensorEventType getType();

     String getObjectId();
}
