package remote.command;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.model.SmartHome;
import ru.sbt.mipt.oop.rс.commands.TurnOffAllLightsCommand;
import ru.sbt.mipt.oop.utils.SmartHomeJsonLoader;

public class TurnOnHallLightsCommandTest {

    private SmartHomeJsonLoader smartHomeJsonLoader;
    private SmartHome testSmartHome;

    @Before
    public void init() {
        smartHomeJsonLoader = new SmartHomeJsonLoader("smart-home-1.json");

    }

    @Test
    public void shouldTurnOnHallLightsWhenExecuted() {
        testSmartHome = smartHomeJsonLoader.load();
        TurnOffAllLightsCommand turnOffAllLightsCommand = new TurnOffAllLightsCommand(testSmartHome);

        turnOffAllLightsCommand.execute();

        Assert.assertFalse(testSmartHome.getRooms().stream()
                .filter(room -> room.getName().equals("hall")).anyMatch(room -> room.getLights().stream()
                        .anyMatch(light -> !light.isOn())));
    }
}
