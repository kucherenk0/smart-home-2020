package ru.sbt.mipt.oop.action;

import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.model.Door;
import ru.sbt.mipt.oop.model.Light;
import ru.sbt.mipt.oop.model.Room;
import ru.sbt.mipt.oop.model.SmartHome;
import ru.sbt.mipt.oop.model.event.SensorEvent;

public class HallDoorCloseAction implements Action {

    private SensorEvent event;

    public HallDoorCloseAction(SensorEvent event) {
        this.event = event;
    }

    @Override
    public void act(Actionable actionable) {
        if (actionable instanceof SmartHome) {
            SmartHome smartHome = (SmartHome) actionable;
            for (Room room : smartHome.getRooms()) {
                if (room.getName().equals("hall")) {
                    for (Door door : room.getDoors()) {
                        if (door.getId().equals(event.getObjectId())) {
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
            }
        }
    }
}

