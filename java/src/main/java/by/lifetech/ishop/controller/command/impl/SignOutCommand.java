package by.lifetech.ishop.controller.command.impl;

import by.lifetech.ishop.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignOutCommand implements Command {

    private static final String REDIRECT_COMMAND = "Controller?command=go_to_main";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession(true).removeAttribute("user");
        try {
            resp.sendRedirect(REDIRECT_COMMAND);
        } catch (IOException e) {
            // log
        }
    }
}
