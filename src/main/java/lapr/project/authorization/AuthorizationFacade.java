package lapr.project.authorization;

import lapr.project.authorization.model.UserRole;
import lapr.project.authorization.model.UserRoleRegistration;
import lapr.project.authorization.model.UserRegistration;
import lapr.project.authorization.model.UserSession;
import lapr.project.authorization.model.User;

public class AuthorizationFacade {

    private UserSession m_oSession = null;

    private final UserRoleRegistration m_oRoles = new UserRoleRegistration();
    private final UserRegistration m_oUsers = new UserRegistration();

    public boolean registerUserRole(String strRole) {
        UserRole role = this.m_oRoles.newUserRole(strRole);
        return this.m_oRoles.addRole(role);
    }

    public boolean registerUserRole(String strRole, String strDescription) {
        UserRole role = this.m_oRoles.newUserRole(strRole, strDescription);
        return this.m_oRoles.addRole(role);
    }

    public boolean registerUser(String strName, String strEmail, String strPassword) {
        User utlz = this.m_oUsers.newUser(strName, strEmail, strPassword);
        return this.m_oUsers.addUser(utlz);
    }

    public boolean registerUserWithRole(String strName, String strEmail, String strPassword, String strRole) {
        UserRole role = this.m_oRoles.searchRole(strRole);
        User utlz = this.m_oUsers.newUser(strName, strEmail, strPassword);
        utlz.addRole(role);
        return this.m_oUsers.addUser(utlz);
    }

    public boolean registerUserWithRoles(String strName, String strEmail, String strPassword, String[] roles) {
        User utlz = this.m_oUsers.newUser(strName, strEmail, strPassword);
        for (String strRole : roles) {
            UserRole role = this.m_oRoles.searchRole(strRole);
            utlz.addRole(role);
        }
        return this.m_oUsers.addUser(utlz);
    }

    public boolean existUser(String strId) {
        return this.m_oUsers.hasUser(strId);
    }

    public UserSession doLogin(String strEmail, String strPwd) {
        User utlz = this.m_oUsers.searchUser(strEmail);
        if (utlz != null) {
            if (utlz.hasPassword(strPwd)) {
                this.m_oSession = new UserSession(utlz);
            }
        }
        return getPresentSession();
    }

    public UserSession getPresentSession() {
        return this.m_oSession;
    }

    public void doLogout() {
        if (this.m_oSession != null) {
            this.m_oSession.doLogout();
        }
        this.m_oSession = null;
    }
}
