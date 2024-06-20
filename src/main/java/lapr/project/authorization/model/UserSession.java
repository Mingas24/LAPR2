package lapr.project.authorization.model;

import java.util.List;

public class UserSession {

    private User m_oUser = null;

    private UserSession() {
    }

    public UserSession(User oUser) {
        if (oUser == null) {
            throw new IllegalArgumentException("Argument can´t be null.");
        }
        this.m_oUser = oUser;
    }

    public void doLogout() {
        this.m_oUser = null;
    }

    public boolean isLoggedIn() {
        return this.m_oUser != null;
    }

    public boolean isLoggedInWithRole(String strRole) {
        if (isLoggedIn()) {
            return this.m_oUser.hasRole(strRole);
        }
        return false;
    }

    public String getUserName() {
        if (isLoggedIn()) {
            return this.m_oUser.getName();
        }
        return null;
    }

    public String getUserId() {
        if (isLoggedIn()) {
            return this.m_oUser.getId();
        }
        return null;
    }

    public String getUserEmail() {
        if (isLoggedIn()) {
            return this.m_oUser.getEmail();
        }
        return null;
    }

    public List<UserRole> getUserRoles() {
        return this.m_oUser.getRoles();
    }
}
