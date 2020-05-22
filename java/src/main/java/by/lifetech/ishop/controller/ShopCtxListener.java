package by.lifetech.ishop.controller;

import by.lifetech.ishop.dao.impl.connection.ConnectionPool;
import by.lifetech.ishop.dao.impl.connection.ConnectionPoolException;
import by.lifetech.ishop.service.ItemService;
import by.lifetech.ishop.service.OrderService;
import by.lifetech.ishop.service.exception.ServiceException;
import by.lifetech.ishop.service.factory.ServiceFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ShopCtxListener implements ServletContextListener {

    private static final String CATEGORY_LIST_ATTR = "categoryList";
    private static final String PAYMENT_TYPES_MAP_ATTR = "paymentTypesMap";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ItemService itemService = serviceFactory.getItemService();
        OrderService orderService = serviceFactory.getOrderService();

        try {
            ConnectionPool.getInstance().initPoolData();
            servletContext.setAttribute(CATEGORY_LIST_ATTR, itemService.getCategories());
            servletContext.setAttribute(PAYMENT_TYPES_MAP_ATTR, orderService.getPaymentTypes());
        } catch (ServiceException | ConnectionPoolException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().dispose();
    }
}
