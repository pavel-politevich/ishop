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
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {

        String login = req.getParameter("username");
        byte[] password = req.getParameter("password").getBytes();

        AuthorizedUser authorizedUser = null;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        try {
            authorizedUser = userService.signIn(login, password);

            req.setAttribute("user", authorizedUser);

            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/mainPage.jsp");
            dispatcher.forward(req, resp);

        } catch (ServiceException e) {
            // log
        } catch (ServletException e) {
            // log
        } catch (IOException e) {
            // log
        }

    }
}
