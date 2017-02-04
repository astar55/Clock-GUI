package TimeWidget.Timer;

import TimeWidget.Container.TimeWidgetView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.Duration;
import java.util.List;

public class TimerView extends TimeWidgetView {
    private static ObservableList<GridPane> timers = FXCollections.observableArrayList();

    public TimerView() {
        create();
    }

    @Override
    public void  create() {
        listView = new ListView<>(timers);
    }

    public void createWidget(Stage owner, String name, Duration time, String media) {
        Timer timer = new Timer(this, owner, name, time, media);
        timers.add(timer.getWidget());

    }

    public List<GridPane> getTimers() { return timers;}

}
