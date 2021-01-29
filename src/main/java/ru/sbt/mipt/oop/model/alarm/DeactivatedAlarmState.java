package ru.sbt.mipt.oop.model.alarm;

public class DeactivatedAlarmState implements AlarmState {

    private String code;

    public DeactivatedAlarmState(String code) {
        this.code = code;
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
        return new AlertAlarmState(code);
    }
}
