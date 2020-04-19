package by.lifetech.ishop.dao;

import java.util.Date;
import java.util.List;

import by.lifetech.ishop.bean.AuthorizedUser;
import by.lifetech.ishop.bean.InfoUser;
import by.lifetech.ishop.bean.User;
import by.lifetech.ishop.dao.exception.DAOException;

public interface UserDAO {
    void registration(String login, byte[] password, String name, String surname, String email, String phone, String address, Date birthDate) throws DAOException;
    void registration(User user);
    AuthorizedUser signIn (String login, byte[] password) throws DAOException;
    User getUser(int userId);
    List<InfoUser> findUsersByState(int stateId) throws DAOException;
}
