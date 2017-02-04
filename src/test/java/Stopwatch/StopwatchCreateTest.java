package Stopwatch;

import TimeWidget.Stopwatch.StopwatchCreate;
import TimeWidget.Stopwatch.StopwatchView;
import javafx.application.Application;
import javafx.stage.Stage;

public class StopwatchCreateTest extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        new StopwatchCreate(new Stage(), new StopwatchView());

    }
}
