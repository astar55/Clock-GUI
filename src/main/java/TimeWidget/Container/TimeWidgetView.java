package TimeWidget.Container;

import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.util.List;

public abstract class TimeWidgetView {

    abstract public ListView create();

    abstract public void createWidget(String name);

}
