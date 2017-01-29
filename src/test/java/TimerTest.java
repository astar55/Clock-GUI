import TimeWidget.Timer.TimerView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.Duration;

public class TimerTest extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {

        TimerView testview = new TimerView();
        testview.create();
        //createWidget("Hoo", Duration.ofSeconds(61));
        //createWidget("Hoo", Duration.ofSeconds(71));
        //createWidget("Boo", Duration.ofSeconds(59));
        //createWidget("", Duration.ofSeconds(9));
        //createWidget("a", Duration.ofMinutes(65));
        //createWidget("a", Duration.ofMinutes(85));
        //createWidget("a", Duration.ofMinutes(65));
        //createWidget("", Duration.ofDays(1));
        testview.createWidget("", Duration.ofSeconds(2));



        primaryStage.setTitle("Timer Test");
        primaryStage.setScene(new Scene(testview.getListView(), 600, 575));
        primaryStage.show();
    }
}
