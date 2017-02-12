package TimeWidget.Stopwatch;

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

public class Stopwatch extends TimeWidget{
    private StopwatchView stopwatchView;
    private Duration totaltime;
    private boolean autostart;

    public Stopwatch(StopwatchView stopwatchView, Stage owner, String name, boolean autostart) {
        this.stopwatchView = stopwatchView;
        this.owner = owner;
        this.name = name;
        totaltime = Duration.ZERO;
        this.autostart = autostart;
        createWidget();

    }

    @Override
    protected void createWidgetBottom() {
        HBox timehbox = new HBox();
        timehbox.setAlignment(Pos.CENTER);

        timetxt = new Text(timeFormat(totaltime));
        timehbox.getChildren().add(timetxt);
        widget.add(timehbox, 0, 1, 4,1);

        createTimeButtons();

        executeExecutor();
        if(!autostart) {
            widget.getChildren().remove(pausebtn);
            widget.add(startbtn,0,2);
        }
    }

    @Override
    protected void executeExecutor() {
        executor = new ScheduledThreadPoolExecutor(1);
        addExecutors(executor);
        if(autostart) {
            futureTask = createFutureTask();
        }
    }

    @Override
    protected void resetTime() {
        totaltime = Duration.ZERO;
        cancelExecutor();
        updateGUI();
    }

    @Override
    protected ScheduledFuture<?> createFutureTask() {
        ScheduledFuture<?> scheduledFuture = executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                totaltime = totaltime.plus(Duration.ofMillis(10));
                updateGUI();
            }
        },0,10, TimeUnit.MILLISECONDS);
        return scheduledFuture;
    }

    @Override
    protected String timeFormat(Duration time){
        String timetxt;
        if (time.toMillis() < 1000 * 60) {
            String secs = time.toMillis()/1000 < 10 ? String.format("0%d", time.toMillis()/1000) : String.valueOf(time.toMillis()/1000);
            String millis = ((time.toMillis() % 1000)/10 < 10) ? String.format("0%d", (time.toMillis() % 1000)/10) : String.valueOf((time.toMillis() % 1000)/10);
            timetxt = String.format("%ss %s", secs, millis);
        }
        else if (time.toMillis() < 1000 * 60 *60 ) {
            String mins = time.toMinutes() < 10 ? String.format("0%d", time.toMinutes()): String.valueOf(time.toMinutes());
            String secs = ((time.toMillis()/1000) % 60) < 10 ? String.format("0%d", (time.toMillis()/1000) % 60) : String.valueOf((time.toMillis()/1000) % 60);
            String millis = ((time.toMillis() % 1000)/10 < 10) ? String.format("0%d", (time.toMillis() % 1000)/10) : String.valueOf((time.toMillis() % 1000)/10);
            timetxt = String.format("%sm %ss %s", mins, secs, millis);
        }
        else {
            String hrs = time.toHours() < 10 ? String.format("0%d", time.toHours()) : String.valueOf(time.toHours());
            String mins = ((time.toMillis()/(60 * 1000)) % 60) < 10 ? String.format("0%d", ((time.toMillis()/(60 * 1000)) % 60)) : String.valueOf(((time.toMillis()/(60 * 1000)) % 60));
            String secs = ((time.toMillis()/1000) % 60) < 10 ? String.format("0%d", ((time.toMillis()/1000) % 60)) : String.valueOf((time.toMillis()/1000) % 60);
            String millis = ((time.toMillis() % 1000)/10 < 10) ? String.format("0%d", ((time.toMillis() % 1000)/10)) : String.valueOf(((time.toMillis() % 1000)/10));
            timetxt = String.format("%sh %sm %ss %s", hrs, mins, secs, millis);
        }
        return timetxt;
    }

    @Override
    protected void updateGUI() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                timetxt.setText(timeFormat(totaltime));
            }
        });
    }

    @Override
    protected void closeEvent() {
        cancelExecutor();
        executor.shutdown();
        removeExecutors(executor);
        stopwatchView.getStopwatches().remove(getWidget());
    }
}
