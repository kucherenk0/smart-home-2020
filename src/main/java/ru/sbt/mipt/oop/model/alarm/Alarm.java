package ru.sbt.mipt.oop.model.alarm;

public class Alarm {
    private AlarmState state;
    private String code;

    public Alarm() {
        this.state = new DeactivatedAlarmState();
    }

    public void activate(String code) {
        this.code = code;
        this.state = new ActivatedAlarmState(code);
    }

    public void deactivate(String code) {
        this.state = state.deactivate(code);
    }

    public void alert() {
        this.state = state.alert();
    }

    public boolean isActivated() {
        return state instanceof ActivatedAlarmState;
    }

    public boolean isDeactivated() {
        return state instanceof DeactivatedAlarmState;
    }

    public boolean isAlert() {
        return state instanceof AlertAlarmState;
    }
}
