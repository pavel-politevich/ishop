package by.lifetech.ishop.controller.command.impl;

import by.lifetech.ishop.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeLocaleCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession(true).setAttribute("local", req.getParameter("local"));

        try {
            HttpSession session = req.getSession();
            if (session.getAttribute("lastRequest") != null)
            {
                resp.sendRedirect(session.getAttribute("lastRequest").toString());
            }
            else {
                resp.sendRedirect("/");
            }
        } catch (IOException e) {
            // log
        }
    }
}
