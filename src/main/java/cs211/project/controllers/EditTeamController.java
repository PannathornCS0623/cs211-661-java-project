package cs211.project.controllers;

import cs211.project.models.Account;
import cs211.project.models.Event;
import cs211.project.models.Team;
import cs211.project.models.collections.TeamList;
import cs211.project.services.Datasource;
import cs211.project.services.JABARouter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import cs211.project.services.TeamListFileDatasource;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class EditTeamController {
    @FXML private ListView<Team> teamListEditTeamListVIew;
    @FXML private TextField nameTeamEditTeamTextField;
    @FXML private TextField memberTeamEditTeamTextField;
    @FXML private TextField timeStartEditTeamTextField;
    @FXML private TextField timeEndEditTeamTextField;
    @FXML private DatePicker dateStartPicker;
    @FXML private DatePicker dateEndPicker;
    private Account account;
    private Event event;
    private TeamList teamList;
    private Datasource<TeamList> datasourceTeamList;

    @FXML public void initialize() {
        account = (Account) JABARouter.getAccount();
        event = (Event) JABARouter.getEvent();

        datasourceTeamList = new TeamListFileDatasource("data", "team.csv");
        teamList = datasourceTeamList.readData();

        showTeamList(teamList);

        teamListEditTeamListVIew.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Team>() {
            @Override
            public void changed(ObservableValue<? extends Team> observableValue,Team oldValue, Team newValue) {
                if (newValue != null){
                    try{
                        JABARouter.goTo("EditTeamActivity", account, event, newValue);
                    }catch (IOException e){
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    public void showTeamList(TeamList teamList){
        teamListEditTeamListVIew.getItems().clear();
        teamListEditTeamListVIew.getItems().addAll(teamList.findTeamsByNameEvent(event.getNameEvent()));

    }

    @FXML public void onCreateTeam(){
        String nameTeam = nameTeamEditTeamTextField.getText();
        int memberTeam = Integer.parseInt(memberTeamEditTeamTextField.getText());
        LocalDate dateStartTeam = dateStartPicker.getValue();
        LocalDate dateEndTeam = dateEndPicker.getValue();
        String getTimeStartTeam = timeStartEditTeamTextField.getText();
        LocalTime timeStartTeam = LocalTime.parse(getTimeStartTeam);
        String getTimeEndTeam = timeEndEditTeamTextField.getText();
        LocalTime timeEndTeam = LocalTime.parse(getTimeEndTeam);
        Team exist = teamList.findTeamsByTeamName(event.getNameEvent(), nameTeam);

        if (exist == null && !nameTeam.equals("")&&!timeStartTeam.equals("")&&!timeEndTeam.equals("")&&memberTeam>=0){
            teamList.addNewTeam(event.getNameEvent(), nameTeam, memberTeam, dateStartTeam, dateEndTeam, timeStartTeam, timeEndTeam);
            datasourceTeamList.writeData(teamList);
            showTeamList(teamList);
        }
    }

    @FXML public void onBack() {
        try {
            JABARouter.goTo("EditEvent");
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
