package by.lifetech.ishop.bean;

public class AuthorizedUser {
    private String name;
    private String surname;
    private String email;

    public AuthorizedUser(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public AuthorizedUser () {}

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }
}
