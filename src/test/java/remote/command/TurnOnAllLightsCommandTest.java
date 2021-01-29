package remote.command;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.model.SmartHome;
import ru.sbt.mipt.oop.rс.commands.TurnOnAllLightsCommand;
import ru.sbt.mipt.oop.utils.SmartHomeJsonLoader;

public class TurnOnAllLightsCommandTest {

    private SmartHomeJsonLoader smartHomeJsonLoader;
    private SmartHome testSmartHome;

    @Before
    public void init() {
        smartHomeJsonLoader = new SmartHomeJsonLoader("smart-home-1.json");

    }

    @Test
    public void shouldTurnOnAllLightsWhenExecuted() {
        testSmartHome = smartHomeJsonLoader.load();
        TurnOnAllLightsCommand turnOnAllLightsCommand = new TurnOnAllLightsCommand(testSmartHome);

        turnOnAllLightsCommand.execute();

        Assert.assertFalse(testSmartHome.getRooms().stream()
                .anyMatch(room -> room.getLights().stream()
                        .anyMatch(light -> !light.isOn())));
    }
}
