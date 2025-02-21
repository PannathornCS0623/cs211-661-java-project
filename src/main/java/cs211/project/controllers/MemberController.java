package cs211.project.controllers;

import cs211.project.services.JABARouter;
import javafx.fxml.FXML;

import java.io.IOException;

public class MemberController {
    @FXML
    public void onBack() {
        try {
            JABARouter.goTo("SignIn");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
