import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.model.alarm.Alarm;


public class AlarmTest {

    @Test
    public void ShouldBeInAlertStateWhenCodeIdIncorrect() {
        String code = "very strong password";
        Alarm alarm = new Alarm();
        alarm.activate(code);

        alarm.deactivate("wrong code");

        Assert.assertTrue(alarm.isAlert());
    }

    @Test
    public void ShouldBeDeactivatedWhenCodeIsCorrect() {
        String code = "very strong password";
        Alarm alarm = new Alarm();

        alarm.activate(code);
        alarm.deactivate("wrong code");
        alarm.deactivate(code);

        Assert.assertTrue(alarm.isDeactivated());
    }

    @Test
    public void ShouldBeActiveWhenActivated() {
        String code = "very strong password";
        Alarm alarm = new Alarm();

        alarm.activate(code);

        Assert.assertTrue(alarm.isActivated());
    }
}
