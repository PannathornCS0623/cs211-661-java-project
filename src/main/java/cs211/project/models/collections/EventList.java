package cs211.project.models.collections;

import cs211.project.models.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class EventList {
    private ArrayList<Event> events;

    public EventList(){
        events = new ArrayList<>();
    }


    public void addNewEvent(String username, String nameEvent, int memberEvent, String infoEvent,LocalDate dateStartEvent,LocalDate dateEndEvent,LocalTime timeStartEvent,LocalTime timeEndEvent){
        username = username.trim();
        nameEvent = nameEvent.trim();
        infoEvent = infoEvent.trim();
        if(!nameEvent.equals("") && memberEvent >= 0 && !infoEvent.equals("") && !dateStartEvent.equals("") && !dateEndEvent.equals("") && !timeStartEvent.equals("") && !timeEndEvent.equals("")){
            Event exist = findEventsByNameEvent(nameEvent);
            if(exist == null){
                events.add(new Event(username.trim(), nameEvent.trim(), memberEvent,infoEvent.trim(),dateStartEvent,dateEndEvent,timeStartEvent,timeEndEvent));
            }
        }
    }

    public void addNewEvent(String username, String nameEvent, int joinMemberEvent, int memberEvent, String infoEvent,
                            LocalDate dateStartEvent,LocalDate dateEndEvent,LocalTime timeStartEvent,LocalTime timeEndEvent,
                            LocalDate dateStartJoin, LocalDate dateEndJoin, LocalTime timeStartJoin, LocalTime timeEndJoin, String imagePath, String statusJoin) {
        username = username.trim();
        nameEvent = nameEvent.trim();
        infoEvent = infoEvent.trim();
        Event exist = findEventsByNameEvent(nameEvent);
        if(!nameEvent.equals("") && joinMemberEvent >= 0 && memberEvent >= 0 && !infoEvent.equals("") && !dateStartEvent.equals("") && !dateEndEvent.equals("") && !timeStartEvent.equals("") && !timeEndEvent.equals("")) {
            if (exist == null) {
                events.add(new Event(username.trim(), nameEvent.trim(), joinMemberEvent, memberEvent, infoEvent.trim(),
                        dateStartEvent, dateEndEvent, timeStartEvent, timeEndEvent,
                        dateStartJoin, dateEndJoin, timeStartJoin, timeEndJoin, imagePath.trim(), statusJoin));
            }
        }
    }

    public Event findEventsByNameEvent(String nameEvent){
        for(Event event : events){
            if (event.isNameEvent(nameEvent)){
                return event;
            }
        }
        return null;
    }

    public ArrayList<Event> findEventsByUsername(String username) {
        ArrayList<Event> ownerEvents  = new ArrayList<>();
        for (Event event : events) {
            if (event.getUsername().equals(username)) {
                ownerEvents.add(event);
            }
        }
        return ownerEvents;
    }

    public boolean checkEvents(String nameEvent, String username) {
        for (Event event : events) {
            if (event.getNameEvent().equals(nameEvent) && event.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public void editEvent(String oldNameEvent, String newNameEvent, int memberEvent, String infoEvent,
                          LocalDate dateStartEvent, LocalDate dateEndEvent, LocalTime timeStartEvent, LocalTime timeEndEvent,
                          LocalDate dateStartJoin, LocalDate dateEndJoin, LocalTime timeStartJoin, LocalTime timeEndJoin){
        newNameEvent = newNameEvent.trim();
        infoEvent = infoEvent.trim();
        if (!newNameEvent.equals("") && memberEvent >= 0 && !infoEvent.equals("") && !timeStartEvent.equals("") && !timeEndEvent.equals("") && !timeStartJoin.equals("") && !timeEndJoin.equals("")){
            Event exist = findEventsByNameEvent(oldNameEvent);
            Event newExist = findEventsByNameEvent(newNameEvent);
            if (exist.getNameEvent().equals(newNameEvent)) {
                newExist = null;
            }
            if (exist != null && newExist == null){
                if(!exist.isNameEvent(newNameEvent)) {
                    exist.setNameEvent(newNameEvent);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("EDIT EVENT FAIL");
                alert.setContentText("This EVENT's name has been used.");
                alert.showAndWait();
                return;
            }
            if (!exist.isMemberEvent(memberEvent)){
                exist.setMemberEvent(memberEvent);
            }
            if (!exist.isInfoEvent(infoEvent)){
                exist.setInfoEvent(infoEvent);
            }
            if (!exist.isDateStartEvent(dateStartEvent)){
                exist.setDateStartEvent(dateStartEvent);
            }
            if (!exist.isDateEndEvent(dateEndEvent)){
                exist.setDateEndEvent(dateEndEvent);
            }
            if (!exist.isTimeStartEvent(timeStartEvent)){
                exist.setTimeStartEvent(timeStartEvent);
            }
            if (!exist.isTimeEndEvent(timeEndEvent)){
                exist.setTimeEndEvent(timeEndEvent);
            }

            if (!exist.isDateStartJoin(dateStartJoin)) {
                exist.setDateStartJoin(dateStartJoin);
                exist.setStatusJoin("true");
            }
            if (!exist.isDateEndJoin(dateEndJoin)) {
                exist.setDateEndJoin(dateEndJoin);
                exist.setStatusJoin("true");
            }
            if (!exist.isTimeStartJoin(timeStartJoin)) {
                exist.setTimeStartJoin(timeStartJoin);
                exist.setStatusJoin("true");
            }
            if (!exist.isTimeEndJoin(timeEndJoin)) {
                exist.setTimeEndJoin(timeEndJoin);
                exist.setStatusJoin("true");
            }
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setHeaderText(null);
            alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
            alert.setTitle("EDIT EVENT SUCCESS");
            alert.setContentText("Your EVENT has been changed.");
            alert.showAndWait();
        }
    }

    public void addJoinMemberEvent(String nameEvent) {
        Event exist = findEventsByNameEvent(nameEvent);
        if (exist != null) {
            exist.setJoinMemberEvent();
        }
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public ArrayList<Event> filterByEventName(String eventName) {
        ArrayList<Event> result = new ArrayList<>();
        for (Event event : events) {
            if (event.containsNameEvent(eventName)) {
                result.add(event);
            }
        }

        return result;
    }

}
