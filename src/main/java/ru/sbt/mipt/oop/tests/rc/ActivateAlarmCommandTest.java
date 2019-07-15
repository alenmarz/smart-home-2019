package ru.sbt.mipt.oop.tests.rc;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import rc.RemoteControl;
import ru.sbt.mipt.oop.configuration.Configuration;
import ru.sbt.mipt.oop.configuration.JsonConfigurationLoader;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.alarm.Alarm;
import ru.sbt.mipt.oop.home.alarm.AlarmState;
import ru.sbt.mipt.oop.home.loader.JsonSmartHomeLoader;
import ru.sbt.mipt.oop.home.remote.RemoteController;
import ru.sbt.mipt.oop.home.remote.command.ActivateAlarmCommand;

import java.io.IOException;

class ActivateAlarmCommandTest {
    @Test
    void Test() throws IOException {
        SmartHome smartHome = new JsonSmartHomeLoader("smart-home-1.js").load();
        Configuration configuration = new JsonConfigurationLoader("config.js").load();
        String securityCode = configuration.getSecurityCode();
        smartHome.setAlarm(new Alarm(configuration));
        RemoteControl rc = new RemoteController();
        ((RemoteController) rc).setCommand("A", new ActivateAlarmCommand(smartHome, securityCode));
        rc.onButtonPressed("A");
        Assert.assertEquals(AlarmState.StateType.ACTIVATED, smartHome.getAlarm().getAlarmSystem().getState().getType());
    }
}