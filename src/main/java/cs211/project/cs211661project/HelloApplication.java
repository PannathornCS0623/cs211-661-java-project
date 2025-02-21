package cs211.project.cs211661project;

import javafx.application.Application;
import javafx.stage.Stage;
import cs211.project.services.JABARouter;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        configRoute();
        stage.setResizable(false);
        JABARouter.bind(this, stage, "CS211 661 Project");

        JABARouter.goTo("SignIn");
    }

    private static void configRoute() {
        String resourcesPath = "cs211/project/views/";

        JABARouter.when("Admin", resourcesPath + "admin.fxml", 1200, 800);
        JABARouter.when("CreateEvent", resourcesPath + "create-event.fxml", 1200, 800);
        JABARouter.when("CreateEventImage",resourcesPath + "create-event-image.fxml",1200,800);
        JABARouter.when("CreateEventOwner", resourcesPath + "create-event-owner.fxml", 1200, 800);
        JABARouter.when("EditEventActivity", resourcesPath + "edit-event-activity.fxml", 1200, 800);
        JABARouter.when("EditEvent", resourcesPath + "edit-event.fxml", 1200, 800);
        JABARouter.when("EditProfileAccount", resourcesPath + "edit-profile-account.fxml", 1200, 800);
        JABARouter.when("EditTeamActivity", resourcesPath + "edit-team-activity.fxml", 1200, 800);
        JABARouter.when("EditTeam", resourcesPath + "edit-team.fxml", 1200, 800);
        JABARouter.when("History", resourcesPath + "history.fxml", 1200, 800);
        JABARouter.when("HomeEvent", resourcesPath + "home-event.fxml", 1200, 800);
        JABARouter.when("JoinEventActivity", resourcesPath + "join-event-activity.fxml", 1200, 800);
        JABARouter.when("JoinEvent", resourcesPath + "join-event.fxml", 1200, 800);
        JABARouter.when("JoinTeamActivity", resourcesPath + "join-team-activity.fxml", 1200, 800);
        JABARouter.when("JoinTeam", resourcesPath + "join-team.fxml", 1200, 800);
        JABARouter.when("ProfileAccount", resourcesPath + "profile-account.fxml", 1200, 800);
        JABARouter.when("Manual", resourcesPath + "manual.fxml", 1200, 800);
        JABARouter.when("Member", resourcesPath + "member.fxml", 1200, 800);
        JABARouter.when("Register", resourcesPath + "register.fxml", 1200, 800);
        JABARouter.when("RegisterImage", resourcesPath + "register-image.fxml", 1200, 800);
        JABARouter.when("SignIn", resourcesPath + "signin.fxml", 1200, 800);
    }

    public static void main(String[] args) {
        launch();
    }
}