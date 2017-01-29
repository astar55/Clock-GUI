package TimeWidget.Timer;

import TimeWidget.Container.TimeWidgetNotify;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TimerNotify extends TimeWidgetNotify{


    public TimerNotify(Stage owner, String name, String time, String mediasrc) {
        super(owner, name, time, mediasrc);
    }

    @Override
    public ImageView getImageView() {
        Image timerimg = new Image(getClass().getResourceAsStream("/ic_timer_black_48dp_1x.png"));
        ImageView imageView = new ImageView(timerimg);
        return imageView;
    }

    @Override
    public void setNotifyText() {
        notifytxt = new Text(String.format("Your %s Timer of %s is Completed!", getName(), getTime()));
    }


}
