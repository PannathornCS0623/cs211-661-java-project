package cs211.project.models;

public class Comment {
    private String nameTeamActivity;
    private  String name;
    private String comment;

    public Comment(String nameTeamActivity, String name, String comment){
        this.nameTeamActivity = nameTeamActivity;
        this.name = name;
        this.comment = comment;
    }

    public String getNameTeamActivity() {
        return nameTeamActivity;
    }

    public String getName() {
        return name;
    }

    public String getComment(){
        return comment;
    }

    @Override
    public String toString() {
        return  name + " : " + comment;
    }


}