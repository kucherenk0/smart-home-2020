package ru.sbt.mipt.oop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.handler.*;
import ru.sbt.mipt.oop.model.SmartHome;
import ru.sbt.mipt.oop.model.alarm.Alarm;
import ru.sbt.mipt.oop.model.event.SensorEventType;
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


    @Bean
    EventListener eventListener() {
        return new EventListener(eventGenerator(), smartHomeLoader(), eventHandlerManager());
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
                new LightOffHandler(),
                new LightOnHandler(),
                new DoorCloseHandler(),
                new DoorOpenHandler(),
                new HallDoorCloseHandler()
        );
    }

    @Bean
    Map<String, SensorEventType> eventTypeMap() {
        Map map = new HashMap<>();
        map.put("LightIsOn", SensorEventType.LIGHT_ON);
        map.put("LightIsOff", SensorEventType.LIGHT_OFF);
        map.put("DoorIsOpen", SensorEventType.DOOR_OPEN);
        map.put("DoorIsClosed", SensorEventType.DOOR_CLOSED);
        return map;
    }

}
