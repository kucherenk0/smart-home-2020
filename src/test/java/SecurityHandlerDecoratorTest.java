import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.sbt.mipt.oop.handler.DoorOpenHandler;
import ru.sbt.mipt.oop.handler.EventHandler;
import ru.sbt.mipt.oop.handler.SecurityHandlerDecorator;
import ru.sbt.mipt.oop.model.SmartHome;
import ru.sbt.mipt.oop.model.alarm.Alarm;
import ru.sbt.mipt.oop.model.event.SensorEvent;
import ru.sbt.mipt.oop.model.event.SensorEventType;
import ru.sbt.mipt.oop.utils.DummySmsMessageSender;
import ru.sbt.mipt.oop.utils.MessageSender;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;


public class SecurityHandlerDecoratorTest {

    private EventHandler mockEventHandler;
    private SmartHome mockSmartHome;
    private MessageSender mockMessageSender;

    @Before
    public void init() {
        mockSmartHome = Mockito.mock(SmartHome.class);
        mockEventHandler = Mockito.mock(DoorOpenHandler.class);
        mockMessageSender = Mockito.mock(DummySmsMessageSender.class);
    }

    @Test
    public void ShouldHandleEventsAsUsualWhenAlarmIsDeactivated() {
        Alarm alarm = new Alarm();
        SecurityHandlerDecorator handlerDecorator = new SecurityHandlerDecorator(mockEventHandler, mockMessageSender, alarm);
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, "1");

        handlerDecorator.handle(mockSmartHome, event);

        Assert.assertTrue(alarm.isDeactivated());
        Mockito.verify(mockEventHandler, times(1)).handle(mockSmartHome, event);
    }

    @Test
    public void ShouldNotHandleEventsWhenAlarmIsActivated() {
        String code = "code";
        Alarm alarm = new Alarm();
        alarm.activate(code);
        SecurityHandlerDecorator handlerDecorator = new SecurityHandlerDecorator(mockEventHandler, mockMessageSender, alarm);
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, "1");

        handlerDecorator.handle(mockSmartHome, event);

        Assert.assertTrue(alarm.isAlert());
        Mockito.verify(mockEventHandler, times(0)).handle(any(), any());
        Mockito.verify(mockMessageSender, times(1)).send("ATTENTION! Unauthorized access detected!");
    }


    @Test
    public void ShouldNotHandleEventsWhenAlarmIsAlerted() {
        String code = "code";
        Alarm alarm = new Alarm();
        alarm.activate(code);
        alarm.alert();
        SecurityHandlerDecorator handlerDecorator = new SecurityHandlerDecorator(mockEventHandler, mockMessageSender, alarm);
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, "1");

        handlerDecorator.handle(mockSmartHome, event);

        Assert.assertTrue(alarm.isAlert());
        Mockito.verify(mockEventHandler, times(0)).handle(any(), any());
        Mockito.verify(mockMessageSender, times(1)).send("ATTENTION! Unauthorized access detected!");
    }

}
