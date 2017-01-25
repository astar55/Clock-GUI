package TimeWidget.Alarm;

import TimeWidget.Container.TimeWidget;

import static TimeWidget.Alarm.AlarmView.getAlarms;

public class Alarm extends TimeWidget {

    public Alarm(int id, String name) {
        super(id, name);
    }

    @Override
    protected void closeEvent() {
        getAlarms().remove(getId());
    }
}
