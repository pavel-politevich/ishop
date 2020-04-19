package by.lifetech.ishop.bean;

import java.util.Date;

public class User {
    private String login;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;
    private Date dateOfBirth;
    private Integer stateId;

    public User(String login, String password, String name, String surname, String email, String phone, String address, Date dateOfBirth, Integer stateId) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.stateId = stateId;
    }

    public User() {}

    public String getName() {
        return name;
    }
}
