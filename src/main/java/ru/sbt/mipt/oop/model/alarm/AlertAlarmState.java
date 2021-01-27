package ru.sbt.mipt.oop.model.alarm;

public class AlertAlarmState implements AlarmState {

    private String code;

    public AlertAlarmState(String code) {
        this.code = code;
        this.alert();
    }

    @Override
    public AlarmState activate(String code) {
        return this;
    }

    @Override
    public AlarmState deactivate(String code) {
        if (code.equals(this.code)) {
            return new DeactivatedAlarmState();
        } else {
            System.out.println("Wrong passcode");
            return this.alert();
        }
    }

    @Override
    public AlarmState alert() {
        System.out.println("Warning! Unauthorized access!");
        return this;
    }
}
