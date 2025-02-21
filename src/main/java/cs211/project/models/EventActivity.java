package cs211.project.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventActivity {
    private String eventName;
    private String nameEventActivity;
    private String infoEventActivity;
    private LocalDate dateStartEventActivity;
    private LocalDate dateEndEventActivity;
    private LocalTime timeStartEventActivity;
    private LocalTime timeEndEventActivity;

    public EventActivity(String eventName, String nameEventActivity, String infoEventActivity, LocalDate dateStartEventActivity, LocalDate dateEndEventActivity, LocalTime timeStartEventActivity, LocalTime timeEndEventActivity){
        this.eventName = eventName;
        this.nameEventActivity = nameEventActivity;
        this.infoEventActivity = infoEventActivity;
        this.dateStartEventActivity = dateStartEventActivity;
        this.dateEndEventActivity = dateEndEventActivity;
        this.timeStartEventActivity = timeStartEventActivity;
        this.timeEndEventActivity = timeEndEventActivity;
    }

    public boolean isNameEventActivity(String nameEventActivity){return this.nameEventActivity.equals(nameEventActivity);}

    public String getEventName() {return eventName;}

    public String getNameEventActivity() {return nameEventActivity;}

    public String getInfoEventActivity() {return infoEventActivity;}

    public LocalDate getDateStartEventActivity() {return dateStartEventActivity;}

    public LocalDate getDateEndEventActivity() {return dateEndEventActivity;}

    public LocalTime getTimeStartEventActivity() {return timeStartEventActivity;}

    public LocalTime getTimeEndEventActivity() {return timeEndEventActivity;}

    @Override
    public String toString() {
        return "[   " + "Name   :   " + nameEventActivity + "   ]";
    }
}
