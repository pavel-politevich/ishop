package by.lifetech.ishop.dao;

import by.lifetech.ishop.bean.Category;
import by.lifetech.ishop.bean.Item;
import by.lifetech.ishop.bean.Review;
import by.lifetech.ishop.dao.exception.DAOException;

import java.util.List;

public interface ItemDAO {
    List<Item> findItemsByCategory(int categoryID) throws DAOException;
    List<Category> getCategories() throws DAOException;
    Item getItem(int itemId) throws DAOException;
    void addItemReview(int userId, int itemId, byte rate, String comment) throws DAOException;
    List<Review> getItemReviews(int itemId) throws DAOException;
}
