package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DB.DBConnection;
import DTO.User;

public class UserDAOImpl implements UserDAO {


    @Override
    public boolean addUser(User user) {

        String query = "INSERT INTO user "
                + "(user_id, name, email, password_hash, role) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, user.getUser_id());
            pstmt.setString(2, user.getUser_name());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getRole());

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        String query = "SELECT user_id, name, email, password_hash, role "
                + "FROM user";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {

                User user = new User();

                user.setUser_id(rs.getInt("user_id"));
                user.setUser_name(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password_hash"));
                user.setRole(rs.getString("role"));

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }


    @Override
    public User getUserById(int userId) {

        String query = "SELECT user_id, name, email, password_hash, role "
                + "FROM user WHERE user_id = ?";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, userId);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {

                    User user = new User();

                    user.setUser_id(rs.getInt("user_id"));
                    user.setUser_name(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password_hash"));
                    user.setRole(rs.getString("role"));

                    return user;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public boolean updateUser(User user) {

        String query = "UPDATE user SET "
                + "name = ?, "
                + "email = ?, "
                + "password_hash = ?, "
                + "role = ? "
                + "WHERE user_id = ?";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, user.getUser_name());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getRole());
            pstmt.setInt(5, user.getUser_id());

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean deleteUser(int userId) {

        String query = "DELETE FROM user WHERE user_id = ?";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, userId);

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User login(String email, String password) {

        String query = "SELECT * FROM user WHERE email = ? AND password_hash = ?";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, email);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {

                    User user = new User();

                    user.setUser_id(rs.getInt("user_id"));
                    user.setUser_name(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password_hash"));
                    user.setRole(rs.getString("role"));

                    return user;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}