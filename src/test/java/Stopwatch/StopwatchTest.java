package Stopwatch;

import TimeWidget.Stopwatch.StopwatchCreate;
import TimeWidget.Stopwatch.StopwatchView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import static TimeWidget.Container.CreateFunctions.createColumnConstraintedGridPane;

public class StopwatchTest extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gridPane = createColumnConstraintedGridPane(25);

        Button createbtn = new Button("Create");


        StopwatchView testview = new StopwatchView();

        createbtn.setOnMouseClicked(event -> {
            new StopwatchCreate(primaryStage, testview);
        });
        gridPane.add(createbtn, 3,0);

        ListView<GridPane> listView = testview.getListView();
        gridPane.add(listView,0,1,4,2);

        primaryStage.setTitle("Timer Test");
        primaryStage.setScene(new Scene(gridPane, 600, 450));
        primaryStage.show();

    }
}
