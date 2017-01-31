package Timer;

import TimeWidget.Timer.TimerCreate;
import TimeWidget.Timer.TimerView;
import javafx.application.Application;
import javafx.stage.Stage;

public class TimerCreateTest extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        new TimerCreate(new Stage(), new TimerView());
    }
}
