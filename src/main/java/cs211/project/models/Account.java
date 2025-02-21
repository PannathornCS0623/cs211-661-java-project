package cs211.project.models;

import at.favre.lib.crypto.bcrypt.BCrypt;

import java.time.LocalDate;
import java.time.LocalTime;

public class Account {
    private String name;
    private final String username;
    private String password;
    private String role;
    private String bio;
    private String imagePath;
    private LocalDate date;
    private LocalTime time;

    // Constructor ----------------------------------------
    public Account(String username, String password) {
        this.username = username;
        setPassword(password);
        this.role = "User";
        this.bio = "-";
        this.imagePath = "null";
        this.date = LocalDate.parse("1111-11-11");
        this.time = LocalTime.parse("11:11:11");
    }

    public Account(String name, String username, String password) {
        this(username, password);
        this.name = name;
    }

    public Account(String name, String username, String password, String role, String bio, String imagePath, LocalDate date, LocalTime time){
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
        this.bio = bio;
        this.imagePath = imagePath;
        this.date = date;
        this.time = time;
    }

    // Method ----------------------------------------

    public boolean validatePassword(String password) {
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), this.password);
        return result.verified; // return ture or false
    }

    public void changeNameAccount(String name) {
        this.name = name;
    }

    public void changeBioAccount(String bio) {
        this.bio = bio;
    }

    public boolean isUsername(String username) {
        return this.username.equals(username);
    }

    public String role(){
        if (this.role.equals("User")){
            return "User";
        } else if (this.role.equals("Creator")) {
            return "Creator";
        }else {
            return "Admin";
        }
    }

    // Setter ----------------------------------------
    public void setPassword(String password) {
        this.password = BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    public void setPasswordFromFile(String password) {
        this.password = password;
    }

    public void setOwnerEvent() {
        this.role = "Creator";
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }


    // Getter ----------------------------------------
    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getBio() {
        return bio;
    }

    public String getRole(){
        return role;
    }

    public String getImagePath() {
        if (this.imagePath.equals("null")) {
            return getClass().getResource("/default-image/account.png").toExternalForm();
        } else {
            return "file:data/images/accounts/" + this.imagePath;
        }
    }

    public String getPath() {
        return this.imagePath;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalTime getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return "   Username     :     " + username;
    }

}
