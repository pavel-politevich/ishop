package by.lifetech.ishop.dao.impl;

import by.lifetech.ishop.bean.Item;
import by.lifetech.ishop.bean.Order;
import by.lifetech.ishop.dao.OrderDAO;
import by.lifetech.ishop.dao.exception.DAOException;
import by.lifetech.ishop.dao.factory.DAOFactory;
import by.lifetech.ishop.dao.impl.connection.ConnectionPool;
import by.lifetech.ishop.dao.impl.connection.ConnectionPoolException;

import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OrderDAOImpl implements OrderDAO {

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String TBL_COLUMN_ORDER_ID = "order_id";
    private static final String TBL_COLUMN_START_DATE = "start_date";
    private static final String TBL_COLUMN_COMMENT = "comment";
    private static final String TBL_COLUMN_DELIVERY_ADDRESS = "delivery_address";
    private static final String TBL_COLUMN_STATE = "status_name";
    private static final String TBL_COLUMN_DESCRIPTION = "description";
    private static final String TBL_COLUMN_ITEM_ID = "item_id";
    private static final String TBL_COLUMN_ID = "id";
    private static final String TBL_COLUMN_ITEM_COUNT = "count";
    private static final String CREATE_EMPTY_ORDER_SQL = "{call ishop.create_order(?,?)}";
    private static final String ADD_ITEM_TO_ORDER_SQL = "insert into ishop.order_details(order_id,count,item_id,item_cost) " +
            "select ? as order_id, ? as count, ? as item_id, (select t.price * count as item_cost from ishop.items t where t.id = item_id)";
    private static final String GET_ORDER_BY_ID_SQL = "select * from(\n" +
            "SELECT ord.id, ord.START_DATE, ord.COMMENT, ord.DELIVERY_ADDRESS,\n" +
            "st.STATUS_NAME, ptype.DESCRIPTION,\n" +
            "det.ITEM_ID, det.COUNT, det.ITEM_COST,\n" +
            "(select max(ORDER_STATUS) from ishop.order_status_history where ORDER_ID = ord.id) as max_state\n" +
            "FROM ishop.orders ord join ishop.order_details det\n" +
            "on  det.ORDER_ID = ord.id\n" +
            "left join ishop.dict_payment_types ptype on ptype.id = ord.PAYMENT_TYPE_ID\n" +
            "join ishop.order_status_history sth on ord.id = sth.ORDER_ID\n" +
            "join ishop.dict_order_status st on st.id = sth.ORDER_STATUS\n" +
            "where ord.id = ?) t where  t.max_state = 1";
    private static final String DELETE_ITEM_FROM_ORDER_SQL = "delete from ishop.order_details where order_id = ? and item_id = ?";
    private static final String GET_CURRENT_ORDER_ID_BY_USER_SQL = "select * from (\n" +
            "SELECT ord.id as order_id ,\n" +
            "(select max(ORDER_STATUS) from ishop.order_status_history where ORDER_ID = ord.id) as max_state\n" +
            "FROM ishop.orders ord join ishop.order_details det on  det.ORDER_ID = ord.id \n" +
            "join ishop.order_status_history sth on ord.id = sth.ORDER_ID \n" +
            "join ishop.dict_order_status st on st.id = sth.ORDER_STATUS \n" +
            "where ord.USER_ID = ? and st.id = 1 order by ord.START_DATE desc limit 1) t\n" +
            "where t.max_state = 1";
    private static final String GET_PAYMENT_TYPES_SQL = "SELECT * FROM ishop.dict_payment_types";
    private static final String CONFIRM_ORDER_SQL = "{call ishop.confirm_order(?,?,?,?)}";


    @Override
    public int createEmptyOrder(int userId) throws DAOException {

        CallableStatement cs = null;
        Connection con = null;

        try {
            con = connectionPool.takeConnection();
            cs = con.prepareCall(CREATE_EMPTY_ORDER_SQL);

            cs.setInt(1,userId);

            cs.registerOutParameter(TBL_COLUMN_ORDER_ID, Types.INTEGER);

            cs.execute();

            return cs.getInt(TBL_COLUMN_ORDER_ID);


        } catch (ConnectionPoolException e) {
            throw new DAOException("Error in Connection pool while adding new Order", e);
        } catch (SQLException e) {
            throw new DAOException("Error while adding new Order", e);
        } finally {
            connectionPool.closeConnection(con, cs);
        }
    }

    @Override
    public void addItem(int orderId, int itemId, int count) throws DAOException {

        PreparedStatement ps = null;
        Connection con = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(ADD_ITEM_TO_ORDER_SQL);
            ps.setInt(1, orderId);
            ps.setInt(2, count);
            ps.setInt(3, itemId);

            ps.executeUpdate();

        } catch (ConnectionPoolException e) {
            throw new DAOException("Error in Connection pool while adding Item to Cart", e);
        } catch (SQLException e) {
            throw new DAOException("Error while adding Item to Cart", e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }

    }

    @Override
    public void deleteItem(int orderId, int itemId) throws DAOException {
        PreparedStatement ps = null;
        Connection con = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(DELETE_ITEM_FROM_ORDER_SQL);
            ps.setInt(1, orderId);
            ps.setInt(2, itemId);

            ps.executeUpdate();

        } catch (ConnectionPoolException e) {
            throw new DAOException("Error in Connection pool while remove Item from Cart", e);
        } catch (SQLException e) {
            throw new DAOException("Error while while remove Item from Cart", e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }
    }

    @Override
    public Order getOrder(int orderId) throws DAOException {
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(GET_ORDER_BY_ID_SQL);
            ps.setInt(1, orderId);

            rs = ps.executeQuery();

            if (rs == null) {
                return null;
            }

            Map<Item, Integer> orderItems = new HashMap<>();
            String orderState = null;
            Date orderDate = null;
            String paymentType = null;
            String orderComment = null;
            String deliveryAddress = null;

            while (rs.next()) {
                Item item = DAOFactory.getInstance().getItemDAO().getItem(rs.getInt(TBL_COLUMN_ITEM_ID));
                int itemCount = rs.getInt(TBL_COLUMN_ITEM_COUNT);

                if (orderItems.containsKey(item)) {
                    orderItems.put(item, orderItems.get(item) + itemCount);
                }
                else {
                    orderItems.put(item, itemCount);
                }
            }

            rs.previous();

            if (rs.next()) {
                orderState = rs.getString(TBL_COLUMN_STATE);
                orderDate = rs.getDate(TBL_COLUMN_START_DATE);
                paymentType = rs.getString(TBL_COLUMN_DESCRIPTION);
                orderComment = rs.getString(TBL_COLUMN_COMMENT);
                deliveryAddress = rs.getString(TBL_COLUMN_DELIVERY_ADDRESS);
            }

            return new Order(orderId,orderItems,orderState,orderDate,paymentType,orderComment,deliveryAddress);

        } catch (ConnectionPoolException e) {
            throw new DAOException("Error in Connection pool while search Order", e);
        } catch (SQLException e) {
            throw new DAOException("Error while search Order", e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }
    }

    @Override
    public int getCurrentOrderId(int userId) throws DAOException {

        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        int orderId = 0;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(GET_CURRENT_ORDER_ID_BY_USER_SQL);
            ps.setInt(1, userId);

            rs = ps.executeQuery();

            if (rs == null) {
                return 0;
            }

            if (rs.next()) {
                orderId = rs.getInt(TBL_COLUMN_ORDER_ID);
            }

            return orderId;

        } catch (ConnectionPoolException e) {
            throw new DAOException("Error in Connection pool while get OrderId", e);
        } catch (SQLException e) {
            throw new DAOException("Error while get OrderId", e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }



    }

    @Override
    public Map<Integer, String> getPaymentTypes() throws DAOException {
        Statement st = null;
        Connection con = null;
        ResultSet rs = null;

        try {
            con = connectionPool.takeConnection();
            st = con.createStatement();

            rs = st.executeQuery(GET_PAYMENT_TYPES_SQL);

            if (rs == null) {
                return null;
            }

            Map<Integer,String> paymentMap = new HashMap<>();

            while (rs.next()) {
                paymentMap.put(
                        rs.getInt(TBL_COLUMN_ID),
                        rs.getString(TBL_COLUMN_DESCRIPTION)
                );
            }

            return paymentMap;

        } catch (ConnectionPoolException e) {
            throw new DAOException("Error in Connection pool while find paymentTypes", e);
        } catch (SQLException e) {
            throw new DAOException("Error while find paymentTypes", e);
        } finally {
            connectionPool.closeConnection(con, st, rs);
        }
    }

    @Override
    public void confirmOrder(Order order) throws DAOException {
        CallableStatement cs = null;
        Connection con = null;

        try {
            con = connectionPool.takeConnection();
            cs = con.prepareCall(CONFIRM_ORDER_SQL);

            cs.setString(1,order.getPaymentType());
            cs.setString(2,order.getComment());
            cs.setString(3,order.getAddress());
            cs.setInt(4,order.getId());

            cs.execute();


        } catch (ConnectionPoolException e) {
            throw new DAOException("Error in Connection pool while confirm Order", e);
        } catch (SQLException e) {
            throw new DAOException("Error while confirm Order", e);
        } finally {
            connectionPool.closeConnection(con, cs);
        }
    }
}
