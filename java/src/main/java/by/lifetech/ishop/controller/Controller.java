package by.lifetech.ishop.controller;

import by.lifetech.ishop.controller.command.Command;
import by.lifetech.ishop.dao.impl.connection.ConnectionPool;

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
        ConnectionPool.getInstance().dispose();
        super.destroy();
    }

    @Override
    public void init() throws ServletException {

        ConnectionPool.getInstance().initPoolData();
        super.init();
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

        if (req.getQueryString() != null) {
            req.getSession(true).setAttribute("lastRequest", req.getRequestURI() + "?" + req.getQueryString());
            //System.out.println(req.getRequestURI() + "?" + req.getQueryString());
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding(CHARACTER_ENCODING);
        processRequest(req, resp);
        return;
    }
}
