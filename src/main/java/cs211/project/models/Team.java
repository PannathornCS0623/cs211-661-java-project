package cs211.project.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Team {
    private final String nameEvent;
    private final String nameTeam;
    private int joinMemberTeam;
    private int memberTeam;
    private LocalDate dateStartTeam;
    private LocalDate dateEndTeam;
    private LocalTime timeStartTeam;
    private LocalTime timeEndTeam;

    public Team(String nameEvent,String nameTeam, int memberTeam, LocalDate dateStartTeam, LocalDate dateEndTeam, LocalTime timeStartTeam, LocalTime timeEndTeam) {
        this.nameEvent = nameEvent;
        this.nameTeam = nameTeam;
        this.joinMemberTeam = 0;
        this.memberTeam = memberTeam;
        this.dateStartTeam = dateStartTeam;
        this.dateEndTeam = dateEndTeam;
        this.timeStartTeam = timeStartTeam;
        this.timeEndTeam = timeEndTeam;
    }
    public Team(String nameEvent,String nameTeam, int joinMemberTeam, int memberTeam, LocalDate dateStartTeam, LocalDate dateEndTeam, LocalTime timeStartTeam, LocalTime timeEndTeam) {
        this.nameEvent = nameEvent;
        this.nameTeam = nameTeam;
        this.joinMemberTeam = joinMemberTeam;
        this.memberTeam = memberTeam;
        this.dateStartTeam = dateStartTeam;
        this.dateEndTeam = dateEndTeam;
        this.timeStartTeam = timeStartTeam;
        this.timeEndTeam = timeEndTeam;
    }

    public boolean isNameEvent(String nameEvent) {
        return this.nameEvent.equals(nameEvent);
    }

    public boolean isNameTeam(String nameTeam) {
        return this.nameTeam.equals(nameTeam);
    }

    public boolean isJoinMemberTeam(){
        if (this.joinMemberTeam < this.memberTeam){
            return true;
        }else{
            return false;
        }
    }

    public void setJoinMemberTeam() {
        this.joinMemberTeam++;
    }

    public String getNameEvent(){
        return nameEvent;
    }

    public String getNameTeam() {
        return nameTeam;
    }

    public int getJoinMemberTeam() {return joinMemberTeam;}

    public int getMemberTeam() {
        return memberTeam;
    }

    public LocalDate getDateStartTeam() {
        return dateStartTeam;
    }

    public LocalDate getDateEndTeam() {
        return dateEndTeam;
    }

    public LocalTime getTimeStartTeam() {
        return timeStartTeam;
    }

    public LocalTime getTimeEndTeam() {
        return timeEndTeam;
    }

    @Override
    public String toString() {
        return "[ "+"Team Name : " + nameTeam + "  ,  " + "Member : " + memberTeam + " ] ";
    }
}

