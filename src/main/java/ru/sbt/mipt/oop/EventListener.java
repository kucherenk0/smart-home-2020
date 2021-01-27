package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.model.SmartHome;
import ru.sbt.mipt.oop.model.event.SensorEvent;
import ru.sbt.mipt.oop.utils.SmartHomeLoader;
import ru.sbt.mipt.oop.utils.event.EventGenerator;
import ru.sbt.mipt.oop.utils.event.EventHandlerManager;

public class EventListener {

    private final EventGenerator eventGenerator;
    private final SmartHomeLoader smartHomeLoader;
    private final EventHandlerManager eventHandlerManager;

    public EventListener(EventGenerator eventGenerator,
                         SmartHomeLoader smartHomeLoader,
                         EventHandlerManager eventHandlerManager) {
        this.eventGenerator = eventGenerator;
        this.smartHomeLoader = smartHomeLoader;
        this.eventHandlerManager = eventHandlerManager;
    }

    public void run() {
        SensorEvent event = eventGenerator.getNextEvent();
        SmartHome smartHome = smartHomeLoader.load();

        while (event != null) {
            System.out.println("Got event: " + event);
            eventHandlerManager.passToHandlers(smartHome, event);
            event = eventGenerator.getNextEvent();
        }
    }
}
