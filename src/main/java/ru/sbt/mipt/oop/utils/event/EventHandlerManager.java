package ru.sbt.mipt.oop.utils.event;

import ru.sbt.mipt.oop.handler.EventHandler;
import ru.sbt.mipt.oop.model.SmartHome;
import ru.sbt.mipt.oop.model.event.SensorEvent;

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

    public void addHandler(EventHandler handler) {
        this.handlers.add(handler);
    }

}


