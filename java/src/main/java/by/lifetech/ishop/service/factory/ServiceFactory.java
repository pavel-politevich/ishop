package by.lifetech.ishop.service.factory;

import by.lifetech.ishop.service.ItemService;
import by.lifetech.ishop.service.UserService;
import by.lifetech.ishop.service.impl.ItemServiceImpl;
import by.lifetech.ishop.service.impl.UserServiceImpl;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final UserService userService = new UserServiceImpl();
    private final ItemService itemService = new ItemServiceImpl();

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public ItemService getItemService() {
        return itemService;
    }
}
