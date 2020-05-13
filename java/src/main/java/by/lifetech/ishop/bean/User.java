package by.lifetech.ishop.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class User implements Serializable {
    public static final long serialVersionUID = 1316369952791612931L;

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

    public String getLogin() {
        return login;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Integer getStateId() {
        return stateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return login.equals(user.login) &&
                password.equals(user.password) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(email, user.email) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(address, user.address) &&
                Objects.equals(dateOfBirth, user.dateOfBirth) &&
                stateId.equals(user.stateId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, name, surname, email, phone, address, dateOfBirth, stateId);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", stateId=" + stateId +
                '}';
    }
}
