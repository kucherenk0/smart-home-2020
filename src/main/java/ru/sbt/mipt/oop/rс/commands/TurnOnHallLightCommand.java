package ru.sbt.mipt.oop.rс.commands;


import ru.sbt.mipt.oop.model.Light;
import ru.sbt.mipt.oop.model.Room;
import ru.sbt.mipt.oop.model.SmartHome;
import ru.sbt.mipt.oop.rс.Command;

public class TurnOnHallLightCommand implements Command {
    private final SmartHome smartHome;

    public TurnOnHallLightCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.apply(room -> {
            if (room instanceof Room && ((Room) room).getName().equals("hall")) {
                room.apply(light -> {
                    if (light instanceof Light) ((Light) light).setOn(true);
                });
            }
        });
    }
}
