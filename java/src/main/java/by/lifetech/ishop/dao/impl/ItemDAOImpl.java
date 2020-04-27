package by.lifetech.ishop.dao.impl;

import by.lifetech.ishop.bean.Item;
import by.lifetech.ishop.dao.ConnectionPool;
import by.lifetech.ishop.dao.ConnectionPoolException;
import by.lifetech.ishop.dao.ItemDAO;
import by.lifetech.ishop.dao.exception.DAOException;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public void setItemStatus(int itemId, int itemStateId) throws DAOException {
        PreparedStatement ps = null;
        Connection con = null;
        final String UPDATE_ITEM_STATUS_SQL = "update ishop.items set state_id = ? where id = ?";

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(UPDATE_ITEM_STATUS_SQL);
            ps.setInt(1, itemStateId);
            ps.setInt(2, itemId);

            ps.executeUpdate();


        } catch (ConnectionPoolException e) {
            throw new DAOException("Error in Connection pool while adding new User", e);
        } catch (SQLException e) {
            throw new DAOException("Error while adding new User", e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }

    }

    @Override
    public void updateItemBalance(int itemId, int delta) throws DAOException {
        PreparedStatement ps = null;
        Connection con = null;
        final String UPDATE_ITEM_STATUS_SQL = "update ishop.storage set count = count + ? where item_id = ?";

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(UPDATE_ITEM_STATUS_SQL);
            ps.setInt(1, delta);
            ps.setInt(2, itemId);

            ps.executeUpdate();

        } catch (ConnectionPoolException e) {
            throw new DAOException("Error in Connection pool while adding new User", e);
        } catch (SQLException e) {
            throw new DAOException("Error while adding new User", e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }
    }

    @Override
    public List<Item> findItemsByCategory(int categoryID) throws DAOException {
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;

        final String GET_ITEMS_BY_STATE = "SELECT i.ID, i.NAME_SHORT, i.NAME_FULL, i.DESCRIPTION, i.MANUFACTURER, i.PRICE, st.COUNT, dis.NAME as STATE, cat.CATEGORY_NAME, (select IFNULL(AVG(r.rate),0) from ishop.item_reviews r where r.ITEM_ID = i.ID) as RATING FROM ishop.items i join ishop.storage st on st.ITEM_ID = i.ID join ishop.dict_items_state dis on i.STATE_ID = dis.ID join ishop.category cat on i.ID_CATEGORY = cat.ID where i.ID_CATEGORY = ?";

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(GET_ITEMS_BY_STATE);
            ps.setInt(1, categoryID);

            rs = ps.executeQuery();

            if (rs == null) {
                return null;
            }

            List<Item> itemList = new ArrayList<>();

            while (rs.next()) {
                itemList.add(new Item(
                        rs.getInt("ID"),
                        rs.getString("CATEGORY_NAME"),
                        rs.getString("NAME_SHORT"),
                        rs.getString("NAME_FULL"),
                        rs.getString("DESCRIPTION"),
                        rs.getString("MANUFACTURER"),
                        rs.getBigDecimal("PRICE"),
                        rs.getString("STATE"),
                        rs.getDouble("RATING"),
                        rs.getInt("COUNT")
                ));
            }

            return itemList;

        } catch (ConnectionPoolException e) {
            throw new DAOException("Error in Connection pool while find Users", e);
        } catch (SQLException e) {
            throw new DAOException("Error while find Users", e);
        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
    }
}
