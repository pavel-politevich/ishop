package by.lifetech.ishop.dao.impl;

import by.lifetech.ishop.bean.Category;
import by.lifetech.ishop.bean.Item;
import by.lifetech.ishop.bean.Review;
import by.lifetech.ishop.dao.impl.connection.ConnectionPool;
import by.lifetech.ishop.dao.impl.connection.ConnectionPoolException;
import by.lifetech.ishop.dao.ItemDAO;
import by.lifetech.ishop.dao.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    private static final String TBL_COLUMN_NAME_SHORT = "name_short";
    private static final String TBL_COLUMN_NAME_FULL = "name_full";
    private static final String TBL_COLUMN_DESCRIPTION = "description";
    private static final String TBL_COLUMN_MANUFACTURER = "manufacturer";
    private static final String TBL_COLUMN_PRICE = "price";
    private static final String TBL_COLUMN_COUNT = "count";
    private static final String TBL_COLUMN_ID = "ID";
    private static final String TBL_COLUMN_CATEGORY_NAME = "CATEGORY_NAME";
    private static final String TBL_COLUMN_CATEGORY_DESC = "CATEGORY_DESC";
    private static final String TBL_COLUMN_STATE = "STATE";
    private static final String TBL_COLUMN_RATING = "RATING";
    private static final String TBL_COLUMN_EVENT_DATE = "event_date";
    private static final String TBL_COLUMN_USER_NAME = "name";
    private static final String TBL_COLUMN_RATE = "rate";
    private static final String TBL_COLUMN_COMMENT = "text";

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String GET_ITEMS_BY_CATEGORY = "SELECT i.ID, i.NAME_SHORT, i.NAME_FULL, i.DESCRIPTION," +
            " i.MANUFACTURER, i.PRICE, st.COUNT, dis.NAME as STATE, cat.CATEGORY_NAME, " +
            "(select IFNULL(AVG(r.rate),0) from ishop.item_reviews r where r.ITEM_ID = i.ID) as RATING " +
            "FROM ishop.items i join ishop.storage st on st.ITEM_ID = i.ID join ishop.dict_items_state dis on i.STATE_ID = dis.ID " +
            "join ishop.category cat on i.ID_CATEGORY = cat.ID where i.ID_CATEGORY = ? and st.COUNT > 0";
    private static final String GET_ALL_CATEGORIES_SQL = "select * from ishop.category";
    private static final String GET_ITEM_BY_ID = "SELECT i.ID, i.NAME_SHORT, i.NAME_FULL, i.DESCRIPTION, i.MANUFACTURER, i.PRICE, " +
            "st.COUNT, dis.NAME as STATE, cat.CATEGORY_NAME, (select IFNULL(AVG(r.rate),0) from ishop.item_reviews r " +
            "where r.ITEM_ID = i.ID) as RATING FROM ishop.items i join ishop.storage st on st.ITEM_ID = i.ID " +
            "join ishop.dict_items_state dis on i.STATE_ID = dis.ID join ishop.category cat on i.ID_CATEGORY = cat.ID " +
            "where i.ID = ?";
    private static final String ADD_REVIEW_TO_ITEM_SQL = "insert into ishop.item_reviews(user_id,item_id,rate,text) values(?,?,?,?)";
    private static final String GET_ITEM_REVIEWS_SQL = "SELECT r.event_date, u.NAME, r.RATE, r.TEXT " +
            "FROM ishop.item_reviews r join ishop.users u on r.USER_ID = u.id where r.ITEM_ID = ? " +
            "order by r.event_date desc limit 3";


    public ItemDAOImpl()  { }


    @Override
    public List<Item> findItemsByCategory(int categoryID) throws DAOException {
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(GET_ITEMS_BY_CATEGORY);
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
            throw new DAOException("Error in Connection pool while find Items", e);
        } catch (SQLException e) {
            throw new DAOException("Error while find Items", e);
        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
    }

    @Override
    public List<Category> getCategories() throws DAOException {
        Statement st = null;
        Connection con = null;
        ResultSet rs = null;

        try {
            con = connectionPool.takeConnection();
            st = con.createStatement();

            rs = st.executeQuery(GET_ALL_CATEGORIES_SQL);

            if (rs == null) {
                return null;
            }

            List<Category> categoryList = new ArrayList<>();

            while (rs.next()) {
                categoryList.add(new Category(
                        rs.getInt(TBL_COLUMN_ID),
                        rs.getString(TBL_COLUMN_CATEGORY_NAME),
                        rs.getString(TBL_COLUMN_CATEGORY_DESC)
                ));
            }

            return categoryList;

        } catch (ConnectionPoolException e) {
            throw new DAOException("Error in Connection pool while find Categories", e);
        } catch (SQLException e) {
            throw new DAOException("Error while find Categories", e);
        } finally {
            connectionPool.closeConnection(con, st, rs);
        }
    }

    @Override
    public Item getItem(int itemId) throws DAOException {
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(GET_ITEM_BY_ID);
            ps.setInt(1, itemId);

            rs = ps.executeQuery();

            if (rs == null) {
                return null;
            }

            Item item = null;

            if (rs.next()) {
                item = new Item(
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
                );
            }

            return item;

        } catch (ConnectionPoolException e) {
            throw new DAOException("Error in Connection pool while find Items", e);
        } catch (SQLException e) {
            throw new DAOException("Error while find Items", e);
        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
    }

    @Override
    public void addItemReview(int userId, int itemId, byte rate, String comment) throws DAOException {
        PreparedStatement ps = null;
        Connection con = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(ADD_REVIEW_TO_ITEM_SQL);
            ps.setInt(1, userId);
            ps.setInt(2, itemId);
            ps.setInt(3, rate);
            ps.setString(4, comment);

            ps.executeUpdate();

        } catch (ConnectionPoolException e) {
            throw new DAOException("Error in Connection pool while adding Review", e);
        } catch (SQLException e) {
            throw new DAOException("Error while dding Review", e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }
    }

    @Override
    public List<Review> getItemReviews(int itemId) throws DAOException {
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(GET_ITEM_REVIEWS_SQL);
            ps.setInt(1, itemId);

            rs = ps.executeQuery();

            if (rs == null) {
                return null;
            }

            List<Review> reviewList = new ArrayList<>();

            while (rs.next()) {
                reviewList.add(new Review(
                        rs.getTimestamp(TBL_COLUMN_EVENT_DATE),
                        rs.getString(TBL_COLUMN_USER_NAME),
                        rs.getByte(TBL_COLUMN_RATE),
                        rs.getString(TBL_COLUMN_COMMENT)
                ));
            }

            return reviewList;

        } catch (ConnectionPoolException e) {
            throw new DAOException("Error in Connection pool while reviews", e);
        } catch (SQLException e) {
            throw new DAOException("Error while find reviews", e);
        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
    }
}
