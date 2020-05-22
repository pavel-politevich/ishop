package by.lifetech.ishop.controller.command.impl;

import by.lifetech.ishop.bean.AuthorizedUser;
import by.lifetech.ishop.controller.command.Command;
import by.lifetech.ishop.service.OrderService;
import by.lifetech.ishop.service.exception.ServiceException;
import by.lifetech.ishop.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddToCartCommand implements Command {

    private static final String REDIRECT_COMMAND = "Controller?command=go_to_catalog";
    private static final String REDIRECT_LOGIN_PAGE = "Controller?command=go_to_login";
    private static final String LAST_REQUEST_ATTR = "lastRequest";
    private static final String ITEM_ID_REQUEST_ATTR = "itemId";
    private static final String ITEM_COUNT_REQUEST_ATTR = "count";
    private static final String USER_SESSION_ATTR = "user";
    private static final String ORDER_ID_SESSION_ATTR = "orderId";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        HttpSession session = req.getSession(true);
        int orderId;
        int count;
        int itemId;

        if (session.getAttribute(USER_SESSION_ATTR) == null) {
            resp.sendRedirect(REDIRECT_LOGIN_PAGE);
            return;
        }

        try {
            AuthorizedUser user = (AuthorizedUser) session.getAttribute(USER_SESSION_ATTR);

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            OrderService orderService = serviceFactory.getOrderService();

            itemId = Integer.parseInt(req.getParameter(ITEM_ID_REQUEST_ATTR));
            count = Integer.parseInt(req.getParameter(ITEM_COUNT_REQUEST_ATTR));

            if (session.getAttribute(ORDER_ID_SESSION_ATTR) == null) {
                orderId = orderService.createEmptyOrder(user.getUserId());
                session.setAttribute(ORDER_ID_SESSION_ATTR, orderId);
            }
            else {
                orderId = (int) session.getAttribute(ORDER_ID_SESSION_ATTR);
            }

            orderService.addItem(orderId,itemId,count);

            if (session.getAttribute(LAST_REQUEST_ATTR) != null)
            {
                resp.sendRedirect(session.getAttribute(LAST_REQUEST_ATTR).toString());
            }
            else {
                resp.sendRedirect(REDIRECT_COMMAND);
            }
        } catch (ServiceException | NumberFormatException e) {
            resp.sendRedirect(REDIRECT_COMMAND);
        }


    }
}
