package by.lifetech.ishop.controller.command.impl;

import by.lifetech.ishop.controller.command.Command;
import by.lifetech.ishop.service.UserService;
import by.lifetech.ishop.service.exception.ServiceException;
import by.lifetech.ishop.service.factory.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistrationCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

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

            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/mainPage.jsp");
            dispatcher.forward(req, resp);

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
