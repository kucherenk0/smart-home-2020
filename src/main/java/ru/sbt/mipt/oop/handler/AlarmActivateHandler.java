package ru.sbt.mipt.oop.handler;

import ru.sbt.mipt.oop.model.SmartHome;
import ru.sbt.mipt.oop.model.alarm.Alarm;
import ru.sbt.mipt.oop.model.event.AlarmSensorEvent;
import ru.sbt.mipt.oop.model.event.SensorEvent;
import ru.sbt.mipt.oop.model.event.SensorEventType;

public class AlarmActivateHandler implements EventHandler {
    private Alarm alarm;

    public AlarmActivateHandler(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void handle(SmartHome smartHome, SensorEvent event) {
        if (event instanceof AlarmSensorEvent) {
            if (event.getType() == SensorEventType.ALARM_ACTIVATED) {
                AlarmSensorEvent alarmSensorEvent = (AlarmSensorEvent) event;
                alarm.activate(alarmSensorEvent.getCode());
            }
        }
    }
}
