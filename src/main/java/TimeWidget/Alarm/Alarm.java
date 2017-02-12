package TimeWidget.Alarm;

import TimeWidget.Container.TimeWidget;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static TimeWidget.Alarm.AlarmView.getAlarms;
import static TimeWidget.Index.addExecutors;
import static TimeWidget.Index.removeExecutors;

public class Alarm extends TimeWidget {

    private AlarmView alarmView;
    private LocalTime time;
    private long snoozetime;
    private ImageView offalarmswitch, onalarmswitch;


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
        //widget.setGridLinesVisible(true);

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPercentHeight(33);
        widget.getRowConstraints().addAll(rowConstraints);

        BorderPane borderPane = new BorderPane();
        timetxt = new Text(this.time.toString());
        borderPane.setCenter(timetxt);
        widget.add(borderPane, 0, 1, 3, 1);


        BorderPane alarmswitchPane = new BorderPane();
        offalarmswitch = new ImageView(new Image(getClass().getResourceAsStream("/ic_alarm_black_48dp_1x.png")));
        onalarmswitch = new ImageView(new Image(getClass().getResourceAsStream("/ic_alarm_white_24dp_2x.png")));
        alarmswitchPane.setCenter(onalarmswitch);
        widget.add(alarmswitchPane, 3,1);
        alarmswitchPane.setOnMouseClicked(event -> {
            if (alarmswitchPane.getChildren().contains(onalarmswitch)) {
                alarmswitchPane.setCenter(offalarmswitch);
                cancelExecutor();
            }
            else {
                alarmswitchPane.setCenter(onalarmswitch);
                futureTask = createFutureTask();
            }
        });

        executeExecutor();
    }

    @Override
    protected void executeExecutor() {
        executor = new ScheduledThreadPoolExecutor(2);
        addExecutors(executor);
        futureTask = createFutureTask();
    }

    @Override
    protected void resetTime() {

    }

    @Override
    protected ScheduledFuture<?> createFutureTask() {
        LocalTime now = LocalTime.now();
        Duration delayduration = Duration.between(now, time).isNegative() ? Duration.between(now, time).plusHours(24) :Duration.between(now, time);
        ScheduledFuture scheduledFuture = executor.schedule(new Runnable() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        new AlarmNotify(owner, name, time.toString(), mediasrc, snoozetime);
                    }
                });
                if(futureTask.isDone()) {
                    futureTask = createFutureTask();
                }
            }
        },delayduration.toMillis(), TimeUnit.MILLISECONDS);


        return scheduledFuture;
    }

    @Override
    protected void updateGUI() {

    }

    @Override
    protected void closeEvent() {
        cancelExecutor();
        getExecutor().shutdown();
        removeExecutors(executor);
        getAlarms().remove(getWidget());
    }

}
