package by.lifetech.ishop.service.impl;

import by.lifetech.ishop.bean.Category;
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
}
