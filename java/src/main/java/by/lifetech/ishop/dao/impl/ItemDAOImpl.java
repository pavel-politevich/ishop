package by.lifetech.ishop.dao.impl;

import by.lifetech.ishop.dao.ConnectionPool;
import by.lifetech.ishop.dao.ConnectionPoolException;
import by.lifetech.ishop.dao.ItemDAO;
import by.lifetech.ishop.dao.exception.DAOException;

import java.math.BigDecimal;
import java.sql.*;

public class ItemDAOImpl implements ItemDAO {

    private static ConnectionPool connectionPool = ConnectionPool.getInstance();

    public ItemDAOImpl() throws DAOException {
        try {
            connectionPool.initPoolData();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Error while initialize Connection pool", e);
        }
    }

    @Override
    public int addItem(int categoryId, String nameFull, String nameShort, String description, String manufacturer, BigDecimal cost, int stateId, int count) throws DAOException {
        CallableStatement cs = null;
        Connection con = null;
        //final String INSERT_ITEM_SQL = "insert into ishop.items(id_category, name_short, name_full, description, manufacturer, price, state_id, count) values(?,?,?,?,?,?,?,?)";
        final String INSERT_ITEM_SQL = "{call ishop.add_new_item(?,?,?,?,?,?,?,?,?)}";

        try {
            con = connectionPool.takeConnection();
            cs = con.prepareCall(INSERT_ITEM_SQL);
            cs.setInt("id_category",categoryId);
            cs.setString("name_short",nameShort);
            cs.setString("name_full",nameFull);
            cs.setString("description",description);
            cs.setString("manufacturer",manufacturer);
            cs.setBigDecimal("price",cost);
            cs.setInt("state_id",stateId);
            cs.setInt("count",count);

            cs.registerOutParameter("item_id", Types.INTEGER);

            cs.execute();

            return cs.getInt("item_id");

        } catch (ConnectionPoolException e) {
            throw new DAOException("Error in Connection pool while adding new Item", e);
        } catch (SQLException e) {
            throw new DAOException("Error while adding new Item", e);
        } finally {
            connectionPool.closeConnection(con, cs);
        }
    }

    @Override
    public void deleteItem(int itemId) {

    }
}
