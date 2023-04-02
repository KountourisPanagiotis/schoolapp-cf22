package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements IUserDAO {

    @Override
    public User insert(User user) throws UserDAOException {
        String sql = "INSERT INTO USERS (USERNAME, PASSWORD) VALUES (?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            String username = user.getUsername();
            String password = user.getPassword();

            if (username.equals("") || password.equals("")) {
                return null;
            }

            p.setString(1, username);
            p.setString(2, password);
            p.executeUpdate();
            return user;
        } catch (SQLException | ClassNotFoundException e) {
            // e.printStackTrace(); // logging
            throw new UserDAOException("SQL Error in User " + user + " insertion");
        }
    }

    @Override
    public User update(User user) throws UserDAOException {
        String sql = "UPDATE USERS SET USERNAME = ?, PASSWORD = ? WHERE ID = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            int id = user.getId();
            String username = user.getUsername();
            String password = user.getPassword();

            if (username.equals("") || password.equals("")) {
                return null;
            }

            p.setString(1, username);
            p.setString(2, password);
            p.setInt(3, id);

            p.executeUpdate();
            return user;
        } catch (SQLException | ClassNotFoundException e) {
            //e.printStackTrace();
            throw new UserDAOException("SQL Error in User" + user.getUsername() + " update");
        }
    }

    @Override
    public void delete(int id) throws UserDAOException {
        String sql = "DELETE FROM USERS WHERE ID = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {
            p.setInt(1, id);
            p.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            //e.printStackTrace();
            throw new UserDAOException("SQL Error in User with id = " + id  + " deletion");
        }
    }

    @Override
    public List<User> getByUsername(String username) throws UserDAOException {
        String sql = "SELECT ID, USERNAME, PASSWORD FROM USERS WHERE USERNAME LIKE ?";
        ResultSet rs;
        List<User> users = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            p.setString(1, username + '%');
            rs = p.executeQuery();

            while (rs.next()) {
                User user= new User(
                        rs.getInt("ID"),
                        rs.getString("USERNAME"),
                        rs.getString("PASSWORD")
                );
                users.add(user);
            }

            return users;
        } catch (SQLException | ClassNotFoundException e) {
            // e.printStackTrace();
            throw new UserDAOException("SQL Error in User with username = " + username);
        }
    }

    @Override
    public User getById(int id) throws UserDAOException {
        User user = null;
        ResultSet rs;
        String sql = "SELECT ID, USERNAME, PASSWORD FROM USERS WHERE ID LIKE ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            p.setInt(1, id);
            rs = p.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getInt("ID"),
                        rs.getString("USERNAME"),
                        rs.getString("PASSWORD")
                );
            }
            return user;
        } catch (SQLException | ClassNotFoundException e) {
            // e.printStackTrace();
            throw new UserDAOException("SQL Error in User with id = " + id);
        }
    }

    @Override
    public boolean isUserValid(String username, String password) {
        return true;
    }
}
