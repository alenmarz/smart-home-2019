package ru.sbt.mipt.oop.home.objects.handlers.decorator;


import ru.sbt.mipt.oop.home.EventHandler;
import ru.sbt.mipt.oop.home.alarm.Alarm;
import ru.sbt.mipt.oop.home.alarm.notification.Notification;
import ru.sbt.mipt.oop.home.alarm.notification.SMS;

public class AlarmDecorator extends EventHandlerDecorator {
    private Alarm alarm;

    public AlarmDecorator(EventHandler handler, Alarm alarm) {
        super(handler);
        this.alarm = alarm;
    }

    @Override
    public void handleEvent(Object object) {
        Notification notification = new SMS();

        switch (alarm.getType()) {
            case ACTIVATED:
                wrappee.handleEvent(object);
                alarm.alarm();
                notification.send("ALARM!");
                break;
            case ALARM:
                notification.send("ALARM!");
                break;
            case DEACTIVATED:
                wrappee.handleEvent(object);
        }
    }
}
