package cs211.project.models.collections;

import cs211.project.models.EventActivity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class EventActivityList {
    private ArrayList<EventActivity> eventActivities;

    public EventActivityList(){
        eventActivities = new ArrayList<>();
    }

    public void addNewActivityEvent(String username, String nameActivityEvent, String infoActivityEvent, LocalDate dateStartActivityEvent, LocalDate dateEndActivityEvent, LocalTime timeStartActivityEvent, LocalTime timeEndActivityEvent){
        username = username.trim();
        nameActivityEvent = nameActivityEvent.trim();
        infoActivityEvent = infoActivityEvent.trim();
        if(!nameActivityEvent.equals("") && !infoActivityEvent.equals("") && !dateStartActivityEvent.equals("") && !dateEndActivityEvent.equals("") && !timeStartActivityEvent.equals("") && !timeEndActivityEvent.equals("")){
            EventActivity exist = findEventActivitiesByNameEventActivity(nameActivityEvent);
            if(exist == null){
                eventActivities.add(new EventActivity(username.trim(), nameActivityEvent.trim(), infoActivityEvent.trim(),dateStartActivityEvent,dateEndActivityEvent,timeStartActivityEvent,timeEndActivityEvent));
            }
        }
    }

    public void deleteActivityEventByNameActivityEvent(String nameActivityEvent){
        EventActivity exist = findEventActivitiesByNameEventActivity(nameActivityEvent);
            if (exist != null){
                eventActivities.remove(exist);
            }
    }

    public EventActivity findEventActivitiesByNameEventActivity(String nameActivityEvent){
        for(EventActivity eventActivity : eventActivities){
            if(eventActivity.isNameEventActivity(nameActivityEvent)){
                return eventActivity;
            }
        }
        return null;
    }

    public ArrayList<EventActivity> findEventActivitiesByNameEvent(String nameEvent) {
        ArrayList<EventActivity> ownerEventActivities  = new ArrayList<>();
        for (EventActivity eventActivity : eventActivities) {
            if (eventActivity.getEventName().equals(nameEvent)) {
                ownerEventActivities.add(eventActivity);
            }
        }
        return ownerEventActivities;
    }

    public ArrayList<EventActivity> getEventActivities() {
        return eventActivities;
    }


}
