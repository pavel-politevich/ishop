package by.lifetech.ishop.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class User implements Serializable {
    public static final long serialVersionUID = 1316369952791612931L;

    private int userId;
    private String login;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;
    private Date dateOfBirth;
    private String state;
    private String roleName;

    public User(int userId, String login, String name, String surname, String email, String phone, String address, Date dateOfBirth, String state, String roleName) {
        this.userId = userId;
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.state = state;
        this.roleName = roleName;
    }

    public User() {    }

    public int getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
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

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getState() {
        return state;
    }

    public String getRoleName() {
        return roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                login.equals(user.login) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                email.equals(user.email) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(address, user.address) &&
                Objects.equals(dateOfBirth, user.dateOfBirth) &&
                Objects.equals(state, user.state) &&
                Objects.equals(roleName, user.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, login, name, surname, email, phone, address, dateOfBirth, state, roleName);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", state='" + state + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
