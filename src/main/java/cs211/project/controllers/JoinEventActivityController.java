package cs211.project.controllers;

import cs211.project.models.Account;
import cs211.project.models.Event;
import cs211.project.models.EventActivity;
import cs211.project.models.collections.EventActivityList;
import cs211.project.models.collections.JoinEventList;
import cs211.project.models.participants.JoinEvent;
import cs211.project.services.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class JoinEventActivityController {
    @FXML
    private Label nameLabel;
    @FXML
    private Label infoLabel;
    @FXML
    private Label dateStartLabel;
    @FXML
    private Label timeStartLabel;
    @FXML
    private Label dateEndLabel;
    @FXML
    private Label timeEndLabel;
    @FXML
    private ListView activityJoinUserEventListView;
    private Account account;
    private Event event;
    private EventActivityList eventActivityList;
    private Datasource<EventActivityList> datasourceEventActivityList;
    private JoinEvent joinEvent;
    private JoinEventList joinEventList;
    private Datasource<JoinEventList> datasourceJoinEventList;

    @FXML public void initialize(){
        account = (Account) JABARouter.getAccount();
        event = (Event) JABARouter.getEvent();

        datasourceEventActivityList = new EventListActivityFileDatasource("data", "event-activity.csv");
        eventActivityList = datasourceEventActivityList.readData();

        datasourceJoinEventList = new JoinEventListFileDatasource("data/participants","join-event.csv");
        joinEventList = datasourceJoinEventList.readData();
        joinEvent = joinEventList.findUsersJoinEventByUsername(event.getNameEvent(), account.getUsername());

        if (joinEvent == null) {
            if (account.getRole().equals("Admin") || account.getRole().equals("Creator")) {
                showEventActivityList(eventActivityList);
            }
        } else {
            if (joinEvent.getStatus().equals("true")) {
                showEventActivityList(eventActivityList);
            }
        }

        clearEventInfo();

        activityJoinUserEventListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EventActivity>() {
            @Override
            public void changed(ObservableValue<? extends EventActivity> observableValue, EventActivity oldValue, EventActivity newValue) {
                if (newValue != null){
                    showEventActivityInfo(newValue);
                } else {
                    clearEventInfo();
                }
            }
        });
    }

    public void clearEventInfo(){
        nameLabel.setText("-");
        infoLabel.setText("-");
        dateStartLabel.setText("-");
        timeStartLabel.setText("-");
        dateEndLabel.setText("-");
        timeEndLabel.setText("-");
    }

    public void showEventActivityInfo(EventActivity eventActivity){
        nameLabel.setText(eventActivity.getNameEventActivity());
        infoLabel.setText(eventActivity.getInfoEventActivity());
        dateStartLabel.setText(eventActivity.getDateStartEventActivity().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
        timeStartLabel.setText(eventActivity.getTimeStartEventActivity().format(DateTimeFormatter.ofPattern("hh : mm a")));
        dateEndLabel.setText(eventActivity.getDateEndEventActivity().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
        timeEndLabel.setText(eventActivity.getTimeEndEventActivity().format(DateTimeFormatter.ofPattern("hh : mm a")));
    }

    public void showEventActivityList(EventActivityList eventActivityList) {
            activityJoinUserEventListView.getItems().clear();
            activityJoinUserEventListView.getItems().addAll(eventActivityList.findEventActivitiesByNameEvent(event.getNameEvent()));
    }

    @FXML
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
}
