package remote.command;

import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.model.alarm.Alarm;
import ru.sbt.mipt.oop.rс.commands.AlarmActivateCommand;

public class AlarmActivateCommandTest {

    @Test
    public void shouldTurnOnHallLightsWhenExecuted() {
        Alarm alarm = new Alarm();
        String code = "code";
        AlarmActivateCommand alarmActivateCommand = new AlarmActivateCommand(alarm, code);

        alarmActivateCommand.execute();

        Assert.assertTrue(alarm.isActivated());
    }
}
