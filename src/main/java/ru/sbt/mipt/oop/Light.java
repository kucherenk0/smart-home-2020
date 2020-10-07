package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class Light {
    private boolean isOn;
    private final String id;

    public Light(String id, boolean isOn) {
        this.id = id;
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    public String getId() {
        return id;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public void handleEvent(SensorEvent event){
         if (id.equals(event.getObjectId())) {
             if (event.getType() == LIGHT_ON) {
                 isOn = true;
                 // System.out.println("Light " + id + " in room " + room.getName() + " was turned on.");
             } else {
                 isOn = false;
                 // System.out.println("Light " + id + " in room " + room.getName() + " was turned off.");
             }
         }

     }
}
