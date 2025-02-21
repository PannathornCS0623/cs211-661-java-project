package cs211.project.models.collections;

import cs211.project.models.Team;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TeamList {
    private ArrayList<Team> teams;

    public TeamList() {
        teams = new ArrayList<>();
    }

    public void addNewTeam(String nameEvent, String nameTeam, int joinMemberTeam, int memberTeam, LocalDate dateStartTeam, LocalDate dateEndTeam, LocalTime timeStartTeam, LocalTime timeEndTeam) {
        nameTeam = nameTeam.trim();
        nameEvent = nameEvent.trim();
        if (!nameTeam.equals("") && joinMemberTeam>=0 && memberTeam>=0 && !dateStartTeam.equals("") && !dateEndTeam.equals("") && !timeStartTeam.equals("") && !timeEndTeam.equals("")) {
            Team exist = findTeamsByTeamName(nameEvent, nameTeam);
            if (exist == null) {
                teams.add(new Team(nameEvent.trim(),nameTeam.trim(),joinMemberTeam, memberTeam, dateStartTeam, dateEndTeam, timeStartTeam, timeEndTeam));
            }
        }
    }
    public void addNewTeam(String nameEvent, String nameTeam,int memberTeam, LocalDate dateStartTeam, LocalDate dateEndTeam, LocalTime timeStartTeam, LocalTime timeEndTeam) {
        nameTeam = nameTeam.trim();
        nameEvent = nameEvent.trim();
        if (!nameTeam.equals("") && memberTeam>=0 && !dateStartTeam.equals("") && !dateEndTeam.equals("") && !timeStartTeam.equals("") && !timeEndTeam.equals("")) {
            Team exist = findTeamsByTeamName(nameEvent, nameTeam);
            if (exist == null) {
                teams.add(new Team(nameEvent.trim(),nameTeam.trim(), memberTeam, dateStartTeam, dateEndTeam, timeStartTeam, timeEndTeam));
            }
        }
    }

    public Team findTeamsByTeamName(String nameEvent, String nameTeam) {
        for (Team team : teams) {
            if (team.isNameEvent(nameEvent) && team.isNameTeam(nameTeam)) {
                return team;
            }
        }
        return null;
    }

    public ArrayList<Team> findTeamsByNameEvent(String nameEvent){
        ArrayList<Team> ownerTeams = new ArrayList<>();
        for (Team team : teams) {
            if (team.getNameEvent().equals(nameEvent)) {
                ownerTeams.add(team);
            }
        }
        return ownerTeams;
    }

    public void addJoinMemberTeam(String nameEvent, String nameTeam) {
        Team exist = findTeamsByTeamName(nameEvent, nameTeam);
        if (exist != null) {
            exist.setJoinMemberTeam();
        }
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }
}
