package by.lifetech.ishop.service;

import by.lifetech.ishop.bean.Order;
import by.lifetech.ishop.service.exception.ServiceException;

import java.util.Map;

public interface OrderService {
    int createEmptyOrder(int userId) throws ServiceException;
    boolean addItem(int orderId, int itemId, int count) throws ServiceException;
    boolean deleteItem(int orderId, int itemId) throws ServiceException;
    Order getOrder(int orderId) throws ServiceException;
    int getCurrentOrderId(int userId) throws ServiceException;
    Map<Integer, String> getPaymentTypes() throws ServiceException;
    void confirmOrder(Order order) throws ServiceException;
}
