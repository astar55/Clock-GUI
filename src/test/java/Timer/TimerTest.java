package Timer;

import TimeWidget.Timer.TimerCreate;
import TimeWidget.Timer.TimerView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.Duration;

import static TimeWidget.Container.CreateFunctions.createColumnConstraintedGridPane;

public class TimerTest extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane gridPane = createColumnConstraintedGridPane(25);

        Button createbtn = new Button("Create");


        TimerView testview = new TimerView();

        //createWidget("Hoo", Duration.ofSeconds(61));
        //createWidget("Hoo", Duration.ofSeconds(71));
        //createWidget("Boo", Duration.ofSeconds(59));
        //createWidget("", Duration.ofSeconds(9));
        //createWidget("a", Duration.ofMinutes(65));
        //createWidget("a", Duration.ofMinutes(85));
        //createWidget("a", Duration.ofMinutes(65));
        //createWidget("", Duration.ofDays(1));
        //testview.createWidget("", Duration.ofSeconds(2));

        createbtn.setOnMouseClicked(event -> {
            new TimerCreate(primaryStage, testview);
        });
        gridPane.add(createbtn, 3,0);

        ListView<GridPane> listView = testview.getListView();
        gridPane.add(listView,0,1,4,2);

        primaryStage.setTitle("Timer Test");
        primaryStage.setScene(new Scene(gridPane, 600, 450));
        primaryStage.show();
    }
}
