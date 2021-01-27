package ru.sbt.mipt.oop.model;

import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.action.Action;

import java.util.Collection;

public class Room implements Actionable {
    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public Collection<Light> getLights() {
        return lights;
    }

    public Collection<Door> getDoors() {
        return doors;
    }

    public String getName() {
        return name;
    }

    @Override
    public void apply(Action action) {
        action.act(this);
        for (Light light : lights) {
            action.act(light);
        }
        for (Door door : doors) {
            action.act(door);
        }
    }
}
