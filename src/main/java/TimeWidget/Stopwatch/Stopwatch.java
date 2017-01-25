package TimeWidget.Stopwatch;

import TimeWidget.Container.TimeWidget;

public class Stopwatch extends TimeWidget{

    public Stopwatch(String name) {
        this.name = name;
        createWidget();
    }

    @Override
    protected void createWidgetBottom() {

    }

    @Override
    protected void closeEvent() {

    }
}
