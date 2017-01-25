package TimeWidget.Timer;

import TimeWidget.Container.TimeWidget;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.time.Duration;

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

        Text timetxt = new Text(timeFormat(getRemaingTime()));
        hBox.getChildren().add(timetxt);
        widget.add(hBox, 0,1,4,1);

        createTimeButton();


    }

    @Override
    protected void closeEvent() {
        getTimers().remove(getWidget());
    }

    public Duration getTotalTime() { return this.totaltime; }

    public Duration getRemaingTime() { return this.remaing; }
}
