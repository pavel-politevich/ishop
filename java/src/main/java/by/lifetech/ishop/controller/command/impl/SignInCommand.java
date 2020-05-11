package by.lifetech.ishop.controller.command.impl;

import by.lifetech.ishop.bean.AuthorizedUser;
import by.lifetech.ishop.controller.command.Command;
import by.lifetech.ishop.service.UserService;
import by.lifetech.ishop.service.exception.ServiceException;
import by.lifetech.ishop.service.factory.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInCommand implements Command {

    private static final String REQUEST_PARAMETER_USERNAME = "username";
    private static final String REQUEST_PARAMETER_LOGIN = "password";
    private static final String REDIRECT_COMMAND_SUCCESS = "Controller?command=go_to_main&login=success";
    private static final String REDIRECT_COMMAND_ERROR = "Controller?command=go_to_login&login=fail";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {

        String login = req.getParameter(REQUEST_PARAMETER_USERNAME);
        byte[] password = req.getParameter(REQUEST_PARAMETER_LOGIN).getBytes();

        AuthorizedUser authorizedUser = null;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        try {
            authorizedUser = userService.signIn(login, password);

            if (authorizedUser != null) {
                req.getSession(true).setAttribute("user", authorizedUser);
                resp.sendRedirect(REDIRECT_COMMAND_SUCCESS);
            }
            else {
                resp.sendRedirect(REDIRECT_COMMAND_ERROR);
            }


        } catch (ServiceException e) {
            // log
        } catch (IOException e) {
            // log
        }

    }
}
