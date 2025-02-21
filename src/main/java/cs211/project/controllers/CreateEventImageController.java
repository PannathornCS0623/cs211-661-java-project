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

public class CreateEventImageController {
    @FXML
    private Circle imageCircle;
    private Account account;
    private Event myEvent;
    private EventList eventList;
    private Datasource<EventList> datasource;

    @FXML
    public void initialize() {
        account = (Account) JABARouter.getAccount();

        datasource = new EventListFileDatasource("data", "event.csv");
        eventList = datasource.readData();

        String name = (String) JABARouter.getEvent();
        myEvent = eventList.findEventsByNameEvent(name);
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
                datasource.writeData(eventList);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

        @FXML
        public void onListEvent(){
            try {
                JABARouter.goTo("CreateEventOwner", account);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        @FXML
        public void onBack(){
            try {
                JABARouter.goTo("CreateEvent");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

}

