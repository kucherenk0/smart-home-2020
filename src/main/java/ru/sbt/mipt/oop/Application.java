package ru.sbt.mipt.oop;

public class Application {
    private EventGenerator eventGenerator;
    private SmartHomeLoader smartHomeLoader;
    private EventHandler eventHandler;

    public Application(EventGenerator eventGenerator, SmartHomeLoader smartHomeLoader) {
        this.eventGenerator = eventGenerator;
        this.smartHomeLoader = smartHomeLoader;
    }

    public static void main(String... args) {
        new Application(
                new RandomEventGenerator(),
                new SmartHomeJsonLoader("smart-home-1.json")
        ).run();
    }

    private void run() {
        SensorEvent event = eventGenerator.getNextEvent();
        SmartHome smartHome = smartHomeLoader.load();
        eventHandler = new EventHandler(smartHome);

        while (event != null) {
            System.out.println("Got event: " + event);
            eventHandler.handle(event);
            event = eventGenerator.getNextEvent();
        }
    }
}
