package by.lifetech.ishop.controller.command.impl;

import by.lifetech.ishop.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToRegisterPageCommand implements Command {

    private static final String REGISTRATION_PAGE_URI = "WEB-INF/jsp/registration.jsp";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher(REGISTRATION_PAGE_URI);
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            // log
        } catch (IOException e) {
            // log
        }
    }
}
