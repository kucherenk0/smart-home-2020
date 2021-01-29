package ru.sbt.mipt.oop.model.alarm;

public class ActivatedAlarmState implements AlarmState {

    private String code;

    public ActivatedAlarmState(String code) {
        this.code = code;
    }

    @Override
    public AlarmState deactivate(String code) {
        if (code.equals(this.code)) {
            return new DeactivatedAlarmState(code);
        } else {
            System.out.println("Incorrect code sent!!!");
            return this.alert();
        }
    }

    @Override
    public ActivatedAlarmState activate(String code) {
        System.out.println("Alarm is already activated");
        return this;
    }

    @Override
    public AlarmState alert() {
        return new AlertAlarmState(this.code);
    }

}
