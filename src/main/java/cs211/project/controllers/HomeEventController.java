package cs211.project.controllers;

import cs211.project.models.Account;
import cs211.project.models.Event;
import cs211.project.models.collections.EventList;
import cs211.project.services.Datasource;
import cs211.project.services.EventListFileDatasource;
import cs211.project.services.JABARouter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class HomeEventController {
    @FXML
    private TextField searchEventTextField;
    @FXML
    private TableView searchEventTableView;
    @FXML
    private Label searchLabel;
    @FXML
    private Label nameEventListLabel;
    @FXML
    private Label memberEventListLabel;
    @FXML
    private Circle imageCircle;
    @FXML private Button adminButton;
    @FXML private Button listEventButton;
    private Account account;
    private Event selectedEvent;
    private EventList eventList;
    private Datasource<EventList> datasourceEventList;

    @FXML public void initialize(){
        account = (Account) JABARouter.getAccount();

        datasourceEventList = new EventListFileDatasource("data", "event.csv");
        eventList = datasourceEventList.readData();

        clearEventInfo();
        showEventTable(eventList.getEvents());

        if (!account.getRole().equals("Admin")) {
            adminButton.setVisible(false);
        }
        if (account.getRole().equals("User") || account.getRole().equals("Admin")) {
            listEventButton.setVisible(false);
        }

        searchEventTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Event>() {
            @Override
            public void changed(ObservableValue<? extends Event> observableValue, Event oldValue, Event newValue) {
                if (newValue != null){
                    selectedEvent = newValue;
                    showEventInfo(selectedEvent);
                }
            }
        });

        // Search Event
        searchEventTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals("")) {
                searchLabel.setText("");
                showEventTable(eventList.filterByEventName(newValue));
            } else {
                searchLabel.setText("Search");
                showEventTable(eventList.getEvents());
            }
        });
    }

    public void clearEventInfo() {
        nameEventListLabel.setText("-");
        memberEventListLabel.setText("-" + " | " + "-");
        Image image = new Image(getClass().getResource("/default-image/theCreator.png").toExternalForm());
        imageCircle.setFill(new ImagePattern(image));
    }

    public void showEventInfo(Event selectedEvent) {
        nameEventListLabel.setText(selectedEvent.getNameEvent());
        memberEventListLabel.setText(selectedEvent.getJoinMemberEvent() + " | " + selectedEvent.getMemberEvent());
        Image image = new Image(selectedEvent.getImagePath());
        imageCircle.setFill(new ImagePattern(image));
    }

    public void showEventTable(ArrayList<Event> eventList) {
        TableColumn<Event, String> nameColumn = new TableColumn<>("Event");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nameEvent"));

        TableColumn<Event, String> dateStartJoin = new TableColumn<>("Open Date Join");
        dateStartJoin.setCellValueFactory(new PropertyValueFactory<>("dateStartJoin"));

        TableColumn<Event, String> timeStartJoin = new TableColumn<>("Open Time Join");
        timeStartJoin.setCellValueFactory(new PropertyValueFactory<>("timeStartJoin"));

        TableColumn<Event, String> dateEndJoin = new TableColumn<>("Close Date Join");
        dateEndJoin.setCellValueFactory(new PropertyValueFactory<>("dateEndJoin"));

        TableColumn<Event, String> timeEndJoin = new TableColumn<>("Close Time Join");
        timeEndJoin.setCellValueFactory(new PropertyValueFactory<>("timeEndJoin"));

        searchEventTableView.getColumns().clear();
        searchEventTableView.getColumns().add(nameColumn);
        searchEventTableView.getColumns().add(dateStartJoin);
        searchEventTableView.getColumns().add(timeStartJoin);
        searchEventTableView.getColumns().add(dateEndJoin);
        searchEventTableView.getColumns().add(timeEndJoin);

        searchEventTableView.getItems().clear();

        for (Event event: eventList) {
            if (LocalDate.now().isEqual(event.getDateStartEvent())) {
                if (LocalTime.now().isAfter(event.getTimeStartEvent())) {
                    searchEventTableView.getItems().add(event);
                }
            } else if (LocalDate.now().isEqual(event.getDateEndEvent())) {
                if (LocalTime.now().isBefore(event.getTimeEndEvent())) {
                    searchEventTableView.getItems().add(event);
                }
            } else if (LocalDate.now().isAfter(event.getDateStartEvent()) && LocalDate.now().isBefore(event.getDateEndEvent())) {
                searchEventTableView.getItems().add(event);
            }
        }
    }

    @FXML
    public void onHistoryEventList() {
        try {
            JABARouter.goTo("History", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onJoinEventList() {
        try {
            JABARouter.goTo("JoinEvent", account, selectedEvent.getNameEvent());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onCreateEventList() {
        try {
            JABARouter.goTo("CreateEvent", account.getUsername());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onProfileEventList() {
        try {
            JABARouter.goTo("ProfileAccount", account.getUsername());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onLogOutEventList(){
        try {
            JABARouter.goTo("SignIn");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onAdmin() {
        try {
            JABARouter.goTo("Admin", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
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
}