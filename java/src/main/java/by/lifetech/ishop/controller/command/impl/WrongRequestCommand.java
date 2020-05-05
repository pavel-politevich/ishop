package by.lifetech.ishop.controller.command.impl;

import by.lifetech.ishop.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WrongRequestCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/errorPage.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            // log
        } catch (IOException e) {
            // log
        }
    }
}
