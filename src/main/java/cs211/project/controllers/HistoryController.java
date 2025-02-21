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
import javafx.scene.control.ListView;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class HistoryController {
    @FXML
    private ListView activeEventHistoryListView;
    @FXML
    private ListView nonActiveEventHistoryListView;
    private Account account;
    private EventList eventList;
    private Datasource<EventList> datasourceEventList;
    private JoinEventList joinEventList;
    private Datasource<JoinEventList> datasourceJoinEventList;

    @FXML public void initialize(){
        account = (Account) JABARouter.getAccount();

        datasourceEventList = new EventListFileDatasource("data","event.csv");
        eventList = datasourceEventList.readData();

        datasourceJoinEventList = new JoinEventListFileDatasource("data/participants","join-event.csv");
        joinEventList = datasourceJoinEventList.readData();

        showEventHistory(joinEventList);
    }

    public void showEventHistory(JoinEventList joinEventList){
        ArrayList<JoinEvent> joinEvents = joinEventList.findEventsByUsername(account.getUsername());
        ArrayList<Event> active = new ArrayList<>();
        ArrayList<Event> non_active = new ArrayList<>();
        for (JoinEvent joinEvent : joinEvents){
            Event exist = eventList.findEventsByNameEvent(joinEvent.getNameEvent());
            if (exist != null){
                if ((LocalDate.now().isEqual(exist.getDateStartEvent()) && LocalTime.now().isAfter(exist.getTimeStartEvent())) || (LocalDate.now().isEqual(exist.getDateEndEvent()) && LocalTime.now().isBefore(exist.getTimeEndEvent()))) {
                    active.add(exist);
                } else if (LocalDate.now().isAfter(exist.getDateStartEvent()) && LocalDate.now().isBefore(exist.getDateEndEvent())){
                    active.add(exist);
                } else {
                    non_active.add(exist);
                }
            }
        }
        activeEventHistoryListView.getItems().clear();
        nonActiveEventHistoryListView.getItems().clear();
        activeEventHistoryListView.getItems().addAll(active);
        nonActiveEventHistoryListView.getItems().addAll(non_active);
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
