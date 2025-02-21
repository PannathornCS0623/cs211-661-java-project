package cs211.project.models.collections;

import cs211.project.models.participants.JoinEvent;

import java.util.ArrayList;

public class JoinEventList {
    private ArrayList<JoinEvent> joinEvents;
    public JoinEventList(){
        joinEvents = new ArrayList<>();
    }

    public void addNewJoinEvent(String nameEvent, String username){
        JoinEvent exist = findUsersJoinEventByUsername(nameEvent, username);
            if (exist == null){
                joinEvents.add(new JoinEvent(nameEvent, username));
            }
    }

    public void addNewJoinEvent(String nameEvent, String username, String status){
        JoinEvent exist = findUsersJoinEventByUsername(nameEvent, username);
        if (exist == null){
            joinEvents.add(new JoinEvent(nameEvent, username, status));
        }
    }

    public JoinEvent findUsersJoinEventByUsername(String nameEvent, String username){
        for (JoinEvent joinEvent : joinEvents){
            if (joinEvent.isNameEvent(nameEvent) && joinEvent.isUsername(username)){
                return joinEvent;
            }
        }
        return null;
    }

    public ArrayList<JoinEvent> findUsersByNameEvent(String nameEvent, String username){
        ArrayList<JoinEvent> usersJoinEvent = new ArrayList<>();
        for (JoinEvent joinEvent : joinEvents){
            if (joinEvent.isNameEvent(nameEvent) && !username.equals(joinEvent.getUsername())){
                usersJoinEvent.add(joinEvent);
            }
        }
        return usersJoinEvent;
    }
    public ArrayList<JoinEvent> findEventsByUsername(String username){
       ArrayList<JoinEvent> userJoinEvent = new ArrayList<>();
       for (JoinEvent joinEvent : joinEvents){
           if (joinEvent.isUsername(username)){
               userJoinEvent.add(joinEvent);
           }
       }
       return userJoinEvent;
    }

    public ArrayList<JoinEvent> getJoinEvents(){
        return joinEvents;
    }

}
