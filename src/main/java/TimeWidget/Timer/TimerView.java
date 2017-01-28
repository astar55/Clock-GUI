package TimeWidget.Timer;

import TimeWidget.Container.TimeWidgetView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

import java.time.Duration;
import java.util.List;

public class TimerView extends TimeWidgetView {
    private static ObservableList<GridPane> timers;

    @Override
    public ListView create() {
        timers = FXCollections.observableArrayList();
        ListView<GridPane> timerListView = new ListView<>(timers);
        return timerListView;
    }

    public void createWidget(String name, Duration time) {
        Timer timer = new Timer(name, time);
        timers.add(timer.getWidget());
    }

    public static List<GridPane> getTimers() { return timers;}
}
