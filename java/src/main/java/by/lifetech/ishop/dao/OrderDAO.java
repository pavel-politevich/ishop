package by.lifetech.ishop.dao;

import by.lifetech.ishop.bean.Order;
import by.lifetech.ishop.dao.exception.DAOException;

import java.util.Map;

public interface OrderDAO {
    int createEmptyOrder(int userId) throws DAOException;
    void addItem(int orderId, int itemId, int count) throws DAOException;
    void deleteItem(int orderId, int itemId) throws DAOException;
    Order getOrder(int orderId) throws DAOException;
    int getCurrentOrderId(int userId) throws DAOException;
    Map<Integer, String> getPaymentTypes() throws DAOException;
    void confirmOrder(Order order) throws DAOException;
}
