package TimeWidget.Alarm;

import TimeWidget.Container.TimeWidget;

import java.util.concurrent.ScheduledFuture;

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
    protected void executeExecutor() {

    }

    @Override
    protected ScheduledFuture<?> createFutureTask() {
        return null;
    }

    @Override
    protected void cancelExecutor() {

    }

    @Override
    protected void updateGUI() {

    }

    @Override
    protected void closeEvent() {
        getAlarms().remove(getWidget());
    }
}
