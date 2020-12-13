package ru.sbt.mipt.oop.handler;

import ru.sbt.mipt.oop.action.HallDoorCloseAction;
import ru.sbt.mipt.oop.model.SmartHome;
import ru.sbt.mipt.oop.model.event.SensorEvent;
import ru.sbt.mipt.oop.model.event.SensorEventType;

public class HallDoorCloseHandler implements EventHandler {

    @Override
    public void handle(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == SensorEventType.DOOR_CLOSED) {
            smartHome.apply(new HallDoorCloseAction(event));
        }
    }
}
