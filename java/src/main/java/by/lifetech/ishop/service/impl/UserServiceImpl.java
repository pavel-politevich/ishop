package by.lifetech.ishop.service.impl;

import by.lifetech.ishop.bean.AuthorizedUser;
import by.lifetech.ishop.dao.UserDAO;
import by.lifetech.ishop.dao.exception.DAOException;
import by.lifetech.ishop.dao.exception.DAOUserAlreadyExistsException;
import by.lifetech.ishop.dao.factory.DAOFactory;
import by.lifetech.ishop.service.UserService;
import by.lifetech.ishop.service.exception.ServiceException;
import by.lifetech.ishop.service.exception.ServiceUserAlreadyExistsException;

import java.util.Date;

public class UserServiceImpl implements UserService {
    @Override
    public AuthorizedUser signIn(String login, byte[] password) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();

        if (login.equals("") || password.equals("")) {
            throw new ServiceException("Error while signIn User. Login or Password is null");
        }

        UserDAO userDAO = factory.getUserDAO();

        try {
            return userDAO.signIn(login, password);
        } catch (DAOException e) {
            throw new ServiceException("Error while signIn User", e);
        }
    }

    @Override
    public boolean registration(String login, byte[] password, String name, String surname, String email, String phone, String address, Date birthDate, int roleId) throws ServiceException {

        if (login.equals("") || password.equals("") || email.equals("") || birthDate.equals("")) {
            return false;
        }

        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();

        try {
            userDAO.registration(login, password, name, surname, email, phone, address, birthDate, roleId);
        }
        catch (DAOUserAlreadyExistsException e) {
            throw new ServiceUserAlreadyExistsException("User already exists", e);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while registration User", e);
        }

        return true;
    }
}
