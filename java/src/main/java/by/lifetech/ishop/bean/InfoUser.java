package by.lifetech.ishop.bean;

import java.util.Date;

public class InfoUser {
    private String login;
    private String name;
    private String surname;
    private String email;

    public InfoUser(String login, String name, String surname, String email) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.email = email;
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
}
