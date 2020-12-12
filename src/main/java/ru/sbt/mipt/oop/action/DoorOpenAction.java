package ru.sbt.mipt.oop.action;

import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.model.Door;
import ru.sbt.mipt.oop.model.event.SensorEvent;

public class DoorOpenAction implements Action {

    private SensorEvent event;

    public DoorOpenAction(SensorEvent event) {
        this.event = event;
    }

    @Override
    public void act(Actionable actionable) {
        if (actionable instanceof Door) {
            Door door = (Door) actionable;
            if (door.getId().equals(event.getObjectId())) {
                door.setOpen(true);
                System.out.println("Door " + door.getId() + " was opened.");
            }
        }
    }
}

