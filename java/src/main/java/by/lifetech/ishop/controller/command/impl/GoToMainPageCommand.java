package by.lifetech.ishop.controller.command.impl;

import by.lifetech.ishop.controller.command.Command;
import by.lifetech.ishop.service.ItemService;
import by.lifetech.ishop.service.factory.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToMainPageCommand implements Command {

    private static final String MAIN_PAGE_URI = "index.jsp";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ItemService itemService = serviceFactory.getItemService();
        HttpSession session = req.getSession(true);

        RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE_URI);

        try {
            dispatcher.forward(req, resp);
        }
        catch (ServletException e) {
            // log
        } catch (IOException e) {
            // log
        }
    }
}
