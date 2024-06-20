package lapr.project.gpsd.controller;

import java.util.List;
import lapr.project.authorization.model.UserRole;

public class AuthenticationController {

    private ApplicationGPSD m_oApp;

    public AuthenticationController() {
        this.m_oApp = ApplicationGPSD.getInstance();
    }

    public boolean doLogin(String strEmail, String strPwd) {
        return this.m_oApp.doLogin(strEmail, strPwd);
    }

    public List<UserRole> getUserRoles() {
        if (this.m_oApp.getPresentSession().isLoggedIn()) {
            return this.m_oApp.getPresentSession().getUserRoles();
        }
        return null;
    }

    public void doLogout() {
        this.m_oApp.doLogout();
    }
}
