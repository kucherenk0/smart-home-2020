package ru.sbt.mipt.oop.adapter;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.model.SmartHome;
import ru.sbt.mipt.oop.model.event.SensorEvent;
import ru.sbt.mipt.oop.model.event.SensorEventType;
import ru.sbt.mipt.oop.utils.event.EventHandlerManager;

import java.util.Map;

public class HandlerAdapter implements EventHandler {

    private final EventHandler handler;
    private final Map<String, SensorEventType> eventTypeMap;
    private SmartHome smartHome;
    private EventHandlerManager eventHandlerManager;


    public HandlerAdapter(SmartHome smartHome,
                          EventHandlerManager eventHandlerManager,
                          EventHandler handler,
                          Map<String,
                                  SensorEventType> eventTypeMap) {
        this.eventHandlerManager = eventHandlerManager;
        this.handler = handler;
        this.eventTypeMap = eventTypeMap;
        this.smartHome = smartHome;
    }

    private SensorEvent transform(CCSensorEvent event) {
        if (eventTypeMap.containsKey(event.getEventType())) {
            return new SensorEvent(eventTypeMap.get(event.getEventType()), event.getObjectId());
        } else {
            throw new IllegalArgumentException("Unknown event type");
        }
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        SensorEvent sensorEvent = transform(event);
        eventHandlerManager.passToHandlers(smartHome, sensorEvent);
    }
}

