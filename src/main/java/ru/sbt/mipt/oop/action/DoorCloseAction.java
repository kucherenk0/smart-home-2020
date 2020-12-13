package ru.sbt.mipt.oop.action;

import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.model.Door;
import ru.sbt.mipt.oop.model.event.SensorEvent;

public class DoorCloseAction implements Action {

    private SensorEvent event;

    public DoorCloseAction(SensorEvent event) {
        this.event = event;
    }

    @Override
    public void act(Actionable actionable) {
        if (actionable instanceof Door) {
            Door door = (Door) actionable;
            if (door.getId().equals(event.getObjectId())) {
                door.setOpen(false);
                System.out.println("Door " + door.getId() + " was closed.");
            }
        }
    }
}

