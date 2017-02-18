package TimeWidget.Alarm;

import TimeWidget.Container.TimeWidgetNotify;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static TimeWidget.Index.addExecutors;

public class AlarmNotify extends TimeWidgetNotify{
    private long snoozetime;
    private LocalTime current;
    private ScheduledThreadPoolExecutor executor;
    private ScheduledFuture<?> scheduledFuture;
    private String timeformat;
    private Alarm alarm;

    public AlarmNotify(Alarm alarm, Stage owner, String name, LocalTime time, String timeformat, String mediasrc, long snoozetime) {
        super(owner, name, time.toString(), mediasrc);
        this.alarm = alarm;
        this.snoozetime = snoozetime;
        this.current = time;
        this.timeformat = timeformat;
        executor = new ScheduledThreadPoolExecutor(1);
        addExecutors(executor);
        createNotify(owner);
    }

    @Override
    public ImageView getImageView() {
        Image image = new Image(getClass().getResourceAsStream("/ic_alarm_black_48dp_1x.png"));
        return new ImageView(image);

    }

    @Override
    public void setNotifyText() {
        notifytxt = new Text(formatTime(current));
    }

    @Override
    public void setActionButtons() {
        Button dismissbtn = new Button("Dismiss");
        dismissbtn.minWidthProperty().bind(stage.widthProperty().subtract(10));
        dismissbtn.addEventHandler(ActionEvent.ACTION, (event -> {
            if(hasMedia) {
                mediaPlayer.stop();
            }
            executor.shutdown();
            stage.close();
        }));
        gridPane.add(dismissbtn, 0, 8, 4,1);

        Button snoozebtn = new Button("Snooze");
        snoozebtn.minWidthProperty().bind(stage.widthProperty().subtract(10));
        snoozebtn.addEventHandler(ActionEvent.ACTION, (event -> {
            if(hasMedia) {
                mediaPlayer.stop();
            }
            stage.hide();
            Duration diff = Duration.between(LocalTime.now(), current.plusSeconds(snoozetime/1000));
            scheduledFuture = executor.schedule(new Runnable() {
                @Override
                public void run() {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            snoozefun();
                        }
                    });
                }
            }, diff.toMillis(), TimeUnit.MILLISECONDS);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    alarm.setSnoozeswitch();
                }
            });
        }));
        gridPane.add(snoozebtn, 0, 7, 4,1);

    }

    private void snoozefun() {
        stage.show();
        mediaPlayer.play();
        if(playpausepane.getChildren().contains(playbutton)){
            playpausepane.setCenter(pausebutton);
        }
        setCurrent(current.plusSeconds(snoozetime/1000));
        notifytxt.setText(formatTime(current));
    }

    private String formatTime(LocalTime time){
        String timetxt;
        if (timeformat.equals("24")){
            timetxt = time.toString();
        }else {
            timetxt = time.format(DateTimeFormatter.ofPattern("hh:mm a"));
        }
        return timetxt;
    }

    public LocalTime getCurrent() {
        return this.current;
    }

    public void setCurrent(LocalTime newtime) {
        this.current = newtime;
    }

}
