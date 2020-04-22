package by.lifetech.ishop.dao.factory;

import by.lifetech.ishop.dao.ItemDAO;
import by.lifetech.ishop.dao.UserDAO;
import by.lifetech.ishop.dao.exception.DAOException;
import by.lifetech.ishop.dao.impl.ItemDAOImpl;
import by.lifetech.ishop.dao.impl.UserDAOImpl;

public final class DAOFactory {
    private static DAOFactory instance;

    static {
        try {
            instance = new DAOFactory();
        } catch (DAOException e) {
            //e.printStackTrace();
        }
    }

    private final UserDAO sqlUserImpl = new UserDAOImpl();
    private final ItemDAO sqlItemImpl = new ItemDAOImpl();

    private DAOFactory() throws DAOException {}

    public static DAOFactory getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return sqlUserImpl;
    }
    public ItemDAO getItemDAO() { return  sqlItemImpl; }



}
