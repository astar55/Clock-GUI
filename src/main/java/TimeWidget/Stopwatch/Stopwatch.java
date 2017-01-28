package TimeWidget.Stopwatch;

import TimeWidget.Container.TimeWidget;

import java.util.concurrent.ScheduledFuture;

public class Stopwatch extends TimeWidget{

    public Stopwatch(String name) {
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
