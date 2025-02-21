package cs211.project.controllers;

import cs211.project.models.*;
import cs211.project.models.collections.JoinTeamList;
import cs211.project.models.collections.TeamActivityList;
import cs211.project.models.participants.JoinTeam;
import cs211.project.services.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Arrays;

public class EditTeamActivityController {
    @FXML private TextField nameActivityEditTeamActivityTextField;
    @FXML private TextArea activityEditTeamActivityTextArea;
    @FXML private ListView activityTeamListEditTeamActivityListView;
    @FXML private ListView userListEditTeamActivityListView;
    private Account account;
    private Event event;
    private Team team;
    private TeamActivity selectedTeamActivity;
    private TeamActivityList teamActivityList;
    private Datasource<TeamActivityList> datasourceTeamActivityList;
    private JoinTeam selectedJoinTeam;
    private JoinTeamList joinTeamList;
    private Datasource<JoinTeamList> datasourceJoinTeamList;

    @FXML
    public void initialize(){
        account = (Account) JABARouter.getAccount();
        event = (Event) JABARouter.getEvent();
        team = (Team) JABARouter.getTeam();

        datasourceTeamActivityList = new TeamActivityListFileDatasource("data", "team-activity.csv");
        teamActivityList = datasourceTeamActivityList.readData();

        datasourceJoinTeamList = new JoinTeamListFileDatasource("data/participants","join-team.csv");
        joinTeamList = datasourceJoinTeamList.readData();

        showTeamActivityList(teamActivityList);
        showTeamUserList(joinTeamList);

        userListEditTeamActivityListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<JoinTeam>() {
            @Override
            public void changed(ObservableValue<? extends JoinTeam> observableValue, JoinTeam oldValue, JoinTeam newValue) {
                if (newValue != null){
                    selectedJoinTeam = newValue;
                }
            }
        });
        activityTeamListEditTeamActivityListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TeamActivity>() {
            @Override
            public void changed(ObservableValue<? extends TeamActivity> observableValue, TeamActivity oldValue, TeamActivity newValue) {
                if (newValue != null){
                    selectedTeamActivity = newValue;
                }
            }
        });

    }

    public void showTeamActivityList(TeamActivityList teamActivityList) {
            activityTeamListEditTeamActivityListView.getItems().clear();
            activityTeamListEditTeamActivityListView.getItems().addAll(teamActivityList.findTeamActivitiesListByTeamName(team.getNameEvent(),team.getNameTeam()));
    }

    public void showTeamUserList(JoinTeamList joinTeamList){
        userListEditTeamActivityListView.getItems().clear();
        userListEditTeamActivityListView.getItems().addAll(joinTeamList.findUsersByTeamName(team.getNameTeam(), account.getUsername()));
    }

    @FXML public void onAdd(){
        String nameTeamActivity = nameActivityEditTeamActivityTextField.getText();
        String infoTeamActivity = Arrays.toString(activityEditTeamActivityTextArea.getText().split("\n")).replace(", ", "|").replace("[", "").replace("]", "");
        teamActivityList.addNewTeamActivity(event.getNameEvent(),team.getNameTeam(),nameTeamActivity, infoTeamActivity);
        datasourceTeamActivityList.writeData(teamActivityList);
        showTeamActivityList(teamActivityList);
        nameActivityEditTeamActivityTextField.setText("");
        activityEditTeamActivityTextArea.setText("");

    }

    @FXML public void onDelete(){
        teamActivityList.deleteTeamActivity(selectedTeamActivity.getNameTeamActivity());
        datasourceTeamActivityList.writeData(teamActivityList);
        showTeamActivityList(teamActivityList);

    }

    @FXML public void onBan(){
        selectedJoinTeam.banTeam("false");
        datasourceJoinTeamList.writeData(joinTeamList);
    }
    @FXML public void onFinish(){
        selectedTeamActivity.finishTeamActivity("false");
        datasourceTeamActivityList.writeData(teamActivityList);
        showTeamActivityList(teamActivityList);
    }

    @FXML public void onBack() {
        try {
            JABARouter.goTo("EditTeam");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML public void onHome(){
        try {
            JABARouter.goTo("HomeEvent");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML public void onEventList() {
        try {
            JABARouter.goTo("CreateEventOwner");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
