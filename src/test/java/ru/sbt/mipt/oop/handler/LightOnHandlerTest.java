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

public class LightOnHandlerTest {
    private LightOnHandler lightOnHandler;

    @Before
    public void init() {
        lightOnHandler = new LightOnHandler();
    }

    @Test
    public void shouldApplyActionToSmartHomeWhenEventIsLightOn() {
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ON, "1");
        SmartHome mockSmartHome = Mockito.mock(SmartHome.class);

        lightOnHandler.handle(mockSmartHome, event);

        ArgumentCaptor<Action> argumentCaptor = ArgumentCaptor.forClass(Action.class);
        Mockito.verify(mockSmartHome, times(1)).apply(any());
    }

    @Test
    public void shouldNotApplyActionToSmartHomeWhenEventIsNotLightOn() {
        SensorEvent event1 = new SensorEvent(SensorEventType.DOOR_CLOSED, "1");
        SensorEvent event2 = new SensorEvent(SensorEventType.LIGHT_OFF, "2");
        SensorEvent event3 = new SensorEvent(SensorEventType.DOOR_OPEN, "3");
        SmartHome mockSmartHome = Mockito.mock(SmartHome.class);

        lightOnHandler.handle(mockSmartHome, event1);
        lightOnHandler.handle(mockSmartHome, event2);
        lightOnHandler.handle(mockSmartHome, event3);

        ArgumentCaptor<Action> argumentCaptor = ArgumentCaptor.forClass(Action.class);
        Mockito.verify(mockSmartHome, times(0)).apply(any());
    }

}
