package ru.sbt.mipt.oop.rс.commands;

import ru.sbt.mipt.oop.model.alarm.Alarm;
import ru.sbt.mipt.oop.rс.Command;

public class AlarmActivateCommand implements Command {
    private Alarm alarm;
    private String code;

    public AlarmActivateCommand(Alarm alarm, String code) {
        this.alarm = alarm;
        this.code = code;
    }

    @Override
    public void execute() {
        alarm.activate(code);
    }
}
