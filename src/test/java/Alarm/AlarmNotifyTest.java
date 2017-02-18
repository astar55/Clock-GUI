package Alarm;

import TimeWidget.Alarm.AlarmNotify;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.time.LocalTime;

public class AlarmNotifyTest extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        FileChooser fileChooser = new FileChooser();
        File media = fileChooser.showOpenDialog(new Stage());
        //new AlarmNotify(primaryStage,"Morning", LocalTime.of(8,0),"24", media.toURI().toString(), 1000*60);
        new AlarmNotify(primaryStage,"Morning", LocalTime.of(18,0),"12", media.toURI().toString(), 1000*60);

    }
}
