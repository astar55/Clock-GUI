import Stopwatch.StopwatchCreateTest;
import Stopwatch.StopwatchTest;
import TimeWidget.Index;
import Timer.TimerCreateTest;
import Timer.TimerNotifyTest;
import Timer.TimerTest;
import javafx.application.Application;
import org.junit.Test;

public class Main {

    @Test
    public void run() throws java.lang.InterruptedException {
        Thread app = new Thread(new Runnable() {
            @Override
            public void run() {
                Application.launch(Index.class);
            }
        });
        app.run();
    }

    @Test
    public void timerTest() {
        Thread app = new Thread(new Runnable() {
            @Override
            public void run() {
                Application.launch(TimerTest.class);
            }
        });
        app.run();
    }

    @Test
    public void timerNotifyTest() {
        Thread app = new Thread(new Runnable() {
            @Override
            public void run() {
                Application.launch(TimerNotifyTest.class);
            }
        });
        app.run();
    }

    @Test
    public void timerCreateTest() {
        Thread app = new Thread(new Runnable() {
            @Override
            public void run() {
                Application.launch(TimerCreateTest.class);
            }
        });
        app.run();
    }

    @Test
    public void stopwatchTest() {
        Thread app = new Thread(new Runnable() {
            @Override
            public void run() {
                Application.launch(StopwatchTest.class);
            }
        });
        app.run();
    }

    @Test
    public void stopwatchCreateTest() {
        Thread app = new Thread(new Runnable() {
            @Override
            public void run() {
                Application.launch(StopwatchCreateTest.class);
            }
        });
        app.run();
    }


    @Test
    public void test() {
    }
}