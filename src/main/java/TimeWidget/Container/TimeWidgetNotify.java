package TimeWidget.Container;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import static TimeWidget.Container.CreateFunctions.createColumnConstraintedGridPane;

public abstract class TimeWidgetNotify {
    protected String name;
    protected String time;
    protected Stage stage;
    protected Media media;
    protected MediaPlayer mediaPlayer;
    protected BorderPane volumebtnpane;
    protected ImageView volup;
    protected ImageView voldown;
    protected ImageView volmute;
    final Timeline timeline;
    protected boolean hasMedia = true;
    protected Text notifytxt;
    protected boolean paused = false;
    protected Duration currentDuration;
    protected Duration mediaDuration;
    protected Text playtime;
    protected Slider volumeslider;
    protected double currentvol;
    protected boolean muted = false;
    protected Slider seekbar;
    protected GridPane gridPane;
    protected BorderPane playpausepane;
    protected ImageView playbutton,pausebutton;

    public TimeWidgetNotify(Stage owner, String name, String time, String mediasrc){
        this.name = name;
        this.time = time;
        try {
            this.media = new Media(mediasrc);
        }
        catch(NullPointerException | MediaException | IllegalArgumentException e){
            hasMedia = false;
        }
        timeline = new Timeline();
        stage = new Stage();
    }

