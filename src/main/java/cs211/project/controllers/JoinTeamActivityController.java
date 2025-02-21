package cs211.project.controllers;

import cs211.project.models.*;
import cs211.project.models.collections.CommentList;
import cs211.project.models.collections.JoinTeamList;
import cs211.project.models.collections.TeamActivityList;
import cs211.project.models.participants.JoinTeam;
import cs211.project.services.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class JoinTeamActivityController {
    @FXML private Label nameJoinUserTeamLabel;
    @FXML private Label informationLabel;
    @FXML private ListView<Comment> commentJoinUserTeamListView;
    @FXML private ListView<TeamActivity> activityJoinUserTeamListView;
    @FXML private ListView<TeamActivity> finishedActivitiesTableView;
    @FXML private TextField commentJoinUserTeamTextField;

    private Account account;
    private Event event;
    private Team team;
    private TeamActivity value;
    private TeamActivityList teamActivityList;
    private Datasource<TeamActivityList> datasourceTeamActivity;
    private CommentList commentList;
    private Datasource<CommentList> datasourceCommentList;
    private JoinTeam joinTeam;
    private JoinTeamList joinTeamList;
    private Datasource<JoinTeamList> datasourceJoinTeamList;

    @FXML
    public void initialize() {
        account = (Account) JABARouter.getAccount();
        event = (Event) JABARouter.getEvent();
        team = (Team) JABARouter.getTeam();

        datasourceCommentList = new CommentListFileDatasource("data","comment.csv");
        commentList = datasourceCommentList.readData();

        datasourceTeamActivity = new TeamActivityListFileDatasource("data", "team-activity.csv");
        teamActivityList = datasourceTeamActivity.readData();

        datasourceJoinTeamList = new JoinTeamListFileDatasource("data/participants", "join-team.csv");
        joinTeamList = datasourceJoinTeamList.readData();
        joinTeam = joinTeamList.findUsersJoinTeamByUsername(event.getNameEvent(), team.getNameTeam(), account.getUsername());

        if (joinTeam == null) {
            if ((account.getRole().equals("Admin") || account.getRole().equals("Creator"))) {
                showTeamActivityList(teamActivityList);
                showFinishActivityTeamList(teamActivityList);
            }
        } else {
            if (joinTeam.getTeamStatus().equals("true")) {
                showTeamActivityList(teamActivityList);
                showFinishActivityTeamList(teamActivityList);
            }
        }

        activityJoinUserTeamListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TeamActivity>() {
            @Override
            public void changed(ObservableValue<? extends TeamActivity> observableValue, TeamActivity oldValue, TeamActivity newValue) {
                if (newValue != null && (account.getRole().equals("Admin") || account.getRole().equals("Creator"))) {
                    showCommentList(newValue.getNameTeamActivity());
                    showTeamActivityInfo(newValue);
                    value = newValue;
                } else if (newValue != null && joinTeam.getTeamStatus().equals("true")){
                    showCommentList(newValue.getNameTeamActivity());
                    showTeamActivityInfo(newValue);
                    value = newValue;
                } else {
                    clearCommentInfo();
                }
            }

        });
    }

    public void clearCommentInfo(){
        commentJoinUserTeamTextField.setText("");
        nameJoinUserTeamLabel.setText("-");
        informationLabel.setText("-");
    }

    public void showTeamActivityInfo(TeamActivity teamActivity){
        nameJoinUserTeamLabel.setText(teamActivity.getNameTeamActivity());
        informationLabel.setText(teamActivity.getInfoTeamActivity());
    }

    public void showCommentList(String nameTeamActivity) {
        commentJoinUserTeamListView.getItems().clear();
        commentJoinUserTeamListView.getItems().addAll(commentList.findCommentsByNameTeamActivity(nameTeamActivity));
    }

    public void showTeamActivityList(TeamActivityList teamActivityList) {
        activityJoinUserTeamListView.getItems().clear();
        activityJoinUserTeamListView.getItems().addAll(teamActivityList.findTeamActivitiesListByTeamName(team.getNameEvent(),team.getNameTeam()));
    }

    public void showFinishActivityTeamList(TeamActivityList teamActivityList){
        finishedActivitiesTableView.getItems().clear();
        finishedActivitiesTableView.getItems().addAll(teamActivityList.findFinishTeamActivitiesListByTeamName(team.getNameEvent(),team.getNameTeam()));
    }

    @FXML

    public void onBackJoinUserTeam() {
        try {
            JABARouter.goTo("JoinTeam");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onHomeJoinTeamEvent() {
        try {
            JABARouter.goTo("HomeEvent");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onSentJoinUserTeam() {
        String comment = commentJoinUserTeamTextField.getText();
        commentList.addNewComment(value.getNameTeamActivity(), account.getName(), comment);
        datasourceCommentList.writeData(commentList);
        showCommentList(value.getNameTeamActivity());
        commentJoinUserTeamTextField.setText("");
    }


}

