package ru.sbt.mipt.oop.rс.commands;

import ru.sbt.mipt.oop.model.Door;
import ru.sbt.mipt.oop.model.Room;
import ru.sbt.mipt.oop.model.SmartHome;
import ru.sbt.mipt.oop.rс.Command;

public class CloseHallDoorCommand implements Command {

    private SmartHome smartHome;

    public CloseHallDoorCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.apply(room -> {
            if (room instanceof Room && ((Room) room).getName().equals("hall")) {
                room.apply(door -> {
                    if (door instanceof Door) {
                        ((Door) door).setOpen(false);
                    }
                });
            }
        });
    }
}
