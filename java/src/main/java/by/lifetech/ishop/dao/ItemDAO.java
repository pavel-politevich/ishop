package by.lifetech.ishop.dao;

import by.lifetech.ishop.dao.exception.DAOException;

import java.math.BigDecimal;

public interface ItemDAO {
    int addItem(int categoryId, String nameFull, String nameShort, String description, String manufacturer, BigDecimal cost, int stateId, int count) throws DAOException;
    void  deleteItem(int itemId);
}
