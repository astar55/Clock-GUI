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
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AlarmNotify extends TimeWidgetNotify{
    private long snoozetime;
    private LocalTime current;
    private ScheduledThreadPoolExecutor executor;
    private ScheduledFuture<?> scheduledFuture;
    private String timetxt;

    public AlarmNotify(Stage owner, String name, LocalTime time, String timetxt, String mediasrc, long snoozetime) {
        super(owner, name, time.toString(), mediasrc);
        this.snoozetime = snoozetime;
        this.current = time;
        this.timetxt = timetxt;
        executor = new ScheduledThreadPoolExecutor(1);
        createNotify(owner);
    }

    @Override
    public ImageView getImageView() {
        Image image = new Image(getClass().getResourceAsStream("/ic_alarm_black_48dp_1x.png"));
        return new ImageView(image);

    }

    @Override
    public void setNotifyText() {
        notifytxt = new Text(timetxt);
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
        }));
        gridPane.add(snoozebtn, 0, 7, 4,1);

    }

    private void snoozefun() {
        stage.show();
        mediaPlayer.play();
        setCurrent(current.plusSeconds(snoozetime/1000));
        notifytxt.setText(getCurrent().toString());
    }

    public LocalTime getCurrent() {
        return this.current;
    }

    public void setCurrent(LocalTime newtime) {
        this.current = newtime;
    }

}
