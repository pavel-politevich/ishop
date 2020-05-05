package by.lifetech.ishop.dao.impl;

import by.lifetech.ishop.bean.AuthorizedUser;
import by.lifetech.ishop.bean.InfoUser;
import by.lifetech.ishop.bean.User;
import by.lifetech.ishop.dao.impl.connection.ConnectionPool;
import by.lifetech.ishop.dao.impl.connection.ConnectionPoolException;
import by.lifetech.ishop.dao.UserDAO;
import by.lifetech.ishop.dao.exception.DAOException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private static ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String INSERT_USER_SQL = "insert into users(login,password,name,surname,phone,email,address,date_of_birth,state_id,role_id) values(?,?,?,?,?,?,?,?,?,?)";
    private static final String SIGN_IN_SQL = "select u.*, r.DESCRIPTION as role from ishop.users u join ishop.roles r on u.role_id = r.id where login = ? and password = ?";
    private static final String GET_USERS_BY_STATE = "select u.*, st.NAME as state from ishop.users u join ishop.dict_users_state st on u.STATE_ID = st.ID where state_id = ?";


    public  UserDAOImpl() {  }

    private static String getMD5Hash(byte[] password) {
        byte[] passwordToHash = password;
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash);
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            // log
        }
        return generatedPassword;
    }

    @Override
    public void registration(String login, byte[] password, String name, String surname, String email, String phone, String address, Date birthDate, int roleId) throws DAOException {

        PreparedStatement ps = null;
        Connection con = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(INSERT_USER_SQL);
            ps.setString(1, login);
            ps.setString(2, getMD5Hash(password));
            ps.setString(3, name);
            ps.setString(4, surname);
            ps.setString(5, phone);
            ps.setString(6, email);
            ps.setString(7, address);
            ps.setDate(8, (java.sql.Date) new java.sql.Date(birthDate.getTime()));
            ps.setInt(9, 1);
            ps.setInt(10, roleId);

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
    public void registration(User user) {

    }


    @Override
    public AuthorizedUser signIn(String login, byte[] password) throws DAOException {

        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;


        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SIGN_IN_SQL);
            ps.setString(1, login);
            ps.setString(2, getMD5Hash(password));

            rs = ps.executeQuery();

            if (rs == null) {
                return null;
            }

            rs.last();

            if (rs.getRow() == 1) {
                return new AuthorizedUser(rs.getString("name"), rs.getString("surname"), rs.getString("email"), rs.getString("role"));
            }

        } catch (ConnectionPoolException e) {
            throw new DAOException("Error in Connection pool while authorize User", e);
        } catch (SQLException e) {
            throw new DAOException("Error while authorize User", e);
        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
        return null;
    }

    @Override
    public User getUser(int userId) {
        return null;
    }

    @Override
    public List<InfoUser> findUsersByState(int stateId) throws DAOException {

        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;


        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(GET_USERS_BY_STATE);
            ps.setInt(1, stateId);

            rs = ps.executeQuery();

            if (rs == null) {
                return null;
            }

            List<InfoUser> infoUserList = new ArrayList<>();

            while (rs.next()) {
                infoUserList.add(new InfoUser(
                        rs.getString("login"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("email"),
                        rs.getString("state")
                ));
            }

            return infoUserList;

        } catch (ConnectionPoolException e) {
            throw new DAOException("Error in Connection pool while find Users", e);
        } catch (SQLException e) {
            throw new DAOException("Error while find Users", e);
        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
    }
}
