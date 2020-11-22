package ru.sbt.mipt.oop;

public class EventHandler {

    private SmartHome smartHome;

    public EventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void handle(SensorEvent event) {
        switch (event.getType()) {
            case LIGHT_ON:
                handleLightOn(event);
            case LIGHT_OFF:
                handleLightOff(event);
            case DOOR_OPEN:
                handleDoorOpen(event);
            case DOOR_CLOSED:
                handleDoorClosed(event);
            default:
                throw new RuntimeException("Unsupported event type " + event.getType());
        }
    }


    private void handleLightOn(SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (event.getObjectId().equals(light.getId())) {
                    light.setOn(true);
                    System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
                }
            }
        }
    }

    private void handleLightOff(SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (event.getObjectId().equals(light.getId())) {
                    light.setOn(false);
                    System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
                }
            }
        }
    }

    private void handleDoorOpen(SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (event.getObjectId().equals(door.getId())) {
                    door.setOpen(true);
                    System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
                }
            }
        }
    }

    private void handleDoorClosed(SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    door.setOpen(false);
                    System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
                    if (room.getName().equals("hall")) {
                        turnAllLights();
                    }
                }
            }
        }
    }

    private void turnAllLights() {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                light.setOn(false);
                System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
                //  SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                //  sendCommand(command);
            }
        }
    }
}
