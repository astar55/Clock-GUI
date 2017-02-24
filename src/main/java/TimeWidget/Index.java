package TimeWidget;

import TimeWidget.Alarm.AlarmCreate;
import TimeWidget.Alarm.AlarmView;
import TimeWidget.Stopwatch.StopwatchCreate;
import TimeWidget.Stopwatch.StopwatchView;
import TimeWidget.Timer.TimerCreate;
import TimeWidget.Timer.TimerView;
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
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

import static TimeWidget.Container.CreateFunctions.*;

@SpringBootApplication
public class Index extends Application {

    static ArrayList<ExecutorService> executors = new ArrayList<>();
    AlarmView alarmView;
    TimerView timerView;
    StopwatchView stopwatchView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        GridPane gridPane = createColumnConstraintedGridPane(25);
        //gridPane.setGridLinesVisible(true);


        HBox titlebx = new HBox();
        titlebx.setAlignment(Pos.CENTER);

        Text title = new Text("Time Widget");
        title.getStyleClass().add("title");
        titlebx.getChildren().add(title);
        gridPane.add(titlebx, 0, 0, 4, 1);


        ContextMenu createmenu = new ContextMenu();

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
        tabPane.minHeightProperty().bind(primaryStage.heightProperty().subtract(130));


        Tab alarmTab = createTab("Alarm");
        alarmView = new AlarmView();
        alarmTab.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/ic_alarm_white_24dp_1x.png"))));
        alarmTab.setContent(alarmView.getListView());

        Tab timerTab = createTab("Timer");
        timerView = new TimerView();
        timerTab.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/ic_timer_white_24dp_1x.png"))));
        timerTab.setContent(timerView.getListView());

        Tab stopwatchTab = createTab("Stopwatch");
        stopwatchView = new StopwatchView();
        stopwatchTab.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/ic_watch_white_24dp_1x.png"))));
        stopwatchTab.setContent(stopwatchView.getListView());


        tabPane.getTabs().addAll(alarmTab, timerTab, stopwatchTab);
        gridPane.add(tabPane, 0, 3, 4,1);


        MenuItem alarmitm = new MenuItem("Alarm");
        alarmitm.addEventHandler(ActionEvent.ACTION, (event -> {
            if(!alarmTab.isSelected()) {
                tabPane.getSelectionModel().select(alarmTab);
            }
            new AlarmCreate(primaryStage, alarmView);
        }));
        MenuItem timeritm = new MenuItem("Timer");
        timeritm.addEventHandler(ActionEvent.ACTION, (event -> {
            if(!timerTab.isSelected()) {
                tabPane.getSelectionModel().select(timerTab);
            }
            new TimerCreate(primaryStage, timerView);
        }));
        MenuItem stpwthitm = new MenuItem("StopWatch");
        stpwthitm.addEventHandler(ActionEvent.ACTION, (event -> {
            if(!stopwatchTab.isSelected()) {
                tabPane.getSelectionModel().select(stopwatchTab);
            }
            new StopwatchCreate(primaryStage, stopwatchView);
        }));
        createmenu.getItems().addAll(alarmitm, timeritm,stpwthitm);



        ScrollPane scrollPane = createScrollPane(gridPane);

        Scene indexScene = new Scene(scrollPane, 600, 550);
        indexScene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        primaryStage.setTitle("TimeWidget");
        primaryStage.setScene(indexScene);
        //primaryStage.setMaximized(true);
        primaryStage.show();
    }

    @Override
    public void stop() {
        for(ExecutorService executor : executors) {
            executor.shutdown();
            executor.shutdownNow();
        }
    }

    public static ArrayList<ExecutorService> getExecutorsList() {
        return executors;
    }

    public static void addExecutors(ExecutorService e){
        executors.add(e);
    }

    public static void removeExecutors(ExecutorService e) {
        executors.remove(e);
    }


}
