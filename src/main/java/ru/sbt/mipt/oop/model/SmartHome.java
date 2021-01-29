package ru.sbt.mipt.oop.model;

import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.action.Action;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {
    private Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    @Override
    public void apply(Action action) {
        action.act(this);
        for (Room room : rooms) {
            room.apply(action);
        }
    }
}
