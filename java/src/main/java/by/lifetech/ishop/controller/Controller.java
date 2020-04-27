package by.lifetech.ishop.controller;

import by.lifetech.ishop.bean.AuthorizedUser;
import by.lifetech.ishop.service.UserService;
import by.lifetech.ishop.service.exception.ServiceException;
import by.lifetech.ishop.service.factory.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    public Controller() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);

        String command = req.getParameter("command");

        if (command == "signIn") {
            String login = req.getParameter("username");
            byte[] password = req.getParameter("password").getBytes();

            AuthorizedUser authorizedUser = null;

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            UserService userService = serviceFactory.getUserService();
            try {
                authorizedUser = userService.signIn(login, password);
            } catch (ServiceException e) {
                e.printStackTrace();
            }

            req.setAttribute("user", authorizedUser);
        }


        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/mainPage.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
