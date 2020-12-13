package ru.sbt.mipt.oop.handler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import ru.sbt.mipt.oop.action.Action;
import ru.sbt.mipt.oop.model.SmartHome;
import ru.sbt.mipt.oop.model.event.SensorEvent;
import ru.sbt.mipt.oop.model.event.SensorEventType;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;

public class HallDoorCloseHandlerTest {

    private HallDoorCloseHandler hallDoorCloseHandler;

    @Before
    public void init() {
        hallDoorCloseHandler = new HallDoorCloseHandler();
    }

    @Test
    public void shouldApplyActionToSmartHomeWhenEventIsDoorOpen() {
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, "1");
        SmartHome mockSmartHome = Mockito.mock(SmartHome.class);

        hallDoorCloseHandler.handle(mockSmartHome, event);

        ArgumentCaptor<Action> argumentCaptor = ArgumentCaptor.forClass(Action.class);
        Mockito.verify(mockSmartHome, times(1)).apply(any());
    }

    @Test
    public void shouldNotApplyActionToSmartHomeWhenEventIsNotDoorOpen() {
        SensorEvent event1 = new SensorEvent(SensorEventType.DOOR_OPEN, "1");
        SensorEvent event2 = new SensorEvent(SensorEventType.LIGHT_OFF, "2");
        SensorEvent event3 = new SensorEvent(SensorEventType.LIGHT_ON, "3");
        SmartHome mockSmartHome = Mockito.mock(SmartHome.class);

        hallDoorCloseHandler.handle(mockSmartHome, event1);
        hallDoorCloseHandler.handle(mockSmartHome, event2);
        hallDoorCloseHandler.handle(mockSmartHome, event3);

        ArgumentCaptor<Action> argumentCaptor = ArgumentCaptor.forClass(Action.class);
        Mockito.verify(mockSmartHome, times(0)).apply(any());
    }

}
