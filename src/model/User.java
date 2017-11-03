package model;

public class User {
    public int userId;
    private String username;
    private String userLoginName;
    private String password;
    private Role role;

    public User(String username, String userLoginName, String password, model.User.Role role) {
        this.userId = -1;
        this.username = username;
        this.userLoginName = userLoginName;
        this.password = password;
        this.role = role;
    }

    public User(int userId, String username, String userLoginName, String password, model.User.Role role) {
        this.userId = userId;
        this.username = username;
        this.userLoginName = userLoginName;
        this.password = password;
        this.role = role;
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

    public model.User.Role getRole() {
        return role;
    }

    /* I think this must be logged or something. - Gian */
    public void setRole(model.User.Role role) {
        this.role = role;
    }

    public enum Role {
        OWNER,
        SUPERVISOR,
        CASHIER
    }
}