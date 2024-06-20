package lapr.project.authorization.model;

import java.util.HashSet;
import java.util.Set;

public class UserRegistration {

    private Set<User> m_lstUsers = new HashSet<User>();

    public User newUser(String strName, String strEmail, String strPassword) {
        return new User(strName, strEmail, strPassword);
    }

    public boolean addUser(User utlz) {
        if (utlz != null) {
            return this.m_lstUsers.add(utlz);
        }
        return false;
    }

    public boolean removeUser(User utlz) {
        if (utlz != null) {
            return this.m_lstUsers.remove(utlz);
        }
        return false;
    }

    public User searchUser(String strId) {
        for (User utlz : this.m_lstUsers) {
            if (utlz.hasId(strId)) {
                return utlz;
            }
        }
        return null;
    }

    public boolean hasUser(String strId) {
        User utlz = searchUser(strId);
        if (utlz != null) {
            return this.m_lstUsers.contains(utlz);
        }
        return false;
    }

    public boolean hasUser(User utlz) {
        return this.m_lstUsers.contains(utlz);
    }
}
