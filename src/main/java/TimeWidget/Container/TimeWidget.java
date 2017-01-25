package TimeWidget.Container;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.time.Duration;


public abstract class TimeWidget {
    protected GridPane widget;
    protected String name;
    protected boolean started = true;
    protected boolean paused = false;

    protected void createWidget() {
        widget = CreateFunctions.createColumnConstraintedGridPane(25);
        //widget.setGridLinesVisible(true);

        Text titletxt = new Text(getName());
        widget.add(titletxt, 0, 0, 3,1);

        Image closeimg = new Image(getClass().getResourceAsStream("/ic_clear_black_24dp_1x.png"));
        ImageView closebtn = new ImageView(closeimg);
        closebtn.setOnMouseClicked((event -> {
            closeEvent();
        }));
        BorderPane borderPane = new BorderPane();
        borderPane.setRight(closebtn);
        widget.add(borderPane, 3, 0);

        createWidgetBottom();

    }

    abstract protected void createWidgetBottom();

    protected void createTimeButton() {
        Button startbtn = new Button("Start");
        Button resumebtn = new Button("Resume");
        Button pausebtn = new Button("Pause");
        startbtn.addEventHandler(ActionEvent.ACTION, (event -> {
            this.started = true;
            this.paused = false;
            widget.getChildren().remove(startbtn);
            widget.add(pausebtn, 0, 2);
        }));
        resumebtn.addEventHandler(ActionEvent.ACTION, (event -> {
            this.paused = false;
            widget.getChildren().remove(resumebtn);
            widget.add(pausebtn, 0, 2);
        }));
        pausebtn.addEventHandler(ActionEvent.ACTION, (event -> {
            this.paused = true;
            widget.getChildren().remove(pausebtn);
            widget.add(resumebtn, 0, 2);
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
            }
        });
        borderPane.setRight(resetbtn);
        widget.add(borderPane, 3,2);
    }

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


    protected abstract void closeEvent();

    public GridPane getWidget() {
        return this.widget;
    }

    public String getName() {
        return this.name;
    }

}

