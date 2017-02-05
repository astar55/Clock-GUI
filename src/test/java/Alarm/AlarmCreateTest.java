package Alarm;

import TimeWidget.Alarm.AlarmCreate;
import javafx.application.Application;
import javafx.stage.Stage;

public class AlarmCreateTest extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        new AlarmCreate(new Stage());
    }
}
