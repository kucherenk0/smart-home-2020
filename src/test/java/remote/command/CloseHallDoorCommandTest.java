package remote.command;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.model.SmartHome;
import ru.sbt.mipt.oop.rс.commands.CloseHallDoorCommand;
import ru.sbt.mipt.oop.utils.SmartHomeJsonLoader;

public class CloseHallDoorCommandTest {

    private SmartHomeJsonLoader smartHomeJsonLoader;
    private SmartHome testSmartHome;

    @Before
    public void init() {
        smartHomeJsonLoader = new SmartHomeJsonLoader("smart-home-1.json");

    }

    @Test
    public void shouldTurnOnHallLightsWhenExecuted() {
        testSmartHome = smartHomeJsonLoader.load();
        CloseHallDoorCommand closeHallDoorCommand = new CloseHallDoorCommand(testSmartHome);

        closeHallDoorCommand.execute();

        Assert.assertFalse(testSmartHome.getRooms().stream()
                .filter(room -> room.getName().equals("hall")).anyMatch(room -> room.getDoors().stream()
                        .anyMatch(door -> door.isOpen())));

    }
}
