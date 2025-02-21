package cs211.project.controllers;

import cs211.project.models.Account;
import cs211.project.models.Event;
import cs211.project.models.Team;
import cs211.project.models.collections.EventList;
import cs211.project.models.collections.TeamList;
import cs211.project.models.collections.JoinTeamList;
import cs211.project.models.participants.JoinTeam;
import cs211.project.services.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class JoinTeamController {
    @FXML
    private ListView<Team> teamListListView;
    @FXML private Button joinButton;
    @FXML private Button infoButton;
    @FXML
    private TeamList teamList;
    @FXML
    private Label nameTeamTeamListLabel;
    @FXML
    private Label memberTeamTeamListLabel;
    @FXML
    private Label dateStartTeamListLabel;
    @FXML
    private Label dateEndTeamListLabel;
    @FXML
    private Label timeStartTeamListLabel;
    @FXML
    private Label timeEndTeamListLabel;

    private Team selectedTeam;
    private Event event;
    private EventList eventList;
    private Account account;
    private JoinTeamList joinTeamList;
    private Datasource<TeamList> datasourceTeamList;
    private Datasource<EventList> datasourceEventList;
    private Datasource<JoinTeamList> datasourceJoinTeamList;
    @FXML
    public void initialize(){
        account = (Account) JABARouter.getAccount();
        event = (Event) JABARouter.getEvent();

        datasourceEventList = new EventListFileDatasource("data", "event.csv");
        eventList = datasourceEventList.readData();

        datasourceTeamList = new TeamListFileDatasource("data","team.csv");
        teamList = datasourceTeamList.readData();

        datasourceJoinTeamList = new JoinTeamListFileDatasource("data/participants", "join-team.csv");
        joinTeamList = datasourceJoinTeamList.readData();

        clearTeamInfo();
        showTeamList(teamList);

        teamListListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Team>() {
            @Override
            public void changed(ObservableValue<? extends Team> observableValue, Team oldValue, Team newValue) {
                if (newValue != null) {
                    selectedTeam = newValue;
                    showTeamInfo(selectedTeam);

                    JoinTeam exist = joinTeamList.findUsersJoinTeamByUsername(event.getNameEvent(), selectedTeam.getNameTeam(), account.getUsername());
                    boolean creator = eventList.checkEvents(selectedTeam.getNameEvent(), account.getUsername());
                    if (creator || account.getRole().equals("Admin") || exist != null) {
                        joinButton.setVisible(false);
                        infoButton.setVisible(true);
                    } else {
                        infoButton.setVisible(false);
                        joinButton.setVisible(true);
                    }
                }
            }
        });
    }

    public void clearTeamInfo() {
        nameTeamTeamListLabel.setText("-");
        memberTeamTeamListLabel.setText("-");
        dateStartTeamListLabel.setText("-");
        dateEndTeamListLabel.setText("-");
        timeStartTeamListLabel.setText("-");
        timeEndTeamListLabel.setText("-");
    }

    public void showTeamInfo(Team selectedTeam) {
        nameTeamTeamListLabel.setText(selectedTeam.getNameTeam());
        memberTeamTeamListLabel.setText(selectedTeam.getJoinMemberTeam() + " / " + selectedTeam.getMemberTeam());
        dateStartTeamListLabel.setText(selectedTeam.getDateStartTeam().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
        dateEndTeamListLabel.setText(selectedTeam.getDateEndTeam().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
        timeStartTeamListLabel.setText(selectedTeam.getTimeStartTeam().format(DateTimeFormatter.ofPattern("hh : mm a")));
        timeEndTeamListLabel.setText(selectedTeam.getTimeEndTeam().format(DateTimeFormatter.ofPattern("hh : mm a")));
    }

    public void showTeamList(TeamList teamList) {
        teamListListView.getItems().clear();
        teamListListView.getItems().addAll(teamList.findTeamsByNameEvent(event.getNameEvent()));
    }


    public void onBack() {
        try {
            JABARouter.goTo("JoinEvent");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onHome() {
        try {
            JABARouter.goTo("HomeEvent");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onJoinTeamActivity() {
        if ((LocalDate.now().isEqual(selectedTeam.getDateStartTeam()) && LocalTime.now().isAfter(selectedTeam.getTimeStartTeam())) || (LocalDate.now().isEqual(selectedTeam.getDateEndTeam()) && LocalTime.now().isBefore(selectedTeam.getTimeEndTeam()))) {
            if (selectedTeam.isJoinMemberTeam()) {
                joinTeamList.addNewJoinTeam(event.getNameEvent(), selectedTeam.getNameTeam(), account.getUsername());
                teamList.addJoinMemberTeam(event.getNameEvent(), selectedTeam.getNameTeam());
                datasourceJoinTeamList.writeData(joinTeamList);
                datasourceTeamList.writeData(teamList);
                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setHeaderText(null);
                alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
                alert.setTitle("JOIN TEAM SUCCESS");
                alert.setContentText("You has joined this team.");
                alert.showAndWait();
                joinButton.setVisible(false);
                infoButton.setVisible(true);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("JOIN TEAM FAIL");
                alert.setContentText("This team is full.");
                alert.showAndWait();
            }
        } else  if (LocalDate.now().isAfter(selectedTeam.getDateStartTeam()) && LocalDate.now().isBefore(selectedTeam.getDateEndTeam())) {
            if (selectedTeam.isJoinMemberTeam()) {
                joinTeamList.addNewJoinTeam(event.getNameEvent(), selectedTeam.getNameTeam(), account.getUsername());
                teamList.addJoinMemberTeam(event.getNameEvent(), selectedTeam.getNameTeam());
                datasourceJoinTeamList.writeData(joinTeamList);
                datasourceTeamList.writeData(teamList);
                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setHeaderText(null);
                alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
                alert.setTitle("JOIN TEAM SUCCESS");
                alert.setContentText("You has joined this team.");
                alert.showAndWait();
                joinButton.setVisible(false);
                infoButton.setVisible(true);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("JOIN TEAM FAIL");
                alert.setContentText("This team is full.");
                alert.showAndWait();
            }

        }else if(LocalDate.now().isBefore(selectedTeam.getDateStartTeam())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("JOIN TEAM FAIL");
            alert.setContentText("Is not time to join yet, Sorry..\n Come back later :)");
            alert.showAndWait();
        }

        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("JOIN TEAM FAIL");
            alert.setContentText("The time to join has expired.");
            alert.showAndWait();
        }
    }

    @FXML public void onInfoTeamActivity() {
        try {
            JABARouter.goTo("JoinTeamActivity", account, event, selectedTeam);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

