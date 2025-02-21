package cs211.project.controllers;

import cs211.project.models.Account;
import cs211.project.models.collections.AccountList;
import cs211.project.services.AccountListFileDatasource;
import cs211.project.services.Datasource;
import cs211.project.services.JABARouter;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class EditProfileAccountController {
    @FXML private Label usernameEditProfileUserLabel;
    @FXML private TextField oldPasswordEditProfileUserTextField;
    @FXML private TextField newPasswordEditProfileUserTextField;
    @FXML private TextField confirmPasswordEditProfileUserTextField;
    @FXML private TextField nameEditProfileUserTextField;
    @FXML private TextField bioEditProfileUserTextField;

    private Account account;
    private AccountList accountList;
    private Datasource<AccountList> datasource;

    @FXML public void initialize() {
        datasource = new AccountListFileDatasource("data", "account.csv");
        accountList = datasource.readData();

        String username = (String) JABARouter.getAccount();
        account = accountList.findUsersByUsername(username);

        showAccountInfo(account);
    }

    @FXML public void showAccountInfo(Account account){
        usernameEditProfileUserLabel.setText(account.getUsername());
    }

    @FXML public void onChangePassword() {
        String oldPassword = oldPasswordEditProfileUserTextField.getText();
        String newPassword = newPasswordEditProfileUserTextField.getText();
        String confirmPassword = confirmPasswordEditProfileUserTextField.getText();
        if (!oldPassword.equals("") && !newPassword.equals("") && !confirmPassword.equals("")) {
            if (account.validatePassword(oldPassword) && newPassword.equals(confirmPassword)) {
                accountList.changePassword(account.getUsername(), newPassword);
                datasource.writeData(accountList);
                oldPasswordEditProfileUserTextField.setText("");
                newPasswordEditProfileUserTextField.setText("");
                confirmPasswordEditProfileUserTextField.setText("");
                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setHeaderText(null);
                alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
                alert.setTitle("CHANGE PASSWORD SUCCESS");
                alert.setContentText("You has changed your password.");
                alert.showAndWait();
            } else {
                oldPasswordEditProfileUserTextField.setText("");
                newPasswordEditProfileUserTextField.setText("");
                confirmPasswordEditProfileUserTextField.setText("");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("CHANGE PASSWORD FAIL");
                alert.setContentText("Try again.");
                alert.showAndWait();
            }
        } else {
            oldPasswordEditProfileUserTextField.setText("");
            newPasswordEditProfileUserTextField.setText("");
            confirmPasswordEditProfileUserTextField.setText("");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("CHANGE PASSWORD FAIL");
            alert.setContentText("Can't use blank space");
            alert.showAndWait();
        }
    }

    @FXML public void onChangeName() {
        String name = nameEditProfileUserTextField.getText();
        if (!name.equals("")) {
            accountList.changeName(account.getUsername(), name);
            datasource.writeData(accountList);
            nameEditProfileUserTextField.setText("");
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setHeaderText(null);
            alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
            alert.setTitle("CHANGE NAME SUCCESS");
            alert.setContentText("You has changed your name.");
            alert.showAndWait();
        } else {
            nameEditProfileUserTextField.setText("");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("CHANGE NAME FAIL");
            alert.setContentText("Can't use blank space");
            alert.showAndWait();
        }
    }

    @FXML public void onChangeBio() {
        String bio = bioEditProfileUserTextField.getText();
        if (!bio.equals("")){
            accountList.changeBio(account.getUsername(), bio);
            datasource.writeData(accountList);
            bioEditProfileUserTextField.setText("");
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setHeaderText(null);
            alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
            alert.setTitle("CHANGE BIO SUCCESS");
            alert.setContentText("You has changed bio.");
            alert.showAndWait();
        }
    }

    @FXML public void onBack() {
        try {
            JABARouter.goTo("ProfileAccount");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

     @FXML public void onHome() {
        try {
            JABARouter.goTo("HomeEvent");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
