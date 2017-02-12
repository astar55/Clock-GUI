package TimeWidget.Timer;

import TimeWidget.Container.TimeWidget;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.Duration;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static TimeWidget.Index.addExecutors;
import static TimeWidget.Index.removeExecutors;

public class Timer extends TimeWidget {

    private TimerView timerView;
    private final Duration totaltime;
    private Duration remaing;


    public Timer(TimerView timerView, Stage owner, String name, Duration time, String media) {
        this.timerView = timerView;
        this.owner = owner;
        this.name = name;
        this.totaltime = time;
        this.remaing = time;
        this.mediasrc = media;
        createWidget();
    }

    @Override
    protected void createWidgetBottom() {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);


        timetxt = new Text(timeFormat(getRemaingTime()));
        hBox.getChildren().add(timetxt);
        widget.add(hBox, 0,1,4,1);

        createTimeButtons();
        executeExecutor();
    }

    @Override
    protected void executeExecutor() {
        executor = new ScheduledThreadPoolExecutor(1);
        addExecutors(executor);
        futureTask = createFutureTask();

    }

    @Override
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
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    new TimerNotify(owner, name, timeFormat(totaltime), mediasrc);
                }
            });
        }
    }

    @Override
    protected void resetTime() {
        remaing = totaltime;
        cancelExecutor();
        updateGUI();

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
        removeExecutors(executor);
        timerView.getTimers().remove(getWidget());
    }

    public Duration getTotalTime() { return this.totaltime; }

    public Duration getRemaingTime() { return this.remaing; }
}
