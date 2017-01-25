package TimeWidget.Alarm;

import TimeWidget.Container.TimeWidgetView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.util.List;

public class AlarmView extends TimeWidgetView{
    private static ObservableList<HBox> alarms;
    private static int count = 0;

    public ListView create() {
        alarms = FXCollections.observableArrayList();
        ListView<HBox> alarmListView = new ListView<>(alarms);
        /* clear selection
        alarmListView.setOnMouseClicked((event -> {
            alarmListView.getSelectionModel().clearSelection();
        }));
        */
        createWidget("boo");
        return alarmListView;
    }

    public void createWidget(String name) {
        Alarm alarm = new Alarm(count, name);
        alarms.add(alarm.getWidget());
        count++;
    }

    public static List<HBox> getAlarms() {
        return alarms;
    }
}
