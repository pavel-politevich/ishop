package by.lifetech.ishop.controller.command.impl;

import by.lifetech.ishop.controller.command.Command;
import by.lifetech.ishop.service.UserService;
import by.lifetech.ishop.service.exception.ServiceException;
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
    private static final String REDIRECT_COMMAND = "Controller?command=go_to_main&register=success";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {

        String login = req.getParameter(REQUEST_PARAMETER_LOGIN);
        String password = req.getParameter(REQUEST_PARAMETER_PASSWORD);
        String username = req.getParameter(REQUEST_PARAMETER_USERNAME);
        String surname = req.getParameter(REQUEST_PARAMETER_SURNAME);
        String email = req.getParameter(REQUEST_PARAMETER_EMAIL);
        String phone = req.getParameter(REQUEST_PARAMETER_PHONE);
        String address = req.getParameter(REQUEST_PARAMETER_ADDRESS);
        String dateOfBirth = req.getParameter(REQUEST_PARAMETER_DATE_OF_BIRTH);

        int roleId = 2;

        SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_PATTERN);

        try {
            Date dateBirth = sdf.parse(dateOfBirth);

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            UserService userService = serviceFactory.getUserService();

            userService.registration(login,password.getBytes(),username,surname,email,phone,address,dateBirth,roleId);

            resp.sendRedirect(REDIRECT_COMMAND);

        } catch (ParseException e) {
            // log
        } catch (ServiceException e) {
            // log (unsuccessful registration)
        } catch (IOException e) {
            // log
        }

    }
}
