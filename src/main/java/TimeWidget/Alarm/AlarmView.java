package TimeWidget.Alarm;

import TimeWidget.Container.TimeWidgetView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

import java.util.List;

public class AlarmView extends TimeWidgetView{
    private static ObservableList<GridPane> alarms;

    public ListView create() {
        alarms = FXCollections.observableArrayList();
        ListView<GridPane> alarmListView = new ListView<>(alarms);
        /* clear selection
        alarmListView.setOnMouseClicked((event -> {
            alarmListView.getSelectionModel().clearSelection();
        }));
        */
        createWidget("boo");
        return alarmListView;
    }

    public void createWidget(String name) {
        Alarm alarm = new Alarm(name);
        alarms.add(alarm.getWidget());
    }

    public static List<GridPane> getAlarms() {
        return alarms;
    }
}
