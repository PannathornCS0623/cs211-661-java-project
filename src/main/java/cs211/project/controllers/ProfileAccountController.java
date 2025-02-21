package cs211.project.controllers;

import cs211.project.models.Account;
import cs211.project.models.collections.AccountList;
import cs211.project.services.Datasource;
import cs211.project.services.JABARouter;
import cs211.project.services.AccountListFileDatasource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
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

public class ProfileAccountController {
    @FXML
    private Label nameProfileUserLabel;
    @FXML
    private Label usernameProfileUserLabel;
    @FXML
    private Label bioProfileUserLabel;
    @FXML
    private Circle imageCircle;

    private Account account;
    private AccountList accountList;
    private Datasource<AccountList> datasource;

    @FXML
    public void initialize() {
        datasource = new AccountListFileDatasource("data", "account.csv");
        accountList = datasource.readData();

        String username = (String) JABARouter.getAccount();
        account = accountList.findUsersByUsername(username);

        showProfileUser(account);
    }

    public void showProfileUser(Account account) {
        nameProfileUserLabel.setText(account.getName());
        usernameProfileUserLabel.setText(account.getUsername());
        bioProfileUserLabel.setText(account.getBio());
        Image image = new Image(account.getImagePath());
        imageCircle.setFill(new ImagePattern(image));
    }

    @FXML
    public void onEditProfile() {
        try {
            JABARouter.goTo("EditProfileAccount", account.getUsername());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onBack() {
        try {
            JABARouter.goTo("HomeEvent", account);
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
                File property = new File("data/images/accounts");
                String[] fileSplit = filePath.getName().split("\\.");
                String filename = account.getUsername() + "." + fileSplit[fileSplit.length - 1];
                Path target = FileSystems.getDefault().getPath(property.getAbsolutePath(), filename);

                Files.copy(filePath.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
                Image image = new Image(target.toUri().toURL().toExternalForm());

                this.imageCircle.setFill(new ImagePattern(image));

                account.setImagePath(filename);
                datasource.writeData(accountList);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
