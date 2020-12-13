package ru.sbt.mipt.oop.handler;

import ru.sbt.mipt.oop.action.DoorCloseAction;
import ru.sbt.mipt.oop.model.SmartHome;
import ru.sbt.mipt.oop.model.event.SensorEvent;
import ru.sbt.mipt.oop.model.event.SensorEventType;

public class DoorCloseHandler implements EventHandler {

    @Override
    public void handle(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == SensorEventType.DOOR_CLOSED) {
            smartHome.apply(new DoorCloseAction(event));
        }
    }
}

