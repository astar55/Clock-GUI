import TimeWidget.Timer.TimerNotify;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class TimerNotifyTest extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Media Files");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Audio Files",  "*.mp3", "*.wav", "*.m4a"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        File file = fileChooser.showOpenDialog(primaryStage);
        new TimerNotify(new Stage(), "Boo", "05m 00s", file.toURI().toString());

    }

}
