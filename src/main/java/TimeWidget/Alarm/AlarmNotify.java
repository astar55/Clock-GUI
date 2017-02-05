package TimeWidget.Alarm;

import TimeWidget.Container.TimeWidgetNotify;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalTime;

public class AlarmNotify extends TimeWidgetNotify{
    private long snoozetime;
    private LocalTime current;

    public AlarmNotify(Stage owner, String name, String time, String mediasrc, long snoozetime) {
        super(owner, name, time, mediasrc);
        this.snoozetime = snoozetime;
        this.current = LocalTime.parse(time);
        createNotify(owner);
    }

    @Override
    public ImageView getImageView() {
        Image image = new Image(getClass().getResourceAsStream("/ic_alarm_black_48dp_1x.png"));
        return new ImageView(image);

    }

    @Override
    public void setNotifyText() {
        notifytxt = new Text(getTime());
    }

    @Override
    public void setActionButtons() {
        Button dismissbtn = new Button("Dismiss");
        dismissbtn.minWidthProperty().bind(stage.widthProperty().subtract(10));
        dismissbtn.addEventHandler(ActionEvent.ACTION, (event -> {
            if(hasMedia) {
                mediaPlayer.stop();
            }
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
            Thread snoozethread = new Thread(new Runnable() {
                @Override
                public void run() {
                    snoozefun();
                }
            });
            snoozethread.run();
        }));
        gridPane.add(snoozebtn, 0, 7, 4,1);

    }

    private void snoozefun() {
        try {
            Thread.sleep(snoozetime);
            stage.show();
            mediaPlayer.play();
            setCurrent(current.plusSeconds(snoozetime/1000));
            notifytxt.setText(getCurrent().toString());
        }
        catch (InterruptedException e) {

        }
    }

    public LocalTime getCurrent() {
        return this.current;
    }

    public void setCurrent(LocalTime newtime) {
        this.current = newtime;
    }

}
