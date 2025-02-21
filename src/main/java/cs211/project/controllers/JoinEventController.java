package cs211.project.controllers;

import cs211.project.models.Account;
import cs211.project.models.Event;
import cs211.project.models.collections.EventList;
import cs211.project.models.collections.JoinEventList;
import cs211.project.models.participants.JoinEvent;
import cs211.project.services.Datasource;
import cs211.project.services.EventListFileDatasource;
import cs211.project.services.JABARouter;
import cs211.project.services.JoinEventListFileDatasource;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class JoinEventController {
    @FXML private Label infoLabel;
    @FXML
    private Label nameEventInfoLabel;
    @FXML
    private Label memberEventInfoLabel;
    @FXML
    private Label dateStartLabel;
    @FXML
    private Label dateEndLabel;
    @FXML
    private Label timeStartLabel;
    @FXML
    private Label timeEndLabel;
    @FXML
    private Circle imageCircle;
    @FXML private Button joinEventButton;
    @FXML private Button joinTeamButton;
    @FXML private Button infoButton;
    private Account account;
    private Event event;
    private EventList eventList;
    private Datasource<EventList> datasourceEventList;
    private JoinEventList joinEventList;
    private Datasource<JoinEventList> datasourceJoinEventList;

    public void initialize() {
        account = (Account) JABARouter.getAccount();

        datasourceEventList = new EventListFileDatasource("data", "event.csv");
        eventList = datasourceEventList.readData();

        String nameEvent = (String) JABARouter.getEvent();
        event = eventList.findEventsByNameEvent(nameEvent);

        datasourceJoinEventList = new JoinEventListFileDatasource("data/participants", "join-event.csv");
        joinEventList = datasourceJoinEventList.readData();

        showEventInfo(event);

        JoinEvent exist = joinEventList.findUsersJoinEventByUsername(event.getNameEvent(), account.getUsername());
        boolean creator = eventList.checkEvents(event.getNameEvent(), account.getUsername());
        if (creator || account.getRole().equals("Admin") || exist != null) {
            joinEventButton.setVisible(false);
        } else {
            infoButton.setVisible(false);
            joinTeamButton.setVisible(false);
        }
    }

    public void showEventInfo(Event event) {
        nameEventInfoLabel.setText(event.getNameEvent());
        memberEventInfoLabel.setText(event.getJoinMemberEvent() + " / " + event.getMemberEvent());
        infoLabel.setText(event.getInfoEvent());
        dateStartLabel.setText(event.getDateStartEvent().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
        dateEndLabel.setText(event.getDateEndEvent().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
        timeStartLabel.setText(event.getTimeStartEvent().format(DateTimeFormatter.ofPattern("hh : mm a")));
        timeEndLabel.setText(event.getTimeEndEvent().format(DateTimeFormatter.ofPattern("hh : mm a")));
        Image image = new Image(event.getImagePath());
        imageCircle.setFill(new ImagePattern(image));
    }

    @FXML
    public void onJoinEvent() {
        if (event.getStatus().equals("false")) {
            if (LocalDate.now().isEqual(event.getDateEndEvent())) {
                if (LocalTime.now().isBefore(event.getTimeEndEvent())) {
                    if (event.isJoinMemberEvent()) {
                        joinEventList.addNewJoinEvent(event.getNameEvent(), account.getUsername());
                        eventList.addJoinMemberEvent(event.getNameEvent());
                        datasourceJoinEventList.writeData(joinEventList);
                        datasourceEventList.writeData(eventList);
                        Alert alert = new Alert(Alert.AlertType.NONE);
                        alert.setHeaderText(null);
                        alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        alert.setTitle("JOIN EVENT SUCCESS");
                        alert.setContentText("You has joined this event.");
                        alert.showAndWait();
                        joinEventButton.setVisible(false);
                        infoButton.setVisible(true);
                        joinTeamButton.setVisible(true);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setTitle("JOIN EVENT FAIL");
                        alert.setContentText("This event is full.");
                        alert.showAndWait();
                    }
                }
            } else if (LocalDate.now().isBefore(event.getDateEndEvent())) {
                if (event.isJoinMemberEvent()) {
                    joinEventList.addNewJoinEvent(event.getNameEvent(), account.getUsername());
                    eventList.addJoinMemberEvent(event.getNameEvent());
                    datasourceJoinEventList.writeData(joinEventList);
                    datasourceEventList.writeData(eventList);
                    Alert alert = new Alert(Alert.AlertType.NONE);
                    alert.setHeaderText(null);
                    alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    alert.setTitle("JOIN EVENT SUCCESS");
                    alert.setContentText("You has joined this event.");
                    alert.showAndWait();
                    joinEventButton.setVisible(false);
                    infoButton.setVisible(true);
                    joinTeamButton.setVisible(true);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("JOIN EVENT FAIL");
                    alert.setContentText("This event is full.");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("JOIN EVENT FAIL");
                alert.setContentText("The time to join has expired.");
                alert.showAndWait();
            }
        } else {
            if ((LocalDate.now().isEqual(event.getDateStartJoin()) && LocalTime.now().isAfter(event.getTimeStartJoin())) || (LocalDate.now().isEqual(event.getDateEndJoin()) && LocalTime.now().isBefore(event.getTimeEndJoin()))) {
                if (event.isJoinMemberEvent()) {
                    joinEventList.addNewJoinEvent(event.getNameEvent(), account.getUsername());
                    eventList.addJoinMemberEvent(event.getNameEvent());
                    datasourceJoinEventList.writeData(joinEventList);
                    datasourceEventList.writeData(eventList);
                    Alert alert = new Alert(Alert.AlertType.NONE);
                    alert.setHeaderText(null);
                    alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    alert.setTitle("JOIN EVENT SUCCESS");
                    alert.setContentText("You has joined this event.");
                    alert.showAndWait();
                    joinEventButton.setVisible(false);
                    infoButton.setVisible(true);
                    joinTeamButton.setVisible(true);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("JOIN EVENT FAIL");
                    alert.setContentText("This event is full.");
                    alert.showAndWait();
                }
            } else {
                if (LocalDate.now().isAfter(event.getDateStartJoin()) && LocalDate.now().isBefore(event.getDateEndJoin())) {
                    if (event.isJoinMemberEvent()) {
                        joinEventList.addNewJoinEvent(event.getNameEvent(), account.getUsername());
                        eventList.addJoinMemberEvent(event.getNameEvent());
                        datasourceJoinEventList.writeData(joinEventList);
                        datasourceEventList.writeData(eventList);
                        Alert alert = new Alert(Alert.AlertType.NONE);
                        alert.setHeaderText(null);
                        alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        alert.setTitle("JOIN EVENT SUCCESS");
                        alert.setContentText("You has joined this event.");
                        alert.showAndWait();
                        joinEventButton.setVisible(false);
                        infoButton.setVisible(true);
                        joinTeamButton.setVisible(true);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setTitle("JOIN EVENT FAIL");
                        alert.setContentText("This event is full.");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("JOIN EVENT FAIL");
                    alert.setContentText("Is not time to join yet, Sorry..\n Come back later :)");
                    alert.showAndWait();
                }
            }
        }
    }

    @FXML
    public void onInfoEvent() {
        try {
            JABARouter.goTo("JoinEventActivity", account, event);
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

    @FXML
    public void onJoinTeam() {
        try {
            JABARouter.goTo("JoinTeam", account, event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
