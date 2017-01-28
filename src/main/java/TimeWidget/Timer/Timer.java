package TimeWidget.Timer;

import TimeWidget.Container.TimeWidget;
import javafx.application.Platform;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.time.Duration;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


import static TimeWidget.Timer.TimerView.getTimers;

public class Timer extends TimeWidget {

    private final Duration totaltime;
    private Duration remaing;


    public Timer(String name, Duration time) {
        this.name = name;
        this.totaltime = time;
        this.remaing = time;
        createWidget();

    }

    @Override
    protected void createWidgetBottom() {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);

        System.out.println(this.totaltime + " " + this.remaing + " " + getRemaingTime()+ " " + timeFormat(getRemaingTime()));

        timetxt = new Text(timeFormat(getRemaingTime()));
        hBox.getChildren().add(timetxt);
        widget.add(hBox, 0,1,4,1);
        System.out.println(this.totaltime + " " + this.remaing + " " + getRemaingTime()+ " " + timeFormat(getRemaingTime()));

        createTimeButtons();

    }

    @Override
    protected void executeExecutor() {
        executor = new ScheduledThreadPoolExecutor(1);
        futureTask = createFutureTask();

    }

    protected ScheduledFuture<?> createFutureTask() {
        ScheduledFuture<?> scheduledFuture = executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                updateRemaining();
            }
        }, 0, 1, TimeUnit.SECONDS);
        return scheduledFuture;
    }

    private void updateRemaining() {

        if (getRemaingTime().toMillis() > 0) {
            remaing = remaing.minusMillis(1000);
            updateGUI();
        }
        else {
            cancelExecutor();
        }
    }

    @Override
    protected void resetTime() {
        remaing = totaltime;
        cancelExecutor();
        updateGUI();

    }

    @Override
    protected void cancelExecutor() {
        getFutureTask().cancel(true);
    }

    @Override
    protected void updateGUI() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                setTimetxt(timeFormat(getRemaingTime()));
            }
        });
    }

    @Override
    protected void closeEvent() {
        cancelExecutor();
        getExecutor().shutdown();
        getTimers().remove(getWidget());
    }

    public Duration getTotalTime() { return this.totaltime; }

    public Duration getRemaingTime() { return this.remaing; }
}
