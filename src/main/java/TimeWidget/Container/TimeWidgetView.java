package TimeWidget.Container;

import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

public abstract class TimeWidgetView {
    protected ListView<GridPane> listView;

    abstract public void create();

    public ListView<GridPane> getListView() { return this.listView; }

}
