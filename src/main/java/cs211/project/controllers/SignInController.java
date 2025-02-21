package cs211.project.controllers;

import cs211.project.models.Account;
import cs211.project.models.collections.AccountList;
import cs211.project.services.Datasource;
import cs211.project.services.JABARouter;
import cs211.project.services.AccountListFileDatasource;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SignInController {
    @FXML private TextField usernameSignInTextField;
    @FXML private PasswordField passwordSignInPasswordField;
    @FXML private Label usernameLabel;
    @FXML private Label passwordLabel;
    private Account account;
    private AccountList accountList;
    private Datasource<AccountList> datasource;

    @FXML
    public void initialize(){
        datasource = new AccountListFileDatasource("data", "account.csv");
        accountList = datasource.readData();

        usernameSignInTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals("")) {
                usernameLabel.setText("");
            } else {
                usernameLabel.setText("username");
            }
        });

        passwordSignInPasswordField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals("")) {
                passwordLabel.setText("");
            } else {
                passwordLabel.setText("password");
            }
        });
    }

    public void onLogIn(){
        String username = usernameSignInTextField.getText();
        String password = passwordSignInPasswordField.getText();
        String role = accountList.checkRole(username);
        account = accountList.login(username, password);
        if (account != null) {
            datasource.writeData(accountList);
            if (role.equals("User") || role.equals("Creator")){
                try {
                    JABARouter.goTo("HomeEvent", account);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    JABARouter.goTo("Admin", account);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("LOGIN ERROR");
            alert.setContentText("Try again.");
            alert.showAndWait();
            usernameSignInTextField.setText("");
            passwordSignInPasswordField.setText("");
        }
    }

    @FXML
    public void onCreateNewAccount(){
        try {
            JABARouter.goTo("Register");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onMember() {
        try {
            JABARouter.goTo("Member");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onManual() {
        try {
            JABARouter.goTo("Manual");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
