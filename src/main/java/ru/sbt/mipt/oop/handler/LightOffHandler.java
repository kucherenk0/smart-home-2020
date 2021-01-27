package ru.sbt.mipt.oop.handler;

import ru.sbt.mipt.oop.action.LightOffAction;
import ru.sbt.mipt.oop.model.SmartHome;
import ru.sbt.mipt.oop.model.event.SensorEvent;
import ru.sbt.mipt.oop.model.event.SensorEventType;

public class LightOffHandler implements EventHandler {

    @Override
    public void handle(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == SensorEventType.LIGHT_OFF) {
            smartHome.apply(new LightOffAction(event));
        }
    }
}
