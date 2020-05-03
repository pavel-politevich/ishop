package by.lifetech.ishop.service;

import by.lifetech.ishop.bean.AuthorizedUser;
import by.lifetech.ishop.service.exception.ServiceException;

import java.util.Date;

public interface UserService {
    AuthorizedUser signIn(String login, byte[] password) throws ServiceException;
    void registration(String login, byte[] password, String name, String surname, String email, String phone, String address, Date birthDate) throws ServiceException;
}