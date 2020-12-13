package ru.sbt.mipt.oop.action;

import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.model.Light;
import ru.sbt.mipt.oop.model.event.SensorEvent;

public class LightOnAction implements Action {

    private SensorEvent event;

    public LightOnAction(SensorEvent event) {
        this.event = event;
    }

    @Override
    public void act(Actionable actionable) {
        if (actionable instanceof Light) {
            Light light = (Light) actionable;
            if (light.getId().equals(event.getObjectId())) {
                light.setOn(true);
                System.out.println("Light " + light.getId() + " was turned on.");
            }
        }
    }
}

