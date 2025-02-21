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
import javafx.scene.control.ListView;

import java.io.IOException;

public class CreateEventOwnerController {
    @FXML
    private ListView listEventListView;
    private Account account;
    private EventList eventList;
    private Datasource<EventList> datasource;

    @FXML
    public void initialize() {
        account = (Account) JABARouter.getAccount();

        datasource = new EventListFileDatasource("data", "event.csv");
        eventList = datasource.readData();

        showList(eventList);

        listEventListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Event>() {
            @Override
            public void changed(ObservableValue<? extends Event> observableValue, Event oldValue, Event newValue) {
                if (newValue != null) {
                    try {
                        JABARouter.goTo("EditEvent", account, newValue.getNameEvent());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    public void showList(EventList eventList) {
        listEventListView.getItems().clear();
        if (account.getRole().equals("Admin")) {
            listEventListView.getItems().addAll(eventList.getEvents());
        } else {
            listEventListView.getItems().addAll(eventList.findEventsByUsername(account.getUsername()));
        }
    }

    @FXML
    public void onBack() {
        try {
            JABARouter.goTo("CreateEvent", account.getUsername());
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
