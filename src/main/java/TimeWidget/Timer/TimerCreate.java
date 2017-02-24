package TimeWidget.Timer;

import TimeWidget.Container.TimeCreate;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.time.Duration;

import static TimeWidget.Container.CreateFunctions.createAlignedLabel;

public class TimerCreate extends TimeCreate{
    final private static String type = "Timer";
    protected ComboBox<Integer> hourcb;
    protected ComboBox<Integer> mincb;
    protected ComboBox<Integer> seccb;
    protected Button createbtn;
    protected TimerView timerView;
    protected Text audiotxt;
    protected TextField nametf;


    public TimerCreate(Stage owner, TimerView timerView) {
        super(owner);
        this.timerView = timerView;
    }

    @Override
    public void createCenter() {


        nametf = new TextField(type);
        gridPane.add(nametf, 1,2,3,1);

        HBox timelbl = createAlignedLabel("Time", Pos.CENTER);
        gridPane.add(timelbl, 0,3);

        Tooltip hourtp = new Tooltip("Hour");
        hourcb = createComboBox(hourlist(),0);
        hourcb.setTooltip(hourtp);
        hourcb.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                disableCreate();
            }
        });
        gridPane.add(hourcb, 1,3);

        Tooltip mintp = new Tooltip("Minute");
        mincb = createComboBox(minseclist(), 1);
        mincb.setTooltip(mintp);
        mincb.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                disableCreate();
            }
        });
        gridPane.add(mincb,2,3);

        Tooltip sectp = new Tooltip("Second");
        seccb = createComboBox(minseclist(), 0);
        seccb.setTooltip(sectp);
        seccb.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                disableCreate();
            }
        });
        gridPane.add(seccb, 3,3);

        HBox audiolbl = createAlignedLabel("Audio", Pos.CENTER);
        gridPane.add(audiolbl,0,4);

        ScrollPane scrollPane = new ScrollPane();
        audiotxt = new Text();
        audiotxt.setWrappingWidth(140);
        scrollPane.setContent(audiotxt);
        gridPane.add(scrollPane, 1,4,2,1);

        Button audiobtn = new Button("Browse");
        audiobtn.setOnMouseClicked((event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Audio Files",  "*.mp3", "*.wav", "*.m4a"),
                    new FileChooser.ExtensionFilter("All Files", "*.*")
            );
            try {
                File file = fileChooser.showOpenDialog(new Stage());
                if (file.isFile()) {
                    audiotxt.setText(file.toURI().toString());
                }
            }
            catch (NullPointerException e) {
                audiotxt.setText("");
            }
        }));
        gridPane.add(audiobtn, 3,4);

        createActionButtons();
    }

    @Override
    public void createActionButtons() {
        createbtn = new Button("Create");
        createbtn.setOnMouseClicked(event -> {
            stage.close();
            Duration duration = Duration.ofHours(hourcb.getValue()).plusMinutes(mincb.getValue()).plusSeconds(seccb.getValue());
            timerView.createWidget(owner, nametf.getText(), duration, audiotxt.getText());
        });
        gridPane.add(createbtn, 0, 6);


        Button cancelbtn = new Button("Cancel");
        cancelbtn.setOnMouseClicked(event -> {
            stage.close();
        });
        gridPane.add(cancelbtn,3,6);

    }

    @Override
    public void setStageTitle() {
        stage.setTitle(createTitle(type));
    }

    private ObservableList hourlist() {
        ObservableList list = FXCollections.observableArrayList();
        for(int i=0; i < 100; i++) {
            list.add(i);
        }
        return list;
    }

    private ObservableList minseclist() {
        ObservableList list = FXCollections.observableArrayList();
        for(int i=0; i<60; i++) {
            list.add(i);
        }
        return list;
    }

    private ComboBox<Integer> createComboBox(ObservableList list, int value) {
        ComboBox<Integer> comboBox = new ComboBox<Integer>(list);
        comboBox.setVisibleRowCount(10);
        comboBox.setValue(value);
        return comboBox;
    }

    private void disableCreate(){
        if(hourcb.getValue() == 0 && mincb.getValue() == 0 && seccb.getValue() == 0) {
            createbtn.setDisable(true);
        }
        else {
            createbtn.setDisable(false);
        }
    }

}
