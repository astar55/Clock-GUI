package Alarm;

import TimeWidget.Alarm.AlarmCreate;
import TimeWidget.Alarm.AlarmView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import static TimeWidget.Container.CreateFunctions.createColumnConstraintedGridPane;

public class AlarmTest extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gridPane = createColumnConstraintedGridPane(25);

        Button createbtn = new Button("Create");


        AlarmView testview = new AlarmView();

        createbtn.setOnMouseClicked(event -> {
            new AlarmCreate(primaryStage, testview);
        });
        gridPane.add(createbtn, 3,0);

        ListView<GridPane> listView = testview.getListView();
        gridPane.add(listView,0,1,4,2);

        primaryStage.setTitle("Alarm Test");
        primaryStage.setScene(new Scene(gridPane, 600, 450));
        primaryStage.show();

    }
}
