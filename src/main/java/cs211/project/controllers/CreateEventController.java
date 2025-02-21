package cs211.project.controllers;

import cs211.project.models.Account;
import cs211.project.models.Event;
import cs211.project.models.collections.AccountList;
import cs211.project.models.collections.EventList;
import cs211.project.services.AccountListFileDatasource;
import cs211.project.services.Datasource;
import cs211.project.services.EventListFileDatasource;
import cs211.project.services.JABARouter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

public class CreateEventController {
    @FXML
    private TextField eventNameCreateEventTextField;
    @FXML
    private TextArea infoEventCreateEventTextArea;
    @FXML
    private DatePicker dateStartPicker;
    @FXML
    private DatePicker dateEndPicker;
    @FXML
    private TextField timeStartCreateEventTextField;
    @FXML
    private TextField timeEndCreateEventTextField;
    @FXML
    private TextField memberCreateEventTextField;
    @FXML
    private Button eventListButton;
    private Account account;
    private AccountList accountList;
    private Datasource<AccountList> datasourceAccount;
    private Event event;
    private EventList eventList;
    private Datasource<EventList> datasource;

    @FXML
    public void initialize() {
        datasourceAccount = new AccountListFileDatasource("data", "account.csv");
        accountList = datasourceAccount.readData();

        String username = (String) JABARouter.getAccount();
        account = accountList.findUsersByUsername(username);

        datasource = new EventListFileDatasource("data", "event.csv");
        eventList = datasource.readData();

        if (account.getRole().equals("User")) {
            eventListButton.setVisible(false);
        }
    }

    @FXML
    public void onCreate() {
        String nameEvent = eventNameCreateEventTextField.getText();
        String memberEvent = memberCreateEventTextField.getText();
        String infoEvent = Arrays.toString(infoEventCreateEventTextArea.getText().split("\n")).replace(", ", "|").replace("[", "").replace("]", "");

        LocalDate dateStartEvent = dateStartPicker.getValue();
        LocalDate dateEndEvent = dateEndPicker.getValue();

        String getTimeStartEvent = timeStartCreateEventTextField.getText();
        String getTimeEndEvent = timeEndCreateEventTextField.getText();
        LocalTime timeStartEvent = LocalTime.parse(getTimeStartEvent);
        LocalTime timeEndEvent = LocalTime.parse(getTimeEndEvent);

        Event exist = eventList.findEventsByNameEvent(nameEvent);
        if (exist == null) {
            eventList.addNewEvent(account.getUsername(), nameEvent, Integer.parseInt(memberEvent), infoEvent, dateStartEvent, dateEndEvent, timeStartEvent, timeEndEvent);
            accountList.ownerEvent(account.getUsername());
            datasource.writeData(eventList);
            datasourceAccount.writeData(accountList);
            event = eventList.findEventsByNameEvent(nameEvent);
            try {
                JABARouter.goTo("CreateEventImage", account, event.getNameEvent());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    public void onListEvent() {
        try {
            JABARouter.goTo("CreateEventOwner", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onBack() {
        try {
            JABARouter.goTo("HomeEvent");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}