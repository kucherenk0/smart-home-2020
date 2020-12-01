package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.*;

public class HallDoorCloseHandler implements EventHandler {


    @Override
    public void handle(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == SensorEventType.DOOR_CLOSED) {
            for (Room room : smartHome.getRooms()) {
                if (room.getName().equals("hall")) {
                    for (Door door : room.getDoors()) {
                        if (door.getId().equals(event.getObjectId())) {
                            door.setOpen(false);
                            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
                            turnOffAllLights(smartHome);
                        }
                    }
                }
            }
        }
    }


    private void turnOffAllLights(SmartHome smartHome) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                light.setOn(false);
                System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
                //  SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                //  sendCommand(command);
            }
        }
        System.out.println("All lights were turned off.");
    }
}
