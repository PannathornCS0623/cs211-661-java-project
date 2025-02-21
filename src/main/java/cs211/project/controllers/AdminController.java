package cs211.project.controllers;

import cs211.project.models.Account;
import cs211.project.models.collections.AccountList;
import cs211.project.services.AccountListFileDatasource;
import cs211.project.services.Datasource;
import cs211.project.services.JABARouter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class AdminController {
    @FXML private Label usernameLabel;
    @FXML private Label nameLabel;
    @FXML private Label dateLabel;
    @FXML private Label timeLabel;
    @FXML private Circle imageCircle;
    @FXML private ListView userListAdminListView;
    private Account account, selectedUser;
    private AccountList accountList;
    private Datasource<AccountList> datasource;

    @FXML
    public void initialize(){
        account = (Account) JABARouter.getAccount();

        datasource = new AccountListFileDatasource("data", "account.csv");
        accountList = datasource.readData();
        showUserList(accountList);

        clearUserInfo();
        userListAdminListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Account>() {
            @Override
            public void changed(ObservableValue<? extends Account> observableValue, Account oldValue, Account newValue) {
                if (newValue != null) {
                    selectedUser = newValue;
                    showUserInfo();
                } else {
                    clearUserInfo();
                }
            }
        });
    }

    public void clearUserInfo() {
        Image image = new Image(getClass().getResource("/default-image/account.png").toExternalForm());
        imageCircle.setFill(new ImagePattern(image));
        usernameLabel.setText("-");
        nameLabel.setText("-");
        dateLabel.setText("-");
        timeLabel.setText("-");
    }

    public void showUserInfo() {
        Image image = new Image(selectedUser.getImagePath());
        imageCircle.setFill(new ImagePattern(image));
        usernameLabel.setText(selectedUser.getUsername());
        nameLabel.setText(selectedUser.getName());
        dateLabel.setText(selectedUser.getDate().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
        timeLabel.setText(selectedUser.getTime().format(DateTimeFormatter.ofPattern("hh : mm : ss a")));
    }

    public void showUserList(AccountList accountList){
        userListAdminListView.getItems().clear();
        userListAdminListView.getItems().addAll(accountList.findUsersByRole("Admin"));
    }

    @FXML
    public void onHome(){
        try {
            JABARouter.goTo("HomeEvent", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onLogOut() {
        try {
            JABARouter.goTo("SignIn");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onProfile() {
        try {
            JABARouter.goTo("ProfileAccount", account.getUsername());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
