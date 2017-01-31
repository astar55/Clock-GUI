package TimeWidget.Container;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class CreateFunctions {

    public static Tab createTab(String name) {
        Tab tab = new Tab(name);
        tab.setClosable(false);
        return tab;
    }

    public static ScrollPane createScrollPane(Node name) {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(name);
        return scrollPane;
    }

    public static GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(5));
        return  gridPane;
    }

    public static GridPane createColumnConstraintedGridPane(double percentWidth) {
        ColumnConstraints c1 = new ColumnConstraints();
        c1.setPercentWidth(percentWidth);

        GridPane gridPane = createGridPane();

        for (int i=0; i < 100/percentWidth; i++ ){
            gridPane.getColumnConstraints().add(c1);
        }

        return gridPane;
    }

    public static HBox createAlignedLabel(String text, Pos position) {
        HBox hBox = new HBox();
        Label label = new Label(text);
        hBox.setAlignment(position);
        hBox.getChildren().add(label);
        return hBox;
    }
}
