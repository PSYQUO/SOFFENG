package model;

public class User {
    private int userID;
    private String username;
    private String userLoginName;
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

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
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