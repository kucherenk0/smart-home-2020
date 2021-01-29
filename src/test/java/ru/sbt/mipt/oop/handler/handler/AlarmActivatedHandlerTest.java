package ru.sbt.mipt.oop.handler.handler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.sbt.mipt.oop.handler.AlarmActivateHandler;
import ru.sbt.mipt.oop.model.SmartHome;
import ru.sbt.mipt.oop.model.alarm.Alarm;
import ru.sbt.mipt.oop.model.event.AlarmSensorEvent;
import ru.sbt.mipt.oop.model.event.SensorEvent;
import ru.sbt.mipt.oop.model.event.SensorEventType;

import static org.mockito.Mockito.times;

public class AlarmActivatedHandlerTest {

    private AlarmActivateHandler alarmActivateHandler;
    private Alarm mockAlarm;
    private SmartHome mockSmartHome;

    @Before
    public void init() {
        mockAlarm = Mockito.mock(Alarm.class);
        mockSmartHome = Mockito.mock(SmartHome.class);
        alarmActivateHandler = new AlarmActivateHandler(mockAlarm);
    }


    @Test
    public void shouldActivateAlarmWhenEventIsAlarmActivate() {
        String code = "code";
        SensorEvent event = new AlarmSensorEvent(SensorEventType.ALARM_ACTIVATED, "1", code);

        alarmActivateHandler.handle(mockSmartHome, event);

        Mockito.verify(mockAlarm, times(1)).activate(code);
    }

}

