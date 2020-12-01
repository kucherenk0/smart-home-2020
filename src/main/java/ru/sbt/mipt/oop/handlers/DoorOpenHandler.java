package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.*;

public class DoorOpenHandler implements EventHandler {

    @Override
    public void handle(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == SensorEventType.DOOR_OPEN) {
            for (Room room : smartHome.getRooms()) {
                for (Door door : room.getDoors()) {
                    if (event.getObjectId().equals(door.getId())) {
                        door.setOpen(true);
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
                    }
                }
            }
        }
    }
}
