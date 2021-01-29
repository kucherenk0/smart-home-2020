package ru.sbt.mipt.oop.model.event;

public class AlarmSensorEvent extends SensorEvent {
    private final SensorEventType type;
    private final String objectId;
    private final String code;

    public AlarmSensorEvent(SensorEventType type, String objectId, String code) {
        super(type, objectId);
        this.type = type;
        this.objectId = objectId;
        this.code = code;
    }

    public SensorEventType getType() {
        return type;
    }

    public String getObjectId() {
        return objectId;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "SensorEvent{" +
                "type=" + type +
                ", objectId='" + objectId + '\'' +
                '}';
    }
}
