package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import remote.RemoteControl;
import remote.RemoteControlRegistry;
import ru.sbt.mipt.oop.adapter.HandlerCCAdapter;
import ru.sbt.mipt.oop.handler.*;
import ru.sbt.mipt.oop.model.SmartHome;
import ru.sbt.mipt.oop.model.alarm.Alarm;
import ru.sbt.mipt.oop.model.event.SensorEventType;
import ru.sbt.mipt.oop.rс.Command;
import ru.sbt.mipt.oop.rс.RemoteControlImpl;
import ru.sbt.mipt.oop.rс.commands.*;
import ru.sbt.mipt.oop.utils.DummySmsMessageSender;
import ru.sbt.mipt.oop.utils.MessageSender;
import ru.sbt.mipt.oop.utils.SmartHomeJsonLoader;
import ru.sbt.mipt.oop.utils.SmartHomeLoader;
import ru.sbt.mipt.oop.utils.event.EventGenerator;
import ru.sbt.mipt.oop.utils.event.EventHandlerManager;
import ru.sbt.mipt.oop.utils.event.RandomEventGenerator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Configuration
public class SmartHomeConfiguration {

    @Value("smart-home-1.json")
    private String fileName;

    @Value("12345678")
    private String alarmCode;


    @Bean
    SensorEventsManager sensorEventsManager(HandlerCCAdapter handlerCCAdapter) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(handlerCCAdapter);
        return sensorEventsManager;
    }

    @Bean
    HandlerCCAdapter handlerCCAdapter(EventHandlerManager eventHandlerManager) {
        return new HandlerCCAdapter(smartHome(), eventHandlerManager, eventTypeMap());
    }

    @Bean
    EventGenerator eventGenerator() {
        return new RandomEventGenerator();
    }

    @Bean
    SmartHomeLoader smartHomeLoader() {
        return new SmartHomeJsonLoader(fileName);
    }

    @Bean
    EventHandlerManager eventHandlerManager() {
        return new EventHandlerManager(eventHandlers());
    }

    @Bean
    Alarm alarm() {
        return new Alarm();
    }

    @Bean
    SmartHome smartHome() {
        return smartHomeLoader().load();
    }

    @Bean
    List<EventHandler> eventHandlers() {
        return Arrays.asList(
                new SecurityHandlerDecorator(new LightOffHandler(), dummyMessageSender(), alarm()),
                new SecurityHandlerDecorator(new LightOnHandler(), dummyMessageSender(), alarm()),
                new SecurityHandlerDecorator(new DoorCloseHandler(), dummyMessageSender(), alarm()),
                new SecurityHandlerDecorator(new DoorOpenHandler(), dummyMessageSender(), alarm()),
                new SecurityHandlerDecorator(new AlarmActivateHandler(alarm()), dummyMessageSender(), alarm()),
                new SecurityHandlerDecorator(new AlarmDeactivateHandler(alarm()), dummyMessageSender(), alarm()),
                new SecurityHandlerDecorator(new HallDoorCloseHandler(), dummyMessageSender(), alarm())
        );
    }

    @Bean
    MessageSender dummyMessageSender() {
        return new DummySmsMessageSender();
    }

    @Bean
    Map<String, SensorEventType> eventTypeMap() {
        Map map = new HashMap<>();
        map.put("LightIsOn", SensorEventType.LIGHT_ON);
        map.put("LightIsOff", SensorEventType.LIGHT_OFF);
        map.put("DoorIsOpen", SensorEventType.DOOR_OPEN);
        map.put("DoorIsClosed", SensorEventType.DOOR_CLOSED);
        map.put("DoorIsLocked", SensorEventType.DOOR_CLOSED);
        map.put("DoorIsUnlocked", SensorEventType.DOOR_OPEN);
        return map;
    }

    @Bean
    String remoteControllerId() {
        return "123";
    }

    @Bean
    RemoteControlRegistry remoteControlRegistry() {
        return new RemoteControlRegistry();
    }

    Map<String, Command> remoteCommands() {
        Map<String, Command> map = new HashMap<>();
        map.put("A", new AlarmActivateCommand(alarm(), alarmCode));
        map.put("B", new AlertAlarmCommand(alarm()));
        map.put("C", new CloseHallDoorCommand(smartHome()));
        map.put("D", new TurnOffAllLightsCommand(smartHome()));
        map.put("1", new TurnOnAllLightsCommand(smartHome()));
        map.put("2", new TurnOnHallLightCommand(smartHome()));
        return map;
    }

    @Bean
    RemoteControl remoteControl() {
        RemoteControlImpl remoteController = new RemoteControlImpl(remoteCommands());
        remoteControlRegistry().registerRemoteControl(
                remoteController,
                remoteControllerId()
        );
        return remoteController;
    }

}
