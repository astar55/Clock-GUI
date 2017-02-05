package TimeWidget.Alarm;

import TimeWidget.Container.TimeCreate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.time.Duration;
import java.time.LocalTime;

public class AlarmCreate extends TimeCreate{
    private static String type = "Alarm";

    public AlarmCreate(Stage owner) {
        super(owner);
    }

    @Override
    public void createCenter() {

        TextField nametf = new TextField("Alarm");
        gridPane.add(nametf, 1, 2, 3 ,1);

        Label timeformatlbl = new Label("Time Format");
        gridPane.add(timeformatlbl, 0, 3, 2, 1);

        ToggleGroup timeformattg = new ToggleGroup();
        RadioButton twentyfourfm = new RadioButton("24-Hour");
        twentyfourfm.setToggleGroup(timeformattg);
        twentyfourfm.setSelected(true);
        gridPane.add(twentyfourfm,1,3, 2,1);

        RadioButton twelevefm = new RadioButton("12-Hour");
        twelevefm.setToggleGroup(timeformattg);
        gridPane.add(twelevefm, 2,3,2,1);

        Label timelbl = new Label("Time");
        gridPane.add(timelbl, 0, 4);

        ComboBox<Integer> hrcb = new ComboBox<Integer>(twentyfourlist());
        hrcb.setValue(LocalTime.now().getHour());
        gridPane.add(hrcb, 1,4);

        ComboBox<Integer> mincb = new ComboBox<Integer>(minlist());
        mincb.setValue(LocalTime.now().getMinute());
        gridPane.add(mincb, 2,4);

        ComboBox<String> ampmcb = new ComboBox<String>(ampmlist());
        ampmcb.setValue(LocalTime.now().getHour() <12 ? "AM" : "PM");

        twentyfourfm.addEventHandler(ActionEvent.ACTION, event -> {
            hrcb.setItems(twentyfourlist());
            if(gridPane.getChildren().contains(ampmcb)) {
                gridPane.getChildren().remove(ampmcb);
            }
        });

        twelevefm.addEventHandler(ActionEvent.ACTION, event -> {
            hrcb.setItems(twelevelist());
            gridPane.add(ampmcb, 3,4);
        });

        Tooltip snoozetp = new Tooltip("Set Snooze Length");

        Label snoozelbl = new Label("Snooze");
        snoozelbl.setTooltip(snoozetp);
        gridPane.add(snoozelbl, 0, 5);

        ComboBox<Integer> snoozecb = new ComboBox<Integer>(snoozelen());
        snoozecb.setValue(5);
        gridPane.add(snoozecb, 1,5,3,1);

        Label snoozeunitlbl = new Label("Minutes");
        gridPane.add(snoozeunitlbl, 2,5);

        Label audiolbl = new Label("Audio");
        gridPane.add(audiolbl,0,6);

        Text audiotxt = new Text();
        audiotxt.setWrappingWidth(145);
        gridPane.add(audiotxt, 1,6,2,1);

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
        gridPane.add(audiobtn, 3,6);

        Button createbtn = new Button("Create");
        createbtn.setOnMouseClicked(event -> {
            stage.close();

        });
        gridPane.add(createbtn, 0, 7);


        Button cancelbtn = new Button("Cancel");
        cancelbtn.setOnMouseClicked(event -> {
            stage.close();
        });
        gridPane.add(cancelbtn,3,7);


        stage.setTitle(createTitle(type));

    }

    private ObservableList<Integer> minlist() {
        ObservableList<Integer> min = FXCollections.observableArrayList();
        for (int i = 0; i<60; i++ ){
            min.add(i);
        }
        return min;
    }

    private ObservableList<Integer> twentyfourlist() {
        ObservableList<Integer> hr = FXCollections.observableArrayList();
        for (int i = 0; i<24; i++) {
            hr.add(i);
        }
        return hr;
    }

    private ObservableList<Integer> twelevelist() {
        ObservableList<Integer> hr = FXCollections.observableArrayList();
        for (int i = 1; i<=12; i++) {
            hr.add(i);
        }
        return hr;
    }

    private ObservableList<String> ampmlist() {
        ObservableList<String> timepd = FXCollections.observableArrayList();
        timepd.addAll("AM", "PM");
        return timepd;
    }

    private ObservableList<Integer> snoozelen() {
        ObservableList<Integer> snoozelen = FXCollections.observableArrayList();
        for (int i=1; i<=60; i++) {
            snoozelen.add(i);
        }
        return snoozelen;
    }
}
