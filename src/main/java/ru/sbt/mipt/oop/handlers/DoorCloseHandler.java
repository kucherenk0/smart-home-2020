package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.*;

public class DoorCloseHandler implements EventHandler {

    @Override
    public void handle(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == SensorEventType.DOOR_CLOSED) {
            for (Room room : smartHome.getRooms()) {
                for (Door door : room.getDoors()) {
                    if (door.getId().equals(event.getObjectId())) {
                        door.setOpen(false);
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
                    }
                }
            }
        }
    }
}
