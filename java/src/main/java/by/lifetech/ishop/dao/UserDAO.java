package by.lifetech.ishop.dao;

import by.lifetech.ishop.bean.AuthorizedUser;
import by.lifetech.ishop.dao.exception.DAOException;

import java.util.Date;

public interface UserDAO {
    void registration(String login, byte[] password, String name, String surname, String email, String phone, String address, Date birthDate, int roleId) throws DAOException;
    AuthorizedUser signIn (String login, byte[] password) throws DAOException;
}
