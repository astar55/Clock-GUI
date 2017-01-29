package TimeWidget.Timer;

import TimeWidget.Container.TimeWidgetView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

import java.time.Duration;
import java.util.List;

public class TimerView extends TimeWidgetView {
    private static ObservableList<GridPane> timers = FXCollections.observableArrayList();

    public TimerView() {
        create();
        createWidget("", Duration.ofSeconds(1));
    }

    @Override
    public void  create() {
        listView = new ListView<>(timers);
    }

    public void createWidget(String name, Duration time) {
        Timer timer = new Timer(this, name, time);
        timers.add(timer.getWidget());
    }

    public List<GridPane> getTimers() { return timers;}

}
