package cs211.project.models;

public class TeamActivity {
    private final String nameTeamActivity;
    private String infoTeamActivity;
    private String nameTeam;
    private String nameEvent;
    private String teamActivityStatus;

    public TeamActivity(String nameEvent, String nameTeam, String nameTeamActivity, String infoTeamActivity) {
        this.nameTeam = nameTeam;
        this.nameTeamActivity = nameTeamActivity;
        this.infoTeamActivity = infoTeamActivity;
        this.nameEvent = nameEvent;
        this.teamActivityStatus = "true";
    }

    public TeamActivity(String nameEvent, String nameTeam, String nameTeamActivity, String infoTeamActivity, String teamActivityStatus) {
        this.nameTeam = nameTeam;
        this.nameTeamActivity = nameTeamActivity;
        this.infoTeamActivity = infoTeamActivity;
        this.nameEvent = nameEvent;
        this.teamActivityStatus = teamActivityStatus;
    }

    public void finishTeamActivity(String teamActivityStatus){
       this.teamActivityStatus = teamActivityStatus;
    }

    public boolean isNameTeamActivity(String nameTeamActivity) {
        return this.nameTeamActivity.equals(nameTeamActivity);
    }

    public String getNameTeam(){
        return nameTeam;
    }

    public String getNameTeamActivity() {
        return nameTeamActivity;
    }

    public String getInfoTeamActivity() {
        return infoTeamActivity;
    }

    public String getNameEvent() {return nameEvent;}

   public String getTeamActivityStatus(){return teamActivityStatus;}

    @Override
    public String toString() {
        return "[   " + "Name   :   " + nameTeamActivity + "   ]";
    }
}

