package by.lifetech.ishop.bean;

import java.util.Objects;

public class AuthorizedUser {
    public static final long serialVersionUID = 1614365145795883655L;

    private String name;
    private String surname;
    private String email;



    private String roleName;

    public AuthorizedUser(String name, String surname, String email, String roleName) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.roleName = roleName;
    }

    public AuthorizedUser () {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorizedUser that = (AuthorizedUser) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(email, that.email) &&
                Objects.equals(roleName, that.roleName);
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, surname, email, roleName);
    }


    @Override
    public String toString() {
        return "AuthorizedUser{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getRoleName() { return roleName; }
}
