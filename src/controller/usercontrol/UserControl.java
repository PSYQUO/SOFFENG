package controller.usercontrol;

import model.User;

public class UserControl
{
    private static final UserControl uc = new UserControl();
    private User currentUser;

    public static UserControl getInstance()
    {
        return uc;
    }

    public User getCurrentUser()
    {
        return currentUser;
    }

    public void setCurrentUser(User user)
    {
        currentUser = user;
    }
}
