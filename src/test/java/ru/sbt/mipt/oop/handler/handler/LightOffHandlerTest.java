package ru.sbt.mipt.oop.handler.handler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import ru.sbt.mipt.oop.action.Action;
import ru.sbt.mipt.oop.handler.LightOffHandler;
import ru.sbt.mipt.oop.model.SmartHome;
import ru.sbt.mipt.oop.model.event.SensorEvent;
import ru.sbt.mipt.oop.model.event.SensorEventType;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;

public class LightOffHandlerTest {
    private LightOffHandler lightOffHandler;

    @Before
    public void init() {
        lightOffHandler = new LightOffHandler();
    }

    @Test
    public void shouldApplyActionToSmartHomeWhenEventIsLightOff() {
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_OFF, "1");
        SmartHome mockSmartHome = Mockito.mock(SmartHome.class);

        lightOffHandler.handle(mockSmartHome, event);

        ArgumentCaptor<Action> argumentCaptor = ArgumentCaptor.forClass(Action.class);
        Mockito.verify(mockSmartHome, times(1)).apply(any());
    }

    @Test
    public void shouldNotApplyActionToSmartHomeWhenEventIsNotLightOff() {
        SensorEvent event1 = new SensorEvent(SensorEventType.DOOR_CLOSED, "1");
        SensorEvent event2 = new SensorEvent(SensorEventType.DOOR_OPEN, "2");
        SensorEvent event3 = new SensorEvent(SensorEventType.LIGHT_ON, "3");
        SmartHome mockSmartHome = Mockito.mock(SmartHome.class);

        lightOffHandler.handle(mockSmartHome, event1);
        lightOffHandler.handle(mockSmartHome, event2);
        lightOffHandler.handle(mockSmartHome, event3);

        ArgumentCaptor<Action> argumentCaptor = ArgumentCaptor.forClass(Action.class);
        Mockito.verify(mockSmartHome, times(0)).apply(any());
    }
}
