package by.lifetech.ishop.controller.command.impl;

import by.lifetech.ishop.bean.AuthorizedUser;
import by.lifetech.ishop.controller.command.Command;
import by.lifetech.ishop.service.ItemService;
import by.lifetech.ishop.service.exception.ServiceException;
import by.lifetech.ishop.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddReviewCommand implements Command {

    private static final String USER_SESSION_ATTR = "user";
    private static final String REDIRECT_LOGIN_PAGE = "Controller?command=go_to_login";
    private static final String ITEM_ID_REQUEST_ATTR = "itemId";
    private static final String RATE_REQUEST_ATTR = "rating";
    private static final String COMMENT_REQUEST_ATTR = "comment";
    private static final String REDIRECT_COMMAND = "Controller?command=go_to_catalog";
    private static final String REDIRECT_COMMAND_ITEM = "Controller?command=show_item&id=";
    private static final String GET_PARAM_ADD_REVIEW = "&add_review=";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        HttpSession session = req.getSession(true);
        int itemId;
        byte rate = 0;
        String comment;

        if (session.getAttribute(USER_SESSION_ATTR) == null) {
            resp.sendRedirect(REDIRECT_LOGIN_PAGE);
            return;
        }

        try {
            AuthorizedUser user = (AuthorizedUser) session.getAttribute(USER_SESSION_ATTR);
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            ItemService itemService = serviceFactory.getItemService();

            itemId = Integer.parseInt(req.getParameter(ITEM_ID_REQUEST_ATTR));
            if (req.getParameter(RATE_REQUEST_ATTR) != null) {
                rate = Byte.parseByte(req.getParameter(RATE_REQUEST_ATTR));
            }

            comment = req.getParameter(COMMENT_REQUEST_ATTR);

            boolean result = itemService.addItemReview(user.getUserId(), itemId, rate, comment);

            resp.sendRedirect(REDIRECT_COMMAND_ITEM + itemId + GET_PARAM_ADD_REVIEW + result);

        } catch (ServiceException | NumberFormatException e) {
            resp.sendRedirect(REDIRECT_COMMAND);
        }
    }
}
