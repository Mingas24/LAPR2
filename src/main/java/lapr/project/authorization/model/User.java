package lapr.project.authorization.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class User {

    private String m_strName;
    private String m_strEmail;
    private String m_strPassword; // Shouldn't save the password in "plain text",
    private Set<UserRole> m_lstRoles = new HashSet<UserRole>();

    public User(String strName, String strEmail, String strPassword) {
        if ((strName == null) || (strEmail == null) || (strPassword == null)
                || (strName.isEmpty()) || (strEmail.isEmpty()) || (strPassword.isEmpty())) {
            throw new IllegalArgumentException("None of the arguments are null or empty.");
        }

        this.m_strName = strName;
        this.m_strEmail = strEmail;
        this.m_strPassword = strPassword;

    }

    public String getId() {
        return this.m_strEmail;
    }

    public String getName() {
        return this.m_strName;
    }

    public String getEmail() {
        return this.m_strEmail;
    }

    public boolean hasId(String strId) {
        return this.m_strEmail.equals(strId);
    }

    public boolean hasPassword(String strPwd) {
        return this.m_strPassword.equals(strPwd);
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

    public boolean hasRole(UserRole role) {
        return this.m_lstRoles.contains(role);
    }

    public boolean hasRole(String strRole) {
        for (UserRole role : this.m_lstRoles) {
            if (role.hasId(strRole)) {
                return true;
            }
        }
        return false;
    }

    public List<UserRole> getRoles() {
        List<UserRole> list = new ArrayList<>();
        for (UserRole role : this.m_lstRoles) {
            list.add(role);
        }
        return list;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.m_strEmail);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        // Inspired in https://www.sitepoint.com/implement-javas-equals-method-correctly/

        // self check
        if (this == o) {
            return true;
        }
        // null check
        if (o == null) {
            return false;
        }
        // type check and cast
        if (getClass() != o.getClass()) {
            return false;
        }
        // field comparison
        User obj = (User) o;
        return Objects.equals(m_strEmail, obj.m_strEmail);
    }

    @Override
    public String toString() {
        return String.format("%s - %s", this.m_strName, this.m_strEmail);
    }
}
