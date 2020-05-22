package by.lifetech.ishop.service.impl;

import by.lifetech.ishop.bean.Category;
import by.lifetech.ishop.bean.Item;
import by.lifetech.ishop.bean.Review;
import by.lifetech.ishop.dao.ItemDAO;
import by.lifetech.ishop.dao.exception.DAOException;
import by.lifetech.ishop.dao.factory.DAOFactory;
import by.lifetech.ishop.service.ItemService;
import by.lifetech.ishop.service.exception.ServiceException;

import java.util.List;

public class ItemServiceImpl implements ItemService {
    @Override
    public List<Category> getCategories() throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        ItemDAO itemDAO = factory.getItemDAO();

        try {
            return itemDAO.getCategories();
        } catch (DAOException e) {
            throw new ServiceException("Error while find all categories", e);
        }
    }

    @Override
    public List<Item> getItemsByCategory(int categoryId) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        ItemDAO itemDAO = factory.getItemDAO();

        if (categoryId < 1) {
            return null;
        }

        try {
            return itemDAO.findItemsByCategory(categoryId);
        } catch (DAOException e) {
            throw new ServiceException("Error while find Items by category", e);
        }
    }

    @Override
    public Item getItem(int itemId) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        ItemDAO itemDAO = factory.getItemDAO();

        if (itemId < 1) {
            return null;
        }

        try {
            return itemDAO.getItem(itemId);
        } catch (DAOException e) {
            throw new ServiceException("Error while find Item by ID", e);
        }
    }

    @Override
    public boolean addItemReview(int userId, int itemId, byte rate, String comment) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        ItemDAO itemDAO = factory.getItemDAO();

        if (userId < 1 || itemId < 1 || rate < 1 || rate > 5) {
            return false;
        }

        try {
            itemDAO.addItemReview(userId, itemId, rate, comment);
            return true;
        } catch (DAOException e) {
            throw new ServiceException("Error while adding Review", e);
        }
    }

    @Override
    public List<Review> getItemReviews(int itemId) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        ItemDAO itemDAO = factory.getItemDAO();

        if (itemId < 1) {
            return null;
        }

        try {
            return itemDAO.getItemReviews(itemId);
        } catch (DAOException e) {
            throw new ServiceException("Error while get last Reviews", e);
        }
    }

}
