package ru.sbt.mipt.oop.model.alarm;

public class DeactivatedAlarmState implements AlarmState {

    public DeactivatedAlarmState() {
        System.out.println("Alarm is deactivated");
    }

    @Override
    public AlarmState activate(String code) {
        return new ActivatedAlarmState(code);
    }

    @Override
    public AlarmState deactivate(String code) {
        System.out.println("Alarm is already deactivated");
        return this;
    }

    @Override
    public AlarmState alert() {
        System.out.println("Alarm is deactivated");
        return this;
    }
}
