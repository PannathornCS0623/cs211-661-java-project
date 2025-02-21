package cs211.project.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event {
    private final String username;
    private String nameEvent;
    private int joinMemberEvent;
    private int memberEvent;
    private String infoEvent;
    private LocalDate dateStartEvent;
    private LocalDate dateEndEvent;
    private LocalTime timeStartEvent;
    private LocalTime timeEndEvent;
    private LocalDate dateStartJoin;
    private LocalDate dateEndJoin;
    private LocalTime timeStartJoin;
    private LocalTime timeEndJoin;
    private String imagePath;
    private String statusJoin;

    public Event(String username, String nameEvent, int memberEvent, String infoEvent,
                 LocalDate dateStartEvent, LocalDate dateEndEvent, LocalTime timeStartEvent, LocalTime timeEndEvent){
        this.username = username;
        this.nameEvent = nameEvent;
        this.joinMemberEvent = 0;
        this.memberEvent = memberEvent;
        this.infoEvent = infoEvent;
        this.dateStartEvent = dateStartEvent;
        this.dateEndEvent = dateEndEvent;
        this.timeStartEvent = timeStartEvent;
        this.timeEndEvent = timeEndEvent;
        this.dateStartJoin = LocalDate.now();
        this.dateEndJoin = dateEndEvent;
        this.timeStartJoin = LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm")));
        this.timeEndJoin = timeEndEvent;
        this.imagePath = "null";
        this.statusJoin = "false";
    }

    public Event(String username, String nameEvent, int joinMemberEvent, int memberEvent, String infoEvent,
                 LocalDate dateStartEvent, LocalDate dateEndEvent, LocalTime timeStartEvent, LocalTime timeEndEvent,
                 LocalDate dateStartJoin, LocalDate dateEndJoin, LocalTime timeStartJoin, LocalTime timeEndJoin, String imagePath, String statusJoin){
        this.username = username;
        this.nameEvent = nameEvent;
        this.joinMemberEvent = joinMemberEvent;
        this.memberEvent = memberEvent;
        this.infoEvent = infoEvent;
        this.dateStartEvent = dateStartEvent;
        this.dateEndEvent = dateEndEvent;
        this.timeStartEvent = timeStartEvent;
        this.timeEndEvent = timeEndEvent;
        this.dateStartJoin = dateStartJoin;
        this.dateEndJoin = dateEndJoin;
        this.timeStartJoin = timeStartJoin;
        this.timeEndJoin = timeEndJoin;
        this.imagePath = imagePath;
        this.statusJoin = statusJoin;
    }

    public boolean containsNameEvent(String nameEvent) {
        return this.nameEvent.contains(nameEvent);
    }

    public boolean isNameEvent(String nameEvent){
        return this.nameEvent.equals(nameEvent);
    }

    public boolean isJoinMemberEvent() {
        if (this.joinMemberEvent < this.memberEvent) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isMemberEvent(int memberEvent){
        return this.memberEvent == memberEvent;
    }

    public boolean isInfoEvent(String infoEvent){
        return this.infoEvent.equals(infoEvent);
    }

    public boolean isDateStartEvent(LocalDate dateStartEvent){
        return this.dateStartEvent.equals(dateStartEvent);
    }

    public boolean isDateEndEvent(LocalDate dateEndEvent){
        return this.dateEndEvent.equals(dateEndEvent);
    }

    public boolean isTimeStartEvent(LocalTime timeStartEvent){
        return this.timeStartEvent.equals(timeStartEvent);
    }

    public boolean isTimeEndEvent(LocalTime timeEndEvent){
        return this.timeEndEvent.equals(timeEndEvent);
    }

    public boolean isDateStartJoin(LocalDate dateStartJoin) { return this.timeStartJoin.equals(dateStartJoin);}

    public boolean isDateEndJoin(LocalDate dateEndJoin) { return this.dateEndJoin.equals(dateEndJoin);}

    public boolean isTimeStartJoin(LocalTime timeStartJoin) { return this.timeStartJoin.equals(timeStartJoin);}

    public boolean isTimeEndJoin(LocalTime timeEndJoin) { return this.timeEndJoin.equals(timeEndJoin);}


    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public void setMemberEvent(int memberEvent) {
        this.memberEvent = memberEvent;
    }

    public void setJoinMemberEvent() {
        this.joinMemberEvent ++;
    }

    public void setInfoEvent(String infoEvent) {
        this.infoEvent = infoEvent;
    }

    public void setDateStartEvent(LocalDate dateStartEvent) {
        this.dateStartEvent = dateStartEvent;
    }

    public void setDateEndEvent(LocalDate dateEndEvent) {
        this.dateEndEvent = dateEndEvent;
    }

    public void setTimeStartEvent(LocalTime timeStartEvent) {
        this.timeStartEvent = timeStartEvent;
    }

    public void setTimeEndEvent(LocalTime timeEndEvent) {
        this.timeEndEvent = timeEndEvent;
    }

    public void setDateStartJoin(LocalDate dateStartJoin) {
        this.dateStartJoin = dateStartJoin;
    }

    public void setDateEndJoin(LocalDate dateEndJoin) {
        this.dateEndJoin = dateEndJoin;
    }

    public void setTimeStartJoin(LocalTime timeStartJoin) {
        this.timeStartJoin = timeStartJoin;
    }

    public void setTimeEndJoin(LocalTime timeEndJoin) {
        this.timeEndJoin = timeEndJoin;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setStatusJoin(String statusJoin) {
        this.statusJoin = statusJoin;
    }

    public String getUsername() {
        return username;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public int getJoinMemberEvent() {
        return joinMemberEvent;
    }

    public int getMemberEvent() {
        return memberEvent;
    }

    public String getInfoEvent() {
        return infoEvent;
    }

    public LocalDate getDateStartEvent() {
        return dateStartEvent;
    }

    public LocalDate getDateEndEvent() {
        return dateEndEvent;
    }

    public LocalTime getTimeStartEvent() {
        return timeStartEvent;
    }

    public LocalTime getTimeEndEvent() {
        return timeEndEvent;
    }

    public LocalDate getDateStartJoin() {
        return dateStartJoin;
    }

    public LocalDate getDateEndJoin() {
        return dateEndJoin;
    }

    public LocalTime getTimeStartJoin() {
        return timeStartJoin;
    }

    public LocalTime getTimeEndJoin() {
        return timeEndJoin;
    }

    public String getImagePath() {
        if (this.imagePath.equals("null")) {
            return getClass().getResource("/default-image/event.png").toExternalForm();
        } else {
            return "file:data/images/events/" + this.imagePath;
        }
    }

    public String getPath() {
        return this.imagePath;
    }

    public String getStatus() {
        return statusJoin;
    }

    @Override
    public String toString() {
        return "   Event   :   " + nameEvent;
    }
}
