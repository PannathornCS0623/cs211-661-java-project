package cs211.project.models.collections;

import cs211.project.models.Event;
import cs211.project.models.TeamActivity;
import java.util.ArrayList;

public class TeamActivityList {
    private ArrayList<TeamActivity> teamActivities;
    private Event event;
    public TeamActivityList() {
        teamActivities = new ArrayList<>();
    }
    public void addNewTeamActivity(String nameEvent, String nameTeam, String nameTeamActivity, String infoTeamActivity) {
        nameEvent =nameEvent.trim();
        nameTeam = nameTeam.trim();
        nameTeamActivity = nameTeamActivity.trim();
        infoTeamActivity = infoTeamActivity.trim();
        if ( !nameEvent.equals("") && !nameTeam.equals("") && !nameTeamActivity.equals("") && !infoTeamActivity.equals("")) {
            TeamActivity exist = findTeamActivitiesByTeamName(nameTeamActivity);
            if (exist == null) {
                teamActivities.add(new TeamActivity(nameEvent,nameTeam, nameTeamActivity, infoTeamActivity));
            }
        }
    }
    public void addNewTeamActivity(String nameEvent, String nameTeam, String nameTeamActivity, String infoTeamActivity, String activityTeamStatus) {
        nameEvent =nameEvent.trim();
        nameTeam = nameTeam.trim();
        nameTeamActivity = nameTeamActivity.trim();
        infoTeamActivity = infoTeamActivity.trim();
        activityTeamStatus = activityTeamStatus.trim();
        if ( !nameEvent.equals("") && !nameTeam.equals("") && !nameTeamActivity.equals("") && !infoTeamActivity.equals("") && !activityTeamStatus.equals("")) {
            TeamActivity exist = findTeamActivitiesByTeamName(nameTeamActivity);
            if (exist == null) {
                teamActivities.add(new TeamActivity(nameEvent,nameTeam, nameTeamActivity, infoTeamActivity, activityTeamStatus));
            }
        }
    }

    public TeamActivity findTeamActivitiesByTeamName(String nameTeamActivity) {
        for (TeamActivity teamActivity : teamActivities) {
            if (teamActivity.isNameTeamActivity(nameTeamActivity)) {
                return teamActivity;
            }
        }
        return null;

    }

    public ArrayList<TeamActivity> findTeamActivitiesListByTeamName(String nameEvent ,String nameTeam){
        ArrayList<TeamActivity> ownerTeamActivities = new ArrayList<>();
        for (TeamActivity teamActivity : teamActivities){
            if (teamActivity.getNameEvent().equals(nameEvent) && teamActivity.getNameTeam().equals(nameTeam) && teamActivity.getTeamActivityStatus().equals("true")) {
                ownerTeamActivities.add(teamActivity);
            }
        }
        return ownerTeamActivities;
    }

    public ArrayList<TeamActivity> findFinishTeamActivitiesListByTeamName(String nameEvent,String nameTeam){
        ArrayList<TeamActivity> finishTeamActivities = new ArrayList<>();
        for (TeamActivity teamActivity : teamActivities){
            if(teamActivity.getNameEvent().equals(nameEvent) && teamActivity.getNameTeam().equals(nameTeam) && teamActivity.getTeamActivityStatus().equals("false"))
                finishTeamActivities.add(teamActivity);
        }
        return finishTeamActivities;
    }


    public void deleteTeamActivity (String nameTeamActivity){
        TeamActivity exist = findTeamActivitiesByTeamName(nameTeamActivity);
        if(exist != null){
            teamActivities.remove(exist);
        }
    }


    public ArrayList<TeamActivity> getTeamActivities() {
        return teamActivities;
    }

}
