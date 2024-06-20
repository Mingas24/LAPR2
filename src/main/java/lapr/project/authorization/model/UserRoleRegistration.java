package lapr.project.authorization.model;

import java.util.HashSet;
import java.util.Set;

public class UserRoleRegistration {

    private Set<UserRole> m_lstRoles = new HashSet<UserRole>();

    public UserRole newUserRole(String strRole) {
        return new UserRole(strRole);
    }

    public UserRole newUserRole(String strRole, String strDescription) {
        return new UserRole(strRole, strDescription);
    }

    public boolean addRole(UserRole role) {
        if (role != null) {
            return this.m_lstRoles.add(role);
        }
        return false;
    }

    public boolean removeRole(UserRole role) {
        if (role != null) {
            return this.m_lstRoles.remove(role);
        }
        return false;
    }

    public UserRole searchRole(String strRole) {
        for (UserRole role : this.m_lstRoles) {
            if (role.hasId(strRole)) {
                return role;
            }
        }
        return null;
    }

    public boolean hasRole(String strRole) {
        UserRole role = searchRole(strRole);
        if (role != null) {
            return this.m_lstRoles.contains(role);
        }
        return false;
    }

    public boolean hasRole(UserRole role) {
        return this.m_lstRoles.contains(role);
    }
}
