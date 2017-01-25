package TimeWidget.Alarm;

import TimeWidget.Container.TimeWidget;

import static TimeWidget.Alarm.AlarmView.getAlarms;

public class Alarm extends TimeWidget {


    public Alarm(String name) {
        this.name = name;
        createWidget();
    }

    @Override
    protected void createWidgetBottom() {

    }

    @Override
    protected void closeEvent() {
        getAlarms().remove(getWidget());
    }
}
