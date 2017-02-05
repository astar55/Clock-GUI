package TimeWidget.Stopwatch;

import TimeWidget.Container.TimeCreate;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class StopwatchCreate extends TimeCreate{
    private static String type = "Stopwatch";
    StopwatchView stopwatchView;

    public StopwatchCreate(Stage owner, StopwatchView stopwatchView) {
        super(owner);
        this.stopwatchView = stopwatchView;
    }

    @Override
    public void createCenter() {
        TextField nametxtfield = new TextField("Stopwatch");
        gridPane.add(nametxtfield, 1,2,3,1);

        HBox autostartlblbox = new HBox();
        autostartlblbox.setAlignment(Pos.CENTER);
        Label autostartlbl = new Label("Autostart?");
        autostartlblbox.getChildren().add(autostartlbl);
        gridPane.add(autostartlblbox, 0,3);

        CheckBox autostartchckbx = new CheckBox();
        autostartchckbx.setTextAlignment(TextAlignment.LEFT);
        autostartchckbx.setSelected(true);
        gridPane.add(autostartchckbx, 1,3, 4,1);

        Button createbtn = new Button("Create");
        createbtn.setOnMouseClicked((event -> {
            stopwatchView.createWidget(owner,nametxtfield.getText(),autostartchckbx.isSelected());
            stage.close();
        }));
        gridPane.add(createbtn, 0, 5);

        Button cancelbtn = new Button("Cancel");
        cancelbtn.setOnMouseClicked(event -> {
            stage.close();
        });
        gridPane.add(cancelbtn, 3,5);

        stage.setTitle(createTitle(type));
    }
}
