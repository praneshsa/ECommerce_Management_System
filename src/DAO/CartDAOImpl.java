package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DB.DBConnection;
import DTO.Cart;

public class CartDAOImpl implements CartDAO {


    public void createCartTable() {

        String query = "CREATE TABLE IF NOT EXISTS cart ("
                + "cart_id INT PRIMARY KEY, "
                + "user_id INT NOT NULL"
                + ")";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.executeUpdate();

            System.out.println("Cart table created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addCart(Cart cart) {

        String query = "INSERT INTO cart (cart_id, user_id) VALUES (?, ?)";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, cart.getCart_id());
            pstmt.setInt(2, cart.getUser_id());

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Cart getCartByUserId(int user_id) {

        String query = "SELECT * FROM cart WHERE user_id = ?";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, user_id);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {

                    Cart cart = new Cart();

                    cart.setCart_id(rs.getInt("cart_id"));
                    cart.setUser_id(rs.getInt("user_id"));

                    return cart;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean deleteCart(int cart_id) {

        String query = "DELETE FROM cart WHERE cart_id = ?";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, cart_id);

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean cartExists(int cart_id) {

        String query = "SELECT 1 FROM cart WHERE cart_id = ?";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, cart_id);

            try (ResultSet rs = pstmt.executeQuery()) {

                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}