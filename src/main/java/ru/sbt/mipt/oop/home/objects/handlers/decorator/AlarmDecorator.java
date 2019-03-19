package ru.sbt.mipt.oop.home.objects.handlers.decorator;


import ru.sbt.mipt.oop.home.EventHandler;

public class AlarmDecorator extends EventHandlerDecorator {
    public AlarmDecorator(EventHandler handler) {
        super(handler);
    }

    @Override
    public void handleEvent(Object object) {
        switch (wrappee.getSmartHome().getAlarm().getState().getType()) {
            case ACTIVATED:
                super.handleEvent(object);
                wrappee.getSmartHome().getAlarm().getState().setAlarmState();
                sendSMS();
                break;
            case ALARM:
                sendSMS();
                break;
            case DEACTIVATED:
                super.handleEvent(object);
        }
    }

    private void sendSMS() {
        System.out.println("Sending SMS...");
    }
}
