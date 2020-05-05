package by.lifetech.ishop.bean;

import java.util.Date;
import java.util.Objects;

public class InfoUser {
    public static final long serialVersionUID = 7185969937587806156L;

    private String login;
    private String name;
    private String surname;
    private String email;
    private  String state;

    public InfoUser(String login, String name, String surname, String email, String state) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfoUser infoUser = (InfoUser) o;
        return login.equals(infoUser.login) &&
                Objects.equals(name, infoUser.name) &&
                Objects.equals(surname, infoUser.surname) &&
                Objects.equals(email, infoUser.email) &&
                state.equals(infoUser.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, name, surname, email, state);
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

    public String getLogin() {
        return login;
    }

    public String getState() {
        return state;
    }
}
