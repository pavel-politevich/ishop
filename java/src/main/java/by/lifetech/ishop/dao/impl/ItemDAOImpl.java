package by.lifetech.ishop.dao.impl;

import by.lifetech.ishop.bean.Item;
import by.lifetech.ishop.dao.impl.connection.ConnectionPool;
import by.lifetech.ishop.dao.impl.connection.ConnectionPoolException;
import by.lifetech.ishop.dao.ItemDAO;
import by.lifetech.ishop.dao.exception.DAOException;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    private static final String TBL_COLUMN_ID_CATEGORY = "id_category";
    private static final String TBL_COLUMN_NAME_SHORT = "name_short";
    private static final String TBL_COLUMN_NAME_FULL = "name_full";
    private static final String TBL_COLUMN_DESCRIPTION = "description";
    private static final String TBL_COLUMN_MANUFACTURER = "manufacturer";
    private static final String TBL_COLUMN_PRICE = "price";
    private static final String TBL_COLUMN_STATE_ID = "state_id";
    private static final String TBL_COLUMN_COUNT = "count";
    private static final String TBL_COLUMN_ITEM_ID = "item_id";
    private static final String TBL_COLUMN_ID = "ID";
    private static final String TBL_COLUMN_CATEGORY_NAME = "CATEGORY_NAME";
    private static final String TBL_COLUMN_STATE = "STATE";
    private static final String TBL_COLUMN_RATING = "RATING";

    private static ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String INSERT_ITEM_SQL = "{call ishop.add_new_item(?,?,?,?,?,?,?,?,?)}";
    private static final String UPDATE_ITEM_STATUS_SQL = "update ishop.items set state_id = ? where id = ?";
    private static final String GET_ITEMS_BY_STATE = "SELECT i.ID, i.NAME_SHORT, i.NAME_FULL, i.DESCRIPTION, i.MANUFACTURER, i.PRICE, st.COUNT, dis.NAME as STATE, cat.CATEGORY_NAME, (select IFNULL(AVG(r.rate),0) from ishop.item_reviews r where r.ITEM_ID = i.ID) as RATING FROM ishop.items i join ishop.storage st on st.ITEM_ID = i.ID join ishop.dict_items_state dis on i.STATE_ID = dis.ID join ishop.category cat on i.ID_CATEGORY = cat.ID where i.ID_CATEGORY = ?";



    public ItemDAOImpl()  { }

    @Override
    public int addItem(int categoryId, String nameFull, String nameShort, String description, String manufacturer, BigDecimal cost, int stateId, int count) throws DAOException {
        CallableStatement cs = null;
        Connection con = null;

        try {
            con = connectionPool.takeConnection();
            cs = con.prepareCall(INSERT_ITEM_SQL);
            cs.setInt(TBL_COLUMN_ID_CATEGORY,categoryId);
            cs.setString(TBL_COLUMN_NAME_SHORT,nameShort);
            cs.setString(TBL_COLUMN_NAME_FULL,nameFull);
            cs.setString(TBL_COLUMN_DESCRIPTION,description);
            cs.setString(TBL_COLUMN_MANUFACTURER,manufacturer);
            cs.setBigDecimal(TBL_COLUMN_PRICE,cost);
            cs.setInt(TBL_COLUMN_STATE_ID,stateId);
            cs.setInt(TBL_COLUMN_COUNT,count);

            cs.registerOutParameter(TBL_COLUMN_ITEM_ID, Types.INTEGER);

            cs.execute();

            return cs.getInt(TBL_COLUMN_ITEM_ID);

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
                        rs.getInt(TBL_COLUMN_ID),
                        rs.getString(TBL_COLUMN_CATEGORY_NAME),
                        rs.getString(TBL_COLUMN_NAME_SHORT),
                        rs.getString(TBL_COLUMN_NAME_FULL),
                        rs.getString(TBL_COLUMN_DESCRIPTION),
                        rs.getString(TBL_COLUMN_MANUFACTURER),
                        rs.getBigDecimal(TBL_COLUMN_PRICE),
                        rs.getString(TBL_COLUMN_STATE),
                        rs.getDouble(TBL_COLUMN_RATING),
                        rs.getInt(TBL_COLUMN_COUNT)
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
