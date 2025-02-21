package cs211.project.models.collections;

import cs211.project.models.Comment;

import java.util.ArrayList;

public class CommentList {
    private ArrayList<Comment> comments;

    public CommentList(){
        comments = new ArrayList<>();
    }

    public void addNewComment(String nameTeamActivity, String name, String comment){
        if (!comment.equals("")){
            comments.add(new Comment(nameTeamActivity, name, comment));
        }
    }

    public ArrayList<Comment> findCommentsByNameTeamActivity(String nameTeamActivity){
        ArrayList<Comment> ownerComment = new ArrayList<>();
        for (Comment comment : comments) {
            if (comment.getNameTeamActivity().equals(nameTeamActivity)) {
                ownerComment.add(comment);
            }
        }
        return ownerComment;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }
}
