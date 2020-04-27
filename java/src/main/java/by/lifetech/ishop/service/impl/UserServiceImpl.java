package by.lifetech.ishop.service.impl;

import by.lifetech.ishop.bean.AuthorizedUser;
import by.lifetech.ishop.dao.UserDAO;
import by.lifetech.ishop.dao.exception.DAOException;
import by.lifetech.ishop.dao.factory.DAOFactory;
import by.lifetech.ishop.dao.impl.UserDAOImpl;
import by.lifetech.ishop.service.UserService;
import by.lifetech.ishop.service.exception.ServiceException;

import java.util.Date;

public class UserServiceImpl implements UserService {
    @Override
    public AuthorizedUser signIn(String login, byte[] password) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();

        if (login == null || password == null) {
            return null;
        }

        UserDAO userDAO = null;
        try {
            userDAO = new UserDAOImpl();
            return userDAO.signIn(login, password);
        } catch (DAOException e) {
            throw new ServiceException("Error while signIn User", e);
        }
    }

    @Override
    public void registration(String login, byte[] password, String name, String surname, String email, String phone, String address, Date birthDate) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();

        UserDAO userDAO = null;
        try {
            userDAO = new UserDAOImpl();
            userDAO.registration(login, password, name, surname, email, phone, address, birthDate);
        } catch (DAOException e) {
            throw new ServiceException("Error while registration User", e);
        }
    }
}
