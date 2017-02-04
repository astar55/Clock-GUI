package Timer;

import TimeWidget.Timer.TimerCreate;
import TimeWidget.Timer.TimerView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import static TimeWidget.Container.CreateFunctions.createColumnConstraintedGridPane;

public class TimerTest extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane gridPane = createColumnConstraintedGridPane(25);

        Button createbtn = new Button("Create");


        TimerView testview = new TimerView();

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
