package ru.sbt.mipt.oop.handler.handler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import ru.sbt.mipt.oop.action.Action;
import ru.sbt.mipt.oop.handler.DoorOpenHandler;
import ru.sbt.mipt.oop.model.SmartHome;
import ru.sbt.mipt.oop.model.event.SensorEvent;
import ru.sbt.mipt.oop.model.event.SensorEventType;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;

public class DoorOpenHandlerTest {

    private DoorOpenHandler doorOpenHandler;

    @Before
    public void init() {
        doorOpenHandler = new DoorOpenHandler();
    }

    @Test
    public void shouldApplyActionToSmartHomeWhenEventIsDoorOpen() {
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, "1");
        SmartHome mockSmartHome = Mockito.mock(SmartHome.class);

        doorOpenHandler.handle(mockSmartHome, event);

        ArgumentCaptor<Action> argumentCaptor = ArgumentCaptor.forClass(Action.class);
        Mockito.verify(mockSmartHome, times(1)).apply(any());
    }

    @Test
    public void shouldNotApplyActionToSmartHomeWhenEventIsNotDoorOpen() {
        SensorEvent event1 = new SensorEvent(SensorEventType.DOOR_OPEN, "1");
        SensorEvent event2 = new SensorEvent(SensorEventType.LIGHT_OFF, "2");
        SensorEvent event3 = new SensorEvent(SensorEventType.LIGHT_ON, "3");
        SmartHome mockSmartHome = Mockito.mock(SmartHome.class);

        doorOpenHandler.handle(mockSmartHome, event1);
        doorOpenHandler.handle(mockSmartHome, event2);
        doorOpenHandler.handle(mockSmartHome, event3);

        ArgumentCaptor<Action> argumentCaptor = ArgumentCaptor.forClass(Action.class);
        Mockito.verify(mockSmartHome, times(0)).apply(any());
    }

}
