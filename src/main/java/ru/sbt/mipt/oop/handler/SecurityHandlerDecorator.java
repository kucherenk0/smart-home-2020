package ru.sbt.mipt.oop.handler;

import ru.sbt.mipt.oop.model.SmartHome;
import ru.sbt.mipt.oop.model.alarm.Alarm;
import ru.sbt.mipt.oop.model.event.SensorEvent;
import ru.sbt.mipt.oop.model.event.SensorEventType;
import ru.sbt.mipt.oop.utils.MessageSender;

public class SecurityHandlerDecorator implements EventHandler {

    private final EventHandler wrappee;
    private final MessageSender messageSender;
    private final Alarm alarm;

    public SecurityHandlerDecorator(EventHandler wrappee, MessageSender messageSender, Alarm alarm) {
        this.alarm = alarm;
        this.wrappee = wrappee;
        this.messageSender = messageSender;
    }

    private boolean isAlarmEvent(SensorEvent event) {
        return event.getType() == SensorEventType.ALARM_ACTIVATED ||
                event.getType() == SensorEventType.ALARM_DEACTIVATED;
    }

    @Override
    public void handle(SmartHome smartHome, SensorEvent event) {
        if (isAlarmEvent(event)) {
            wrappee.handle(smartHome, event);
        }
        if (alarm.isActivated() || alarm.isAlert()) {
            alarm.alert();
            System.out.println("Sensor detection while alarm is on");
            messageSender.send("ATTENTION! Unauthorized access detected!");
        } else {
            wrappee.handle(smartHome, event);
        }
    }
}