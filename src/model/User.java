package model;

/**
 * Represents an employee or an owner in the business that directly participates in 
 * the order business process and transactions. 
 * Has a username and password used to log into the system in addition to 
 * the person’s name as well as the role/position of the person.
 */
public class User {
    private int userID;
    private String username; // Name of the user.
    private String userLoginName; // Name that is used to log into the system.
    private String password;
    private Role role;

    public User(String username, String userLoginName, String password, Role role) {
        this.userID = -1;
        this.username = username;
        this.userLoginName = userLoginName;
        this.password = password;
        this.role = role;
    }

    public User(int userID, String username, String userLoginName, String password, Role role) {
        this.userID = userID;
        this.username = username;
        this.userLoginName = userLoginName;
        this.password = password;
        this.role = role;
    }

    public int getUserID(){
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username.chars().allMatch(Character::isLetter))
            this.username = username;
    }

    public String getUserLoginName() {
        return userLoginName;
    }

    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole()
    {
        return role;
    }
}