package remote.command;

import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.model.alarm.Alarm;
import ru.sbt.mipt.oop.rс.commands.AlertAlarmCommand;

public class AlarmAlertCommandTest {

    @Test
    public void shouldAlertAlarmWhenExecuted() {
        Alarm alarm = new Alarm();
        String code = "code";
        alarm.activate(code);
        AlertAlarmCommand alertAlarmCommand = new AlertAlarmCommand(alarm);


        alertAlarmCommand.execute();

        Assert.assertTrue(alarm.isAlert());
    }
}
