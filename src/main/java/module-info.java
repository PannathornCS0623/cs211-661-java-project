module cs211.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires bcrypt;
    requires java.sql;

    opens cs211.project.cs211661project to javafx.fxml;
    exports cs211.project.cs211661project;

    exports cs211.project.controllers;
    opens cs211.project.controllers to javafx.fxml;

    exports cs211.project.models;
    exports cs211.project;
    opens cs211.project to javafx.fxml;
}