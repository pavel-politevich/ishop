package by.lifetech.ishop.dao;

import by.lifetech.ishop.bean.Item;
import by.lifetech.ishop.dao.exception.DAOException;

import java.math.BigDecimal;
import java.util.List;

public interface ItemDAO {
    int addItem(int categoryId, String nameFull, String nameShort, String description, String manufacturer, BigDecimal cost, int stateId, int count) throws DAOException;

    void setItemStatus(int itemId, int itemStateId) throws DAOException;

    void updateItemBalance(int itemId, int delta) throws DAOException;

    List<Item> findItemsByCategory(int categoryID) throws DAOException;
}
