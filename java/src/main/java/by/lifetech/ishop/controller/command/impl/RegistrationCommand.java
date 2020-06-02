package by.lifetech.ishop.controller.command.impl;

import by.lifetech.ishop.controller.command.Command;
import by.lifetech.ishop.service.UserService;
import by.lifetech.ishop.service.exception.ServiceException;
import by.lifetech.ishop.service.exception.ServiceUserAlreadyExistsException;
import by.lifetech.ishop.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistrationCommand implements Command {

    private static final String REQUEST_PARAMETER_LOGIN = "login";
    private static final String REQUEST_PARAMETER_PASSWORD = "password";
    private static final String REQUEST_PARAMETER_USERNAME = "username";
    private static final String REQUEST_PARAMETER_SURNAME = "surname";
    private static final String REQUEST_PARAMETER_EMAIL = "email";
    private static final String REQUEST_PARAMETER_PHONE = "phone";
    private static final String REQUEST_PARAMETER_ADDRESS = "address";
    private static final String REQUEST_PARAMETER_DATE_OF_BIRTH = "dateOfBirth";
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String REDIRECT_COMMAND_SUCCESS = "Controller?command=go_to_main&register=success";
    private static final String REDIRECT_COMMAND_ERROR = "Controller?command=go_to_register&register=error";
    private static final String REDIRECT_COMMAND_ERROR_DUPLICATE = "Controller?command=go_to_register&error=unique";
    private static final int DEFAULT_ROLE_ID = 2;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String login = req.getParameter(REQUEST_PARAMETER_LOGIN);
        String password = req.getParameter(REQUEST_PARAMETER_PASSWORD);
        String username = req.getParameter(REQUEST_PARAMETER_USERNAME);
        String surname = req.getParameter(REQUEST_PARAMETER_SURNAME);
        String email = req.getParameter(REQUEST_PARAMETER_EMAIL);
        String phone = req.getParameter(REQUEST_PARAMETER_PHONE);
        String address = req.getParameter(REQUEST_PARAMETER_ADDRESS);
        String dateOfBirth = req.getParameter(REQUEST_PARAMETER_DATE_OF_BIRTH);

        // Default role for user
        int roleId = DEFAULT_ROLE_ID;

        SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_PATTERN);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        try {
            Date dateBirth = sdf.parse(dateOfBirth);
            userService.registration(login, password.getBytes(), username, surname, email, phone, address, dateBirth, roleId);

            resp.sendRedirect(REDIRECT_COMMAND_SUCCESS);

        } catch (ParseException e) {
            resp.sendRedirect(REDIRECT_COMMAND_ERROR);
        } catch (ServiceUserAlreadyExistsException e) {
            resp.sendRedirect(REDIRECT_COMMAND_ERROR_DUPLICATE);
        } catch (ServiceException e) {
            // log (unsuccessful registration)
            resp.sendRedirect(REDIRECT_COMMAND_ERROR);
        }

    }
}
