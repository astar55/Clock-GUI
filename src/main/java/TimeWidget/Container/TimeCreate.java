package TimeWidget.Container;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static TimeWidget.Container.CreateFunctions.createAlignedLabel;
import static TimeWidget.Container.CreateFunctions.createColumnConstraintedGridPane;

public abstract class TimeCreate {
    protected GridPane gridPane;
    protected Stage stage;
    protected Label err;
    protected Stage owner;

    public TimeCreate(Stage owner) {
        stage = new Stage();
        this.owner = owner;
        create(owner);
    }

    public void create(Stage owner) {
        gridPane = createColumnConstraintedGridPane(25);
        //gridPane.setGridLinesVisible(true);

        BorderPane errPane = new BorderPane();
        err = new Label();
        errPane.setCenter(err);
        gridPane.add(errPane, 0, 1, 4, 1);

        HBox namelblHbox = createAlignedLabel("Name", Pos.CENTER);
        gridPane.add(namelblHbox, 0,2);

        createCenter();

        setStageTitle();

        stage.setScene(new Scene(gridPane, 300,275));
        stage.initOwner(owner);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public abstract void createCenter();

    public abstract void createActionButtons();

    public abstract void setStageTitle();

    public String createTitle(String type) {
        String title ="Create " + type;
        return title;
    }
}
