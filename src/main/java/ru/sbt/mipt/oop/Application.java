package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.handlers.*;

import java.util.List;

public class Application {
    private EventGenerator eventGenerator;
    private SmartHomeLoader smartHomeLoader;
    private EventHandlerManager eventHandlerManager;

    public Application(EventGenerator eventGenerator,
                       SmartHomeLoader smartHomeLoader,
                       EventHandlerManager eventHandlerManager) {
        this.eventGenerator = eventGenerator;
        this.smartHomeLoader = smartHomeLoader;
        this.eventHandlerManager = eventHandlerManager;
    }

    public static void main(String... args) {
        new Application(
                new RandomEventGenerator(),
                new SmartHomeJsonLoader("smart-home-1.json"),
                new EventHandlerManager(List.of(
                        new LightOffHandler(),
                        new LightOnHandler(),
                        new DoorCloseHandler(),
                        new DoorOpenHandler(),
                        new HallDoorCloseHandler()
                ))).run();
    }

    private void run() {
        SensorEvent event = eventGenerator.getNextEvent();
        SmartHome smartHome = smartHomeLoader.load();

        while (event != null) {
            System.out.println("Got event: " + event);
            eventHandlerManager.passToHandlers(smartHome, event);
            event = eventGenerator.getNextEvent();
        }
    }
}
