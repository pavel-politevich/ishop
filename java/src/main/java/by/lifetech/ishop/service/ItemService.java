package by.lifetech.ishop.service;

import by.lifetech.ishop.bean.Category;
import by.lifetech.ishop.service.exception.ServiceException;

import java.util.List;

public interface ItemService {
    List<Category> getCategories() throws ServiceException;
}