    public void createNotify(Stage owner) {
        gridPane = createColumnConstraintedGridPane(25);
        gridPane.getStylesheets().add(getClass().getResource("/TimeWidget/style.css").toExternalForm());
        //gridPane.setGridLinesVisible(true);

        Text title = new Text(name);
        gridPane.add(title, 0, 0, 4, 1);

        ImageView imageView = getImageView();

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        final KeyValue leftrotate = new KeyValue(imageView.rotateProperty(), -45);
        final KeyValue rightrotate = new KeyValue(imageView.rotateProperty(), 45);
        final KeyFrame leftkeyframe = new KeyFrame(Duration.millis(0), leftrotate);
        final KeyFrame rightkeyframe = new KeyFrame(Duration.millis(1000), rightrotate);
        timeline.getKeyFrames().addAll(leftkeyframe, rightkeyframe);
        timeline.play();

        BorderPane borderPane = new BorderPane(imageView);
        gridPane.add(borderPane, 0, 1, 4, 2 );

        setNotifyText();
        notifytxt.getStyleClass().add("notify");

        BorderPane notifyPane = new BorderPane();
        notifyPane.setCenter(notifytxt);
        gridPane.add(notifyPane,0,3,4,1);


        if(hasMedia) {
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.setVolume(0.5);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

            MediaView mediaView = new MediaView(mediaPlayer);
            BorderPane mediaPane = new BorderPane();
            mediaPane.setCenter(mediaView);
            gridPane.add(mediaPane, 0, 4, 4, 1);

            GridPane mediaControls = createColumnConstraintedGridPane(12.5);
            mediaControls.setPadding(new Insets(0));
            mediaControls.minWidthProperty().bind(gridPane.widthProperty().subtract(10));
            //mediaControls.setGridLinesVisible(true);

            seekbar = new Slider();
            seekbar.minWidthProperty().bind(stage.widthProperty().subtract(10));
            mediaControls.add(seekbar, 0, 0, 8, 1);

            seekbar.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    if (seekbar.isValueChanging()){
                        mediaPlayer.seek(new Duration(mediaDuration.toMillis() * (seekbar.getValue() /100)));
                    }
                }
            });

            seekbar.addEventHandler(MouseEvent.MOUSE_PRESSED, (event -> {
                mediaPlayer.seek(new Duration(mediaDuration.toMillis() * (seekbar.getValue() /100)));
            }));

            seekbar.addEventHandler(KeyEvent.ANY, (event -> {
                if(event.getCode() == KeyCode.LEFT){
                    mediaPlayer.seek(currentDuration.subtract(new Duration(5000)));
                }
                else if (event.getCode() == KeyCode.RIGHT) {
                    mediaPlayer.seek(new Duration((int)currentDuration.toMillis()+ 5000));
                }
            }));

            playpausepane = new BorderPane();

            ImageView playbutton = new ImageView(new Image(getClass().getResourceAsStream("/ic_play_arrow_black_18dp_1x.png")));
            ImageView pausebutton = new ImageView(new Image(getClass().getResourceAsStream("/ic_pause_black_18dp_1x.png")));

            playpausepane.setOnMouseClicked((event -> {
                if(paused) {
                    mediaPlayer.play();
                    playpausepane.setCenter(pausebutton);
                }
                else {
                    mediaPlayer.pause();
                    playpausepane.setCenter(playbutton);

                }
                paused = !paused;
            }));

            playpausepane.setCenter(playbutton);
            mediaControls.add(playpausepane, 0, 1);

            playtime = new Text();
            mediaControls.add(playtime, 1, 1, 2, 1);

            volumebtnpane = new BorderPane();

            volup = new ImageView(new Image(getClass().getResourceAsStream("/ic_volume_up_black_18dp_1x.png")));
            voldown = new ImageView(new Image(getClass().getResourceAsStream("/ic_volume_down_black_18dp_1x.png")));
            volmute = new ImageView(new Image(getClass().getResourceAsStream("/ic_volume_off_black_18dp_1x.png")));

            volumebtnpane.setOnMouseClicked((event -> {
                if(muted) {
                    mediaPlayer.setMute(false);
                    muted = !muted;
                    if (currentvol > 0) {
                        volumeslider.setValue(currentvol);
                    } else {
                        volumeslider.setValue(5);
                    }

                }
                else if (volumeslider.getValue() ==  0 ){
                    volumeslider.setValue(100);

                }
                else {
                    currentvol = volumeslider.getValue();
                    mediaPlayer.setMute(true);
                    muted = !muted;
                    volumeslider.setValue(0);
                }
            }));

            volumebtnpane.setCenter(volup);
            mediaControls.add(volumebtnpane, 4, 1);

            volumeslider = new Slider();
            volumeslider.setValue(mediaPlayer.getVolume() * 100);
            volumeslider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(volumeslider.getValue()/100);
                }
            });

            volumeslider.addEventHandler(MouseEvent.MOUSE_CLICKED, (event -> {
                mediaPlayer.setVolume(volumeslider.getValue()/100);


            }));

            volumeslider.addEventHandler(KeyEvent.KEY_RELEASED, (event -> {
                if(event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.RIGHT){
                    mediaPlayer.setVolume(volumeslider.getValue()/100);

                }
            }));


            mediaControls.add(volumeslider, 5, 1, 4, 1);

            gridPane.add(mediaControls, 0, 5, 4, 2);

            mediaPlayer.currentTimeProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    updateTime();
                    updateSeek();
                };
            });

            mediaPlayer.volumeProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    updateVolume();
                }
            });

            mediaPlayer.setOnReady(new Runnable() {
                @Override
                public void run() {
                    playpausepane.setCenter(pausebutton);
                    mediaDuration = mediaPlayer.getTotalDuration();
                }
            });

            mediaPlayer.setOnEndOfMedia(new Runnable() {
                @Override
                public void run() {
                    mediaPlayer.stop();
                    playpausepane.setCenter(playbutton);
                }
            });


        }


        setActionButtons();

        stage.setScene(new Scene(gridPane, 325, 300));
        stage.initOwner(owner);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        if (hasMedia && (media.getSource().substring(media.getSource().lastIndexOf(".")+1).equals("mp4"))) {
            stage.setMaximized(true);
        }
        stage.showAndWait();
    }

    public void setActionButtons() {
        Button dismissbtn = new Button("Dismiss");
        dismissbtn.minWidthProperty().bind(stage.widthProperty().subtract(10));
        dismissbtn.addEventHandler(ActionEvent.ACTION, (event -> {
            if(hasMedia) {
                mediaPlayer.stop();
            }
            stage.close();
        }));
        gridPane.add(dismissbtn, 0,7,4,1);

    }

    public void updateTime() {
        currentDuration = mediaPlayer.getCurrentTime();
        mediaDuration = mediaPlayer.getMedia().getDuration();
        playtime.setText(String.format("%s/%s", timeFormat(currentDuration), timeFormat(mediaDuration)));
    }

    public void updateSeek() {
        seekbar.setValue((currentDuration.toMillis()/mediaDuration.toMillis()) * 100 );
    }

    public void updateVolume() {
        if (volumeslider.getValue() > 50 ) {
            volumebtnpane.setCenter(volup);
        }
        else if(volumeslider.getValue() > 0) {
            volumebtnpane.setCenter(voldown);
        }
        else {
            volumebtnpane.setCenter(volmute);
        }
        if (muted && mediaPlayer.getVolume() > 0) {
            mediaPlayer.setMute(false);
            muted = !muted;
        }
    }

    protected String timeFormat(Duration time){
        String timetxt;
        if (time.toMillis() < 1000 * 60) {
            String secs = time.toMillis()/1000 < 10 ? String.format("0%d", (int)time.toMillis()/1000) : String.valueOf((int)time.toMillis()/1000);
            timetxt = String.format("00:%s", secs);
        }
        else if (time.toMillis() < 1000 * 60 *60 ) {
            String mins = time.toMinutes() < 10 ? String.format("0%d", (int)time.toMinutes()): String.valueOf((int)time.toMinutes());
            String secs = ((time.toMillis()/1000) % 60) < 10 ? String.format("0%d",(int)(time.toMillis()/1000) % 60) : String.valueOf((int)(time.toMillis()/1000) % 60);
            timetxt = String.format("%s:%s", mins, secs);
        }
        else {
            String hrs = time.toHours() < 10 ? String.format("0%d", (int)time.toHours()) : String.valueOf((int)time.toHours());
            String mins = ((time.toMillis()/(60 * 1000)) % 60) < 10 ? String.format("0%d", (int)((time.toMillis()/(60 * 1000)) % 60)) : String.valueOf((int)((time.toMillis()/(60 * 1000)) % 60));
            String secs = ((time.toMillis()/1000) % 60) < 10 ? String.format("0%d", (int)((time.toMillis()/1000) % 60)) : String.valueOf((int)(time.toMillis()/1000) % 60);
            timetxt = String.format("%s:%s:%s", hrs, mins, secs);
        }

        return timetxt;
    }


    public abstract ImageView getImageView();

    public abstract void setNotifyText();

    public String getName() { return this.name; }
    public String getTime() { return this.time; }
    public Stage getStage() { return this.stage; }
}
