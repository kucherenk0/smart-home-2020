package ru.sbt.mipt.oop.rс.commands;

import ru.sbt.mipt.oop.model.alarm.Alarm;
import ru.sbt.mipt.oop.rс.Command;

public class AlertAlarmCommand implements Command {

    private Alarm alarm;

    public AlertAlarmCommand(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void execute() {
        alarm.alert();
    }
}
