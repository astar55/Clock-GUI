package TimeWidget.Container;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;


public abstract class TimeWidget {
    private HBox widget;
    private int id;
    private String name;

    public TimeWidget (int id, String name) {
        this.id = id;
        this.name = name;
        createWidget();
    }

    protected void createWidget() {
        widget = new HBox();
        GridPane gridPane = CreateFunctions.createColumnConstraintedGridPane(25);

        Text titletxt = new Text(getName());
        gridPane.add(titletxt, 0, 0, 3,1);

        Image closeimg = new Image(getClass().getResourceAsStream("/ic_clear_black_24dp_1x.png"));
        ImageView closebtn = new ImageView(closeimg);
        closebtn.setOnMouseClicked((event -> {
            closeEvent();
        }));
        gridPane.add(closebtn, 4, 0);

        HBox timetxtcontainer = new HBox();
        timetxtcontainer.setAlignment(Pos.CENTER);

        /**
         *  Time Text
         */


        widget.getChildren().add(gridPane);
    }

    protected void closeEvent() {}

    public HBox getWidget() {
        return this.widget;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

}

