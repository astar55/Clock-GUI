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
        createWidget("Hoo", Duration.ofSeconds(61));
        createWidget("Hoo", Duration.ofSeconds(71));
        createWidget("Boo", Duration.ofSeconds(59));
        createWidget("", Duration.ofSeconds(9));
        createWidget("a", Duration.ofMinutes(65));
        createWidget("a", Duration.ofMinutes(85));
        createWidget("a", Duration.ofMinutes(65));
        return timerListView;
    }

    public void createWidget(String name, Duration time) {
        Timer timer = new Timer(name, time);
        timers.add(timer.getWidget());
    }

    public static List<GridPane> getTimers() { return timers;}
}
