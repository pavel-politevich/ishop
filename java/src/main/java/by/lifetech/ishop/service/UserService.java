package by.lifetech.ishop.service;

import by.lifetech.ishop.bean.AuthorizedUser;
import by.lifetech.ishop.service.exception.ServiceException;

import java.util.Date;

public interface UserService {
    AuthorizedUser signIn(String login, byte[] password) throws ServiceException;
    boolean registration(String login, byte[] password, String name, String surname, String email, String phone, String address, Date birthDate, int roleId) throws ServiceException;
}
