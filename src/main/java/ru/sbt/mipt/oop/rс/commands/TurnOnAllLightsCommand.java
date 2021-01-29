package ru.sbt.mipt.oop.rс.commands;


import ru.sbt.mipt.oop.model.Light;
import ru.sbt.mipt.oop.model.SmartHome;
import ru.sbt.mipt.oop.rс.Command;

public class TurnOnAllLightsCommand implements Command {
    private final SmartHome smartHome;

    public TurnOnAllLightsCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.apply(light -> {
            if (light instanceof Light) ((Light) light).setOn(true);
        });
    }
}
