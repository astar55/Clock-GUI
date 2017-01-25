import TimeWidget.Alarm.AlarmView;
import TimeWidget.Index;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import org.junit.Test;

public class Main {

    @Test
    public void run() throws java.lang.InterruptedException{
        Thread app = new Thread(new Runnable() {
            @Override
            public void run() {
                Application.launch(Index.class);
            }
        });
        app.run();
    }
}
