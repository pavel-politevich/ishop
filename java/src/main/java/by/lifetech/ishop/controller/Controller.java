package by.lifetech.ishop.controller;

import by.lifetech.ishop.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private static final String REQUEST_PARAMETER_COMMAND = "command";
    private static final String CHARACTER_ENCODING = "utf-8";

    public Controller() {
        super();
    }

    private final CommandProvider provider = new CommandProvider();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding(CHARACTER_ENCODING);
        processRequest(req, resp);
        return;
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String commandName;
        Command executionCommand;

        commandName = req.getParameter(REQUEST_PARAMETER_COMMAND);
        executionCommand = provider.getCommand(commandName);
        executionCommand.execute(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
