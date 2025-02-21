package cs211.project.models.participants;

public class JoinTeam {
    private String nameEvent;
    private String nameTeam;
    private String username;
    private String teamStatus;

    public JoinTeam(String nameEvent, String nameTeam, String username) {
        this.nameEvent = nameEvent;
        this.nameTeam = nameTeam;
        this.username = username;
        this.teamStatus = "true";
    }

    public JoinTeam(String nameEvent, String nameTeam, String username, String teamStatus){
        this.nameEvent = nameEvent;
        this.nameTeam = nameTeam;
        this.username = username;
        this.teamStatus = teamStatus;
    }

    public void banTeam(String teamStatus){
        this.teamStatus = teamStatus;
    }

    public boolean isUsername(String username) {return this.username.equals(username);}

    public boolean isNameTeam(String nameTeam) {return this.nameTeam.equals(nameTeam);}

    public boolean isNameEvent(String nameEvent) {return this.nameEvent.equals(nameEvent);}

    public String getNameEvent() {return nameEvent;}

    public String getNameTeam() {
        return nameTeam;
    }

    public String getUsername() {
        return username;
    }

    public String getTeamStatus() {
        return teamStatus;
    }

    @Override
    public String toString(){
        return "[   " + "User  :  " + username + "   |   " + nameTeam + "   ]";
    }
}
