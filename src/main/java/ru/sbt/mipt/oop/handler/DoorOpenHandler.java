package ru.sbt.mipt.oop.handler;

import ru.sbt.mipt.oop.action.DoorOpenAction;
import ru.sbt.mipt.oop.model.SmartHome;
import ru.sbt.mipt.oop.model.event.SensorEvent;
import ru.sbt.mipt.oop.model.event.SensorEventType;

public class DoorOpenHandler implements EventHandler {

    @Override
    public void handle(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == SensorEventType.DOOR_OPEN) {
            smartHome.apply(new DoorOpenAction(event));
        }
    }
}
