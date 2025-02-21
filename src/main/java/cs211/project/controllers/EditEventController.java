package cs211.project.controllers;

import cs211.project.models.Account;
import cs211.project.models.Event;
import cs211.project.models.collections.EventList;
import cs211.project.services.Datasource;
import cs211.project.services.EventListFileDatasource;
import cs211.project.services.JABARouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

public class EditEventController {
    @FXML private TextField eventNameEditEventTextField;
    @FXML private TextField memberEditEventTextField;
    @FXML private TextArea infoEventEditEventTextArea;
    @FXML private DatePicker dateStartEventPicker;
    @FXML private DatePicker dateEndEventPicker;
    @FXML private TextField timeStartEditEventTextField;
    @FXML private TextField timeEndEditEventTextField;
    @FXML private DatePicker dateStartJoinPicker;
    @FXML private DatePicker dateEndJoinPicker;
    @FXML private TextField timeStartJoinEditEventTextField;
    @FXML private TextField timeEndJoinEditEventTextField;
    @FXML private Circle imageCircle;
    private Account account;
    private Event myEvent;
    private EventList eventList;
    private Datasource<EventList> datasource;

    @FXML public void initialize(){
        account = (Account) JABARouter.getAccount();

        datasource = new EventListFileDatasource("data", "event.csv");
        eventList = datasource.readData();

        String name = (String) JABARouter.getEvent();
        myEvent = eventList.findEventsByNameEvent(name);

        showEventInfo(myEvent);
    }

    public void showEventInfo(Event event){
        eventNameEditEventTextField.setText(event.getNameEvent());
        memberEditEventTextField.setText("" + event.getMemberEvent());
        infoEventEditEventTextArea.setText(event.getInfoEvent());
        dateStartEventPicker.setValue(event.getDateStartEvent());
        dateEndEventPicker.setValue(event.getDateEndEvent());
        timeStartEditEventTextField.setText(event.getTimeStartEvent().toString());
        timeEndEditEventTextField.setText(event.getTimeEndEvent().toString());
        dateStartJoinPicker.setValue(event.getDateStartJoin());
        dateEndJoinPicker.setValue(event.getDateEndJoin());
        timeStartJoinEditEventTextField.setText(event.getTimeStartJoin().toString());
        timeEndJoinEditEventTextField.setText(event.getTimeEndJoin().toString());
        Image image = new Image(event.getImagePath());
        imageCircle.setFill(new ImagePattern(image));
    }

    @FXML public void onEdit(){
        String nameEvent = eventNameEditEventTextField.getText();
        int memberEvent = Integer.parseInt(memberEditEventTextField.getText());
        String info = infoEventEditEventTextArea.getText();
        String infoEvent = Arrays.toString(info.split("\n")).replace(", ", "|").replace("[", "").replace("]", "");

        LocalDate dateStartEvent = dateStartEventPicker.getValue();
        LocalDate dateEndEvent = dateEndEventPicker.getValue();

        String getTimeStartEvent = timeStartEditEventTextField.getText();
        String getTimeEndEvent = timeEndEditEventTextField.getText();
        LocalTime timeStartEvent = LocalTime.parse(getTimeStartEvent);
        LocalTime timeEndEvent = LocalTime.parse(getTimeEndEvent);

        LocalDate dateStartJoin = dateStartJoinPicker.getValue();
        LocalDate dateEndJoin = dateEndJoinPicker.getValue();

        String getTimeStartJoin = timeStartJoinEditEventTextField.getText();
        String getTimeEndJoin = timeEndJoinEditEventTextField.getText();
        LocalTime timeStartJoin = LocalTime.parse(getTimeStartJoin);
        LocalTime timeEndJoin = LocalTime.parse(getTimeEndJoin);

        eventList.editEvent(myEvent.getNameEvent(), nameEvent,memberEvent,infoEvent,
                dateStartEvent,dateEndEvent,timeStartEvent,timeEndEvent,
                dateStartJoin, dateEndJoin, timeStartJoin, timeEndJoin);
        datasource.writeData(eventList);
    }

    @FXML public void onBack(){
        try {
            JABARouter.goTo("CreateEventOwner");
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

    @FXML public void onEditUser(){
        try {
            JABARouter.goTo("EditEventActivity", account, myEvent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML public void onEditTeam(){
        try {
            JABARouter.goTo("EditTeam", account, myEvent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onEditImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File filePath;
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        fileChooser.setTitle("Open image");
        String userDirectoryString = System.getProperty("user.home");
        File userDirectory = new File(userDirectoryString);
        if (!userDirectory.canRead())
            userDirectory = new File("C:/");
        filePath = fileChooser.showOpenDialog(stage);

        if (filePath != null) {
            try {
                File property = new File("data/images/events");
                String[] fileSplit = filePath.getName().split("\\.");
                String filename = myEvent.getNameEvent() + "." + fileSplit[fileSplit.length - 1];
                Path target = FileSystems.getDefault().getPath(property.getAbsolutePath(), filename);

                Files.copy(filePath.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
                Image image = new Image(target.toUri().toURL().toExternalForm());

                this.imageCircle.setFill(new ImagePattern(image));

                myEvent.setImagePath(filename);
                System.out.println(myEvent.getPath());
                datasource.writeData(eventList);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
