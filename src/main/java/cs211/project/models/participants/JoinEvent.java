package cs211.project.models.participants;

public class JoinEvent {
    private String nameEvent;
    private String username;
    private String status;

    public JoinEvent(String nameEvent, String username){
        this.nameEvent = nameEvent;
        this.username = username;
        this.status = "true";
    }

    public JoinEvent(String nameEvent, String username, String status) {
        this(nameEvent, username);
        this.status = status;
    }

    public void ban(String status){
        this.status = status;
    }

    public boolean isUsername(String username) {
        return this.username.equals(username);
    }

    public boolean isNameEvent(String nameEvent) {
        return this.nameEvent.equals(nameEvent);
    }

    public String getNameEvent(){
        return nameEvent;
    }

    public String getUsername(){
        return username;
    }

    public String getStatus(){
        return status;
    }

    @Override
    public String toString(){
        return "[   " + "User  :  " + username + "   |   " + nameEvent + "   ]";
    }

}
