package TimeWidget.Alarm;

import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.time.LocalTime;

public class AlarmUpdate extends AlarmCreate{
    Alarm alarm;


    public AlarmUpdate(Stage owner, AlarmView alarmView, Alarm alarm) {
        super(owner, alarmView);
        this.alarm = alarm;
        setValues();
    }

    private void setValues() {
        if(!nametf.getText().equals(type)) { nametf.setText(alarm.getName());}
        if (alarm.getTimeformat().equals("24")){
            twentyfourfm.setSelected(true);
        } else {
            twelevefm.setSelected(true);
        }
        hrcb.setValue(alarm.getTime().getHour());
        mincb.setValue(alarm.getTime().getMinute());
        snoozecb.setValue((int)alarm.getSnoozetime()/(1000*60));
        audiotxt.setText(alarm.getMediasrc());
    }

    @Override
    public void createActionButtons() {
        Button createbtn = new Button("Update");
        createbtn.setOnMouseClicked(event -> {
            stage.close();
            LocalTime time;
            String timeformat;
            if (twentyfourfm.isSelected()){
                time = LocalTime.of(hrcb.getValue() , mincb.getValue());
                timeformat = "24";
            }
            else{
                time = ampmcb.getValue().equals("AM") ? LocalTime.of((hrcb.getValue() < 12 ? hrcb.getValue() : 0 ), mincb.getValue()) : LocalTime.of((hrcb.getValue() < 12 ? 12 + hrcb.getValue() : 12 ) , mincb.getValue());
                timeformat = "12";
            }
            alarm.updateAlarm(nametf.getText(), time, timeformat, snoozecb.getValue()*1000*60,audiotxt.getText());
        });
        gridPane.add(createbtn, 0, 7);


        Button cancelbtn = new Button("Cancel");
        cancelbtn.setOnMouseClicked(event -> {
            stage.close();
        });
        gridPane.add(cancelbtn,3,7);


    }

    @Override
    public void setStageTitle() { stage.setTitle(updateTitle(type)); }

}
