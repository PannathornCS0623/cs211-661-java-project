package cs211.project.models.collections;

import cs211.project.models.participants.JoinTeam;

import java.util.ArrayList;

public class JoinTeamList {
    private ArrayList<JoinTeam> joinTeams;
    public JoinTeamList(){joinTeams = new ArrayList<>();}

    public void addNewJoinTeam(String nameEvent, String nameTeam,String username){
        JoinTeam exist = findUsersJoinTeamByUsername(nameEvent, nameTeam, username);
        if (exist == null){
            joinTeams.add(new JoinTeam(nameEvent,nameTeam,username));
        }
    }

    public void addNewJoinTeam(String nameEvent, String nameTeam, String username, String teamStatus){
        JoinTeam exist = findUsersJoinTeamByUsername(nameEvent, nameTeam, username);
        if(exist == null){
            joinTeams.add(new JoinTeam(nameEvent,nameTeam,username,teamStatus));
        }
    }

    public  JoinTeam findUsersJoinTeamByUsername(String nameEvent, String nameTeam, String username){
        for (JoinTeam joinTeam : joinTeams){
            if (joinTeam.isNameEvent(nameEvent) && joinTeam.isNameTeam(nameTeam) && joinTeam.isUsername(username)){
                return joinTeam;
            }
        }
        return null;
    }

    public ArrayList<JoinTeam> findUsersByTeamName(String nameTeam, String username){
        ArrayList<JoinTeam> usersJoinTeam = new ArrayList<>();
        for (JoinTeam joinTeam : joinTeams){
            if (joinTeam.isNameTeam(nameTeam) && !username.equals(joinTeam.getUsername())){
                usersJoinTeam.add(joinTeam);
            }
        }
        return usersJoinTeam;
    }
    public ArrayList<JoinTeam> getJoinTeams(){ return joinTeams;}
}
