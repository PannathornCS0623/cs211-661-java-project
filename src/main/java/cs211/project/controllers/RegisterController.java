package cs211.project.controllers;

import cs211.project.models.Account;
import cs211.project.models.collections.AccountList;
import cs211.project.services.Datasource;
import cs211.project.services.JABARouter;
import cs211.project.services.AccountListFileDatasource;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegisterController {

    @FXML private TextField nameRegisterTextField;
    @FXML private TextField usernameRegisterTextField;
    @FXML private TextField passwordRegisterTextField;
    @FXML private TextField confirmPasswordRegisterTextField;
    @FXML private Label usernameLabel;
    @FXML private Label passwordLabel;
    @FXML private Label confirmPasswordLabel;
    @FXML private Label nameLabel;

    private Account account;
    private AccountList accountList;
    private Datasource<AccountList> datasource;

    @FXML public void initialize(){
        datasource = new AccountListFileDatasource("data", "account.csv");
        accountList = datasource.readData();

        usernameRegisterTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals("")) {
                usernameLabel.setText("");
            } else {
                usernameLabel.setText("username");
            }
        });

        passwordRegisterTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals("")) {
                passwordLabel.setText("");
            } else {
                passwordLabel.setText("password");
            }
        });

        confirmPasswordRegisterTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals("")) {
                confirmPasswordLabel.setText("");
            } else {
                confirmPasswordLabel.setText("confirm password");
            }
        });

        nameRegisterTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals("")) {
                nameLabel.setText("");
            } else {
                nameLabel.setText("name");
            }
        });
    }

    @FXML public void onRegister(){
        String name = nameRegisterTextField.getText();
        String username = usernameRegisterTextField.getText();
        String password = passwordRegisterTextField.getText();
        String confirmPassword = confirmPasswordRegisterTextField.getText();
        if (!name.equals("") && !username.equals("") && !password.equals("") && !confirmPassword.equals("") && confirmPassword.equals(password)){
            Account exist = accountList.findUsersByUsername(username);
            if (exist == null){
                accountList.addNewUser(name, username, password);
                datasource.writeData(accountList);
                account = accountList.findUsersByUsername(username);
                try {
                    JABARouter.goTo("RegisterImage", account.getUsername());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                usernameRegisterTextField.setText("");
                passwordRegisterTextField.setText("");
                confirmPasswordRegisterTextField.setText("");
                nameRegisterTextField.setText("");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("REGISTER FAIL");
                alert.setContentText("This username was used.");
                alert.showAndWait();
            }
        } else if (!name.equals("") && !username.equals("") && !password.equals("") && confirmPassword.equals("")) {
            confirmPasswordRegisterTextField.setText("");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("CONFIRM PASSWORD FAIL");
            alert.setContentText("Confirm password is wrong.");
            alert.showAndWait();
        } else {
            confirmPasswordRegisterTextField.setText("");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("REGISTER FAIL");
            alert.setContentText("Please fill out the information completely and correctly.");
            alert.showAndWait();
        }
    }

    @FXML public void onBack(){
        try {
            JABARouter.goTo("SignIn");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
