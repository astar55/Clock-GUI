package TimeWidget.Container;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.Duration;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;


public abstract class TimeWidget {
    protected GridPane widget;
    protected String name;
    protected Text timetxt;
    protected String mediasrc;
    protected boolean started = true;
    protected boolean paused = false;
    protected ScheduledThreadPoolExecutor executor;
    protected ScheduledFuture<?> futureTask;
    protected Stage owner;
    protected Button startbtn;
    protected Button resumebtn;
    protected Button pausebtn;

    protected void createWidget() {
        widget = CreateFunctions.createColumnConstraintedGridPane(25);
        //widget.setGridLinesVisible(true);

        Text titletxt = new Text(getName());
        widget.add(titletxt, 0, 0, 3,1);

        Image closeimg = new Image(getClass().getResourceAsStream("/ic_clear_black_24dp_1x.png"));
        ImageView closebtn = new ImageView(closeimg);

        BorderPane borderPane = new BorderPane();
        borderPane.setRight(closebtn);
        borderPane.setOnMouseClicked((event -> {
            closeEvent();
        }));
        widget.add(borderPane, 3, 0);

        createWidgetBottom();


    }

    abstract protected void createWidgetBottom();

    abstract protected void executeExecutor();

    protected void createTimeButtons() {
        startbtn = new Button("Start");
        resumebtn = new Button("Resume");
        pausebtn = new Button("Pause");
        startbtn.addEventHandler(ActionEvent.ACTION, (event -> {
            this.started = true;
            this.paused = false;
            widget.getChildren().remove(startbtn);
            widget.add(pausebtn, 0, 2);
            futureTask = createFutureTask();
        }));
        resumebtn.addEventHandler(ActionEvent.ACTION, (event -> {
            this.paused = false;
            widget.getChildren().remove(resumebtn);
            widget.add(pausebtn, 0, 2);
            futureTask = createFutureTask();
        }));
        pausebtn.addEventHandler(ActionEvent.ACTION, (event -> {
            this.paused = true;
            widget.getChildren().remove(pausebtn);
            widget.add(resumebtn, 0, 2);
            cancelExecutor();
        }));
        widget.add(pausebtn, 0, 2);

        BorderPane borderPane = new BorderPane();
        Button resetbtn = new Button("Reset");
        resetbtn.addEventHandler(ActionEvent.ACTION, event -> {
            if(started) {
                this.started = false;
                if (paused) {
                    widget.getChildren().remove(resumebtn);
                }
                else {
                    widget.getChildren().remove(pausebtn);
                }
                widget.add(startbtn, 0, 2);
                resetTime();
            }
            cancelExecutor();
        });
        borderPane.setRight(resetbtn);
        widget.add(borderPane, 3,2);
    }

    protected abstract void resetTime();

    protected abstract ScheduledFuture<?> createFutureTask();

    protected void cancelExecutor() {
        getFutureTask().cancel(true);
    };

    protected String timeFormat(Duration time){
        String timetxt;
        if (time.toMillis() < 1000 * 60) {
            String secs = time.toMillis()/1000 < 10 ? String.format("0%d", time.toMillis()/1000) : String.valueOf(time.toMillis()/1000);
            timetxt = String.format("%ss", secs);
        }
        else if (time.toMillis() < 1000 * 60 *60 ) {
            String mins = time.toMinutes() < 10 ? String.format("0%d", time.toMinutes()): String.valueOf(time.toMinutes());
            String secs = ((time.toMillis()/1000) % 60) < 10 ? String.format("0%d", (time.toMillis()/1000) % 60) : String.valueOf((time.toMillis()/1000) % 60);
            timetxt = String.format("%sm %ss", mins, secs);
        }
        else {
            String hrs = time.toHours() < 10 ? String.format("0%d", time.toHours()) : String.valueOf(time.toHours());
            String mins = ((time.toMillis()/(60 * 1000)) % 60) < 10 ? String.format("0%d", ((time.toMillis()/(60 * 1000)) % 60)) : String.valueOf(((time.toMillis()/(60 * 1000)) % 60));
            String secs = ((time.toMillis()/1000) % 60) < 10 ? String.format("0%d", ((time.toMillis()/1000) % 60)) : String.valueOf((time.toMillis()/1000) % 60);
            timetxt = String.format("%sh %sm %ss", hrs, mins, secs);
        }
        return timetxt;
    }

    protected abstract void updateGUI();

    protected abstract void closeEvent();

    public GridPane getWidget() {
        return this.widget;
    }

    public String getName() {
        return this.name;
    }

    public ScheduledThreadPoolExecutor getExecutor() { return this.executor; }

    public Text getTimetxt() { return this.timetxt; }

    public void setTimetxt(String text) {
        getTimetxt().textProperty().setValue(text);
    }

    public ScheduledFuture<?> getFutureTask() { return this.futureTask; }
}

