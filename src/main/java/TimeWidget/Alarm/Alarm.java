package TimeWidget.Alarm;

import TimeWidget.Container.TimeWidget;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.util.concurrent.ScheduledFuture;

import static TimeWidget.Alarm.AlarmView.getAlarms;

public class Alarm extends TimeWidget {

    private AlarmView alarmView;
    private LocalTime time;
    private long snoozetime;
    private LocalTime alarmtime;


    public Alarm(AlarmView alarmView, Stage owner, String name, LocalTime time, int snoozetime, String media) {
        this.alarmView = alarmView;
        this.owner = owner;
        this.name = name;
        this.time = time;
        this.snoozetime = snoozetime;
        this.mediasrc = media;
        createWidget();
    }

    @Override
    protected void createWidgetBottom() {

    }

    @Override
    protected void executeExecutor() {

    }

    @Override
    protected void resetTime() {

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
