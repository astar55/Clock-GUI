package TimeWidget.Alarm;

import TimeWidget.Container.TimeWidgetView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.util.List;

public class AlarmView extends TimeWidgetView{
    private static ObservableList<GridPane> alarms = FXCollections.observableArrayList();

    public AlarmView() {
        create();
    }

    public void create() {
        listView = new ListView<>(alarms);
        /* clear selection
        alarmListView.setOnMouseClicked((event -> {
            alarmListView.getSelectionModel().clearSelection();
        }));
        */
    }

    public void createWidget(Stage owner, String name, LocalTime time, String format, int snoozetime, String media) {
        Alarm alarm = new Alarm(this, owner, name, time, format,snoozetime, media);
        alarms.add(alarm.getWidget());
    }

    public static List<GridPane> getAlarms() {
        return alarms;
    }

}
