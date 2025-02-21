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
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

public class EditEventActivityController {
    @FXML private TextField nameActivityEditUserTextField;
    @FXML private TextArea activityInfoEditUserTextArea;
    @FXML private DatePicker dateStartPicker;
    @FXML private DatePicker dateEndPicker;
    @FXML private TextField timeStarActivityEditUserTextField;
    @FXML private TextField timeEndActivityEditUserTextField;
    @FXML private ListView activityUserListEditUserListView;
    @FXML private ListView userListEditUserListView;
    private Account account;
    private Event event;
    private EventActivity selectedEventActivity;
    private EventActivityList eventActivityList;
    private Datasource<EventActivityList> datasourceEventActivityList;
    private JoinEvent selectedJoinEvent;
    private JoinEventList joinEventList;
    private Datasource<JoinEventList> datasourceJoinEventList;

    @FXML public void initialize(){
        account = (Account) JABARouter.getAccount();
        event = (Event) JABARouter.getEvent();

        datasourceEventActivityList = new EventListActivityFileDatasource("data", "event-activity.csv");
        eventActivityList = datasourceEventActivityList.readData();

        datasourceJoinEventList = new JoinEventListFileDatasource("data/participants", "join-event.csv");
        joinEventList = datasourceJoinEventList.readData();

        showActivityUserList(eventActivityList);
        showUserList(joinEventList);

        userListEditUserListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<JoinEvent>() {
            @Override
            public void changed(ObservableValue<? extends JoinEvent> observableValue, JoinEvent oldValue, JoinEvent newValue) {
                if (newValue != null){
                    selectedJoinEvent = newValue;
                }
            }
        });

        activityUserListEditUserListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EventActivity>() {
            @Override
            public void changed(ObservableValue<? extends EventActivity> observableValue, EventActivity oldValue, EventActivity newValue) {
                if (newValue != null){
                    selectedEventActivity = newValue;
                    showActivityInfo(newValue);
                }
            }
        });

    }

    public void showActivityInfo(EventActivity selectedEventActivity) {
        nameActivityEditUserTextField.setText(selectedEventActivity.getNameEventActivity());
        activityInfoEditUserTextArea.setText(selectedEventActivity.getInfoEventActivity());
        dateStartPicker.setValue(selectedEventActivity.getDateStartEventActivity());
        dateEndPicker.setValue(selectedEventActivity.getDateEndEventActivity());
        timeStarActivityEditUserTextField.setText(selectedEventActivity.getTimeStartEventActivity().toString());
        timeEndActivityEditUserTextField.setText(selectedEventActivity.getTimeEndEventActivity().toString());
    }

    public void showActivityUserList(EventActivityList eventActivityList) {
        activityUserListEditUserListView.getItems().clear();
        activityUserListEditUserListView.getItems().addAll(eventActivityList.findEventActivitiesByNameEvent(event.getNameEvent()));
    }
    public void showUserList(JoinEventList joinEventList){
        userListEditUserListView.getItems().clear();
        userListEditUserListView.getItems().addAll(joinEventList.findUsersByNameEvent(event.getNameEvent(), account.getUsername()));
    }

    @FXML public void onAddActivity(){
        String nameActivityEvent = nameActivityEditUserTextField.getText();
        String infoActivityEvent = Arrays.toString(activityInfoEditUserTextArea.getText().split("\n")).replace(", ", "|").replace("[", "").replace("]", "");
        LocalDate dateStart = dateStartPicker.getValue();
        LocalDate dateEnd = dateEndPicker.getValue();
        String getTimeStartActivityEvent = timeStarActivityEditUserTextField.getText();
        LocalTime timeStart = LocalTime.parse(getTimeStartActivityEvent);
        String getTimeEndActivityEvent = timeEndActivityEditUserTextField.getText();
        LocalTime timeEnd = LocalTime.parse(getTimeEndActivityEvent);
        eventActivityList.addNewActivityEvent(event.getNameEvent(), nameActivityEvent, infoActivityEvent, dateStart, dateEnd, timeStart, timeEnd);
        datasourceEventActivityList.writeData(eventActivityList);
        showActivityUserList(eventActivityList);
        nameActivityEditUserTextField.setText("");
        activityInfoEditUserTextArea.setText("");
        dateStartPicker.setValue(null);
        dateEndPicker.setValue(null);
        timeStarActivityEditUserTextField.setText("");
        timeEndActivityEditUserTextField.setText("");
    }

    @FXML public void onDelete(){
        eventActivityList.deleteActivityEventByNameActivityEvent(selectedEventActivity.getNameEventActivity());
        datasourceEventActivityList.writeData(eventActivityList);
        showActivityUserList(eventActivityList);
        nameActivityEditUserTextField.setText("");
        activityInfoEditUserTextArea.setText("");
        dateStartPicker.setValue(null);
        dateEndPicker.setValue(null);
        timeStarActivityEditUserTextField.setText("");
        timeEndActivityEditUserTextField.setText("");
    }

    @FXML public void onBan(){
        selectedJoinEvent.ban("false");
        datasourceJoinEventList.writeData(joinEventList);
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
