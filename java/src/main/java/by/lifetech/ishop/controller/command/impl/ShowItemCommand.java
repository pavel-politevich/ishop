package by.lifetech.ishop.controller.command.impl;

import by.lifetech.ishop.controller.command.Command;
import by.lifetech.ishop.service.ItemService;
import by.lifetech.ishop.service.exception.ServiceException;
import by.lifetech.ishop.service.factory.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowItemCommand implements Command {

    private static final String REQUEST_PARAMETER_ITEM_ID = "id";
    private static final String ITEM_ATTR = "item";
    private static final String REDIRECT_COMMAND_ERROR = "Controller?command=go_to_catalog";
    private static final String ITEM_PAGE_URI = "WEB-INF/jsp/itemInfo.jsp";
    private static final String REVIEW_LIST_ATTR = "reviewList";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ItemService itemService = serviceFactory.getItemService();

        try {
            int itemId = Integer.parseInt(req.getParameter(REQUEST_PARAMETER_ITEM_ID));
            itemService.getItem(itemId);

            if (itemService.getItemReviews(itemId) != null) {
                req.setAttribute(REVIEW_LIST_ATTR, itemService.getItemReviews(itemId));
            }

            req.setAttribute(ITEM_ATTR, itemService.getItem(itemId));
            RequestDispatcher dispatcher = req.getRequestDispatcher(ITEM_PAGE_URI);
            dispatcher.forward(req, resp);

        } catch (ServiceException | NumberFormatException e) {
            resp.sendRedirect(REDIRECT_COMMAND_ERROR);
        }
    }
}
