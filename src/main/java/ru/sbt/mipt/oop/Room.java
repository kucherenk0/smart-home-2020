package ru.sbt.mipt.oop;

import java.util.Collection;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class Room {
    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public Collection<Light> getLights() {
        return lights;
    }

    public Collection<Door> getDoors() {
        return doors;
    }

    public String getName() {
        return name;
    }

    public void handleEvent(SensorEvent event){
       switch (event.getType()){
           case LIGHT_ON:
               for (Light light : lights) {

               }
           case DOOR_OPEN:
               for (Door door : doors) {
                   if (door.getId().equals(event.getObjectId())) {
                       if (event.getType() == DOOR_OPEN) {
                           door.setOpen(true);
                           System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
                       } else {
                           door.setOpen(false);
                           System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
                           // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                           // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                           if (room.getName().equals("hall")) {
                               for (Room homeRoom : smartHome.getRooms()) {
                                   for (Light light : homeRoom.getLights()) {
                                       light.setOn(false);
                                       SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                                       sendCommand(command);
                                   }
                               }
                           }
                       }
                   }
               }
           default:
               throw new  RuntimeException("Unsupported event type");
       }
    }
}
