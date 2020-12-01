package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.*;

public class LightOnHandler implements EventHandler {

    @Override
    public void handle(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == SensorEventType.LIGHT_ON) {
            for (Room room : smartHome.getRooms()) {
                for (Light light : room.getLights()) {
                    if (event.getObjectId().equals(light.getId())) {
                        light.setOn(true);
                        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
                    }
                }
            }
        }
    }
}
