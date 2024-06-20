package lapr.project.authorization.model;

import java.util.Objects;

public class UserRole {

    private String role;
    private String description;

    public UserRole(String strRole) {
        if ((strRole == null) || (strRole.isEmpty())) {
            throw new IllegalArgumentException("The argument can't be null or empty.");
        }

        this.role = strRole;
        this.description = strRole;
    }

    public UserRole(String strRole, String strDescription) {
        if ((strRole == null) || (strDescription == null) || (strRole.isEmpty()) || (strDescription.isEmpty())) {
            throw new IllegalArgumentException("None of the arguments can be null or empty.");
        }

        this.role = strRole;
        this.description = strDescription;
    }

    public String getRole() {
        return this.role;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean hasId(String strId) {
        return this.role.equals(strId);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.role);
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
        UserRole obj = (UserRole) o;
        return Objects.equals(role, obj.role);
    }

    @Override
    public String toString() {
        return String.format("%s", this.role);
    }
}
