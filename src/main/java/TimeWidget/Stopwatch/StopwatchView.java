package TimeWidget.Stopwatch;

import TimeWidget.Container.TimeWidgetView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

public class StopwatchView extends TimeWidgetView {
    private static ObservableList<GridPane> stopwatches = FXCollections.observableArrayList();

    public StopwatchView() { create(); }

    @Override
    public void create() {
        listView = new ListView<>(stopwatches);
    }

    public void createWidget(Stage owner, String name, boolean autostart) {
        Stopwatch stopwatch = new Stopwatch(this, owner, name, autostart);
        stopwatches.add(stopwatch.getWidget());
    }

    public List<GridPane> getStopwatches() { return stopwatches; };
}
