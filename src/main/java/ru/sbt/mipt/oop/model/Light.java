package ru.sbt.mipt.oop.model;

import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.action.Action;

public class Light implements Actionable {
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

    @Override
    public void apply(Action action) {
        action.act(this);
    }
}
