package Alarm;

import TimeWidget.Alarm.AlarmNotify;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class AlarmNotifyTest extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        FileChooser fileChooser = new FileChooser();
        File media = fileChooser.showOpenDialog(new Stage());
        new AlarmNotify(primaryStage,"Morning", "08:00",media.toURI().toString(), 1000*60);
    }
}
