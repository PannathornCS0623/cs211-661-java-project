package cs211.project.models.collections;

import cs211.project.models.Account;
import cs211.project.services.AccountDateTimeComparator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class AccountList {
    private ArrayList<Account> accounts;

    public AccountList(){
        accounts = new ArrayList<>();
    }

    public Account findUsersByUsername(String username){
        for (Account account : accounts){
            if (account.isUsername(username)){
                return account;
            }
        }
        return null;
    }

    public ArrayList<Account> findUsersByRole(String role){
        ArrayList<Account> userList = new ArrayList<>();
        for (Account account : accounts){
            if (!account.getRole().equals(role)){
                userList.add(account);
            }
        }
        userList.sort(new AccountDateTimeComparator());
        return userList;
    }

    public void addNewUser(String name, String username, String password){
        name = name.trim();
        username = username.trim();
        password = password.trim();
        if (!name.equals("") && !username.equals("") && !password.equals("")){
            Account exist = findUsersByUsername(username);
            if (exist == null){
                accounts.add(new Account(name.trim(), username.trim(), password.trim()));
            }
        }
    }

    public void addNewUserFromFile(String name, String username, String password, String role, String bio, String imagePath, LocalDate date, LocalTime time){
        name = name.trim();
        username = username.trim();
        password = password.trim();
        role = role.trim();
        bio = bio.trim();
        if (!name.equals("") && !username.equals("") && !password.equals("") && !bio.equals("")){
            Account exist = findUsersByUsername(username);
            if (exist == null){
                Account account = new Account(name, username, password, role, bio, imagePath, date, time);
                account.setPasswordFromFile(password);
                accounts.add(account);
            }
        }
    }

    public Account login(String username, String password) {
        Account exist = findUsersByUsername(username);
        if (exist != null && exist.validatePassword(password)){
            exist.setDate(LocalDate.now());
            exist.setTime(LocalTime.now());
            return exist;
        }
        return null;
    }

    public void ownerEvent(String username) {
        Account exist = findUsersByUsername(username);
        if (exist != null) {
            exist.setOwnerEvent();
        }
    }

    public void changePassword(String username, String newPassword) {
        Account exist = findUsersByUsername(username);
        if (exist != null) {
            exist.setPassword(newPassword);
        }
    }

    public void changeName(String username, String name) {
        Account exist = findUsersByUsername(username);
        if (exist != null) {
            exist.changeNameAccount(name);
        }
    }

    public void changeBio(String username, String bio) {
        Account exist = findUsersByUsername(username);
        if (exist != null) {
            exist.changeBioAccount(bio);
        }
    }

    public String checkRole(String username){
        Account exist = findUsersByUsername(username);
        String role = "";
        if (exist != null){
            role = exist.role();
        }
        return role;
    }

    public ArrayList<Account> getUsers() {
        return accounts;
    }
}
