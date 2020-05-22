package by.lifetech.ishop.service;

import by.lifetech.ishop.bean.Category;
import by.lifetech.ishop.bean.Item;
import by.lifetech.ishop.bean.Review;
import by.lifetech.ishop.service.exception.ServiceException;

import java.util.List;

public interface ItemService {
    List<Category> getCategories() throws ServiceException;
    List<Item> getItemsByCategory(int categoryId) throws ServiceException;
    Item getItem(int itemId) throws ServiceException;
    boolean addItemReview(int userId, int itemId, byte rate, String comment) throws ServiceException;
    List<Review> getItemReviews(int itemId) throws ServiceException;
}
