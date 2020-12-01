package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.handlers.EventHandler;

import java.util.List;

public class EventHandlerManager {
    private List<EventHandler> handlers;

    public EventHandlerManager(List<EventHandler> handlers) {
        this.handlers = handlers;
    }

    public void passToHandlers(SmartHome smartHome, SensorEvent event) {
        for (EventHandler handler : handlers) {
            handler.handle(smartHome, event);
        }
    }
}


