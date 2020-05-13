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

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    private final CommandProvider provider = new CommandProvider();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding(CHARACTER_ENCODING);
        processGetRequest(req, resp);
        return;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding(CHARACTER_ENCODING);
        processPostRequest(req, resp);
        return;
    }

    private void processGetRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String commandName;
        Command executionCommand;

        commandName = req.getParameter(REQUEST_PARAMETER_COMMAND);

        executionCommand = provider.getCommand(commandName);
        executionCommand.execute(req,resp);

        req.getSession(true).setAttribute("lastRequest", req.getRequestURI() + "?" + req.getQueryString());
    }

    private void processPostRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String commandName;
        Command executionCommand;

        commandName = req.getParameter(REQUEST_PARAMETER_COMMAND);
        executionCommand = provider.getCommand(commandName);

        executionCommand.execute(req,resp);
    }

}
