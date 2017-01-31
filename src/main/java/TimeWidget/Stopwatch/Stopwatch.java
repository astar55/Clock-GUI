package TimeWidget.Stopwatch;

import TimeWidget.Container.TimeWidget;
import javafx.stage.Stage;

import java.time.Duration;
import java.util.concurrent.ScheduledFuture;

public class Stopwatch extends TimeWidget{
    private StopwatchView stopwatchView;
    private Duration totaltime;

    public Stopwatch(StopwatchView stopwatchView, Stage owner, String name) {
        this.stopwatchView = stopwatchView;
        this.owner = owner;
        this.name = name;
        createWidget();
    }

    @Override
    protected void createWidgetBottom() {

    }

    @Override
    protected void executeExecutor() {

    }

    @Override
    protected ScheduledFuture<?> createFutureTask() {
        return null;
    }

    @Override
    protected void cancelExecutor() {

    }

    @Override
    protected void updateGUI() {

    }

    @Override
    protected void closeEvent() {

    }
}
