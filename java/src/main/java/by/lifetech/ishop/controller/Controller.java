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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Controller extends HttpServlet {

    public Controller() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        processRequest(req, resp);
        return;
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");

        if (command.equals("signIn")) {
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

        else if (command.equals("registration")) {

            String login = req.getParameter("login");
            String password = req.getParameter("password");
            String username = req.getParameter("username");
            String surname = req.getParameter("surname");
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");
            String address = req.getParameter("address");
            String dateOfBirth = req.getParameter("dateOfBirth");

            SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");

            try {
                Date dateBirth = sdf.parse(dateOfBirth);

                ServiceFactory serviceFactory = ServiceFactory.getInstance();
                UserService userService = serviceFactory.getUserService();

                userService.registration(login,password.getBytes(),username,surname,email,phone,address,dateBirth);

                req.setAttribute("operation", new String("registration"));

            } catch (ParseException e) {
                e.printStackTrace();
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }


        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/mainPage.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
