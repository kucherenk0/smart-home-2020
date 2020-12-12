package ru.sbt.mipt.oop.handler;

import ru.sbt.mipt.oop.action.LightOnAction;
import ru.sbt.mipt.oop.model.SmartHome;
import ru.sbt.mipt.oop.model.event.SensorEvent;
import ru.sbt.mipt.oop.model.event.SensorEventType;

public class LightOnHandler implements EventHandler {

    @Override
    public void handle(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == SensorEventType.LIGHT_ON) {
            smartHome.apply(new LightOnAction(event));
        }
    }
}
