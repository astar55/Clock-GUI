package TimeWidget;

import TimeWidget.Alarm.AlarmView;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import static TimeWidget.Container.CreateFunctions.*;

@SpringBootApplication
public class Index extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        GridPane gridPane = createColumnConstraintedGridPane(25);
        gridPane.setGridLinesVisible(true);


        HBox titlebx = new HBox();
        titlebx.setAlignment(Pos.CENTER);

        Text title = new Text("Time Widget");
        title.getStyleClass().add("title");
        titlebx.getChildren().add(title);
        gridPane.add(titlebx, 0, 0, 4, 1);


        ContextMenu createmenu = new ContextMenu();
        MenuItem alarmitm = new MenuItem("TimeWidget/Alarm");
        alarmitm.addEventHandler(ActionEvent.ACTION, (event -> {
            System.out.println("TimeWidget.Alarm Pressed");
        }));
        MenuItem timeritm = new MenuItem("TimeWidget/Timer");
        timeritm.addEventHandler(ActionEvent.ACTION, (event -> {
            System.out.println("TimeWidget.Timer Pressed");
        }));
        MenuItem stpwthitm = new MenuItem("StopWatch");
        stpwthitm.addEventHandler(ActionEvent.ACTION, (event -> {
            System.out.println("TimeWidget.Stopwatch Pressed");
        }));
        createmenu.getItems().addAll(alarmitm, timeritm,stpwthitm);

        Button createbtn = new Button("Create");
        createbtn.setContextMenu(createmenu);
        createbtn.addEventHandler(ActionEvent.ACTION, (event -> {
            Bounds btnbound = createbtn.getBoundsInLocal();
            Bounds btnscreenbound = createbtn.localToScreen(btnbound);
            createmenu.show(createbtn, btnscreenbound.getMinX(), btnscreenbound.getMinY());
        }));
        gridPane.add(createbtn, 3, 1);

        TabPane tabPane = new TabPane();
        tabPane.tabMinWidthProperty().bind(primaryStage.widthProperty().subtract(82).divide(3));


        Tab alarmTab = createTab("TimeWidget/Alarm");
        AlarmView alarmView = new AlarmView();
        alarmTab.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/ic_alarm_white_24dp_1x.png"))));
        alarmTab.setContent(alarmView.create());

        Tab timerTab = createTab("TimeWidget/Timer");
        timerTab.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/ic_timer_white_24dp_1x.png"))));

        Tab stopwatchTab = createTab("TimeWidget/Stopwatch");
        stopwatchTab.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/ic_watch_white_24dp_1x.png"))));



        tabPane.getTabs().addAll(alarmTab, timerTab, stopwatchTab);
        gridPane.add(tabPane, 0, 3, 4,1);

        Button open = new Button("Open");
        open.addEventHandler(ActionEvent.ACTION, (event -> {
            Stage temp = new Stage();
            HBox a = new HBox();
            a.setAlignment(Pos.CENTER);

            Text boo = new Text("Boo");
            a.getChildren().add(boo);

            temp.setTitle("hal");
            temp.initOwner(primaryStage);
            temp.initModality(Modality.APPLICATION_MODAL);
            temp.setScene(new Scene(a, 500, 500));
            temp.showAndWait();

        }));
        gridPane.add(open, 1, 2);

        Button v = new Button("Open");
        v.addEventHandler(ActionEvent.ACTION, (event -> {
            Stage temp = new Stage();
            HBox a = new HBox();
            a.setAlignment(Pos.CENTER);

            Text boo = new Text("Boo");
            a.getChildren().add(boo);

            temp.setTitle("hal");
            temp.initOwner(primaryStage);
            temp.setScene(new Scene(a, 500, 500));
            temp.showAndWait();

        }));
        gridPane.add(v, 2, 2);


        ScrollPane scrollPane = createScrollPane(gridPane);

        Scene indexScene = new Scene(scrollPane, 600, 550);
        indexScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        primaryStage.setTitle("TimeWidget");
        primaryStage.setScene(indexScene);
        //primaryStage.setMaximized(true);
        primaryStage.show();

    }

}
