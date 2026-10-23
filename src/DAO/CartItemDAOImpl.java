package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DB.DBConnection;
import DTO.CartItem;

public class CartItemDAOImpl implements CartItemDAO {


    public void createCartItemTable() {

        String query = "CREATE TABLE IF NOT EXISTS cart_item ("
                + "cart_item_id INT PRIMARY KEY, "
                + "cart_id INT NOT NULL, "
                + "product_id INT NOT NULL, "
                + "quantity INT NOT NULL"
                + ")";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.executeUpdate();

            System.out.println("CartItem table created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addCartItem(CartItem cartItem) {

        String query = "INSERT INTO cart_item "
                + "(cart_item_id, cart_id, product_id, quantity) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, cartItem.getCart_item_id());
            pstmt.setInt(2, cartItem.getCart_id());
            pstmt.setInt(3, cartItem.getProduct_id());
            pstmt.setInt(4, cartItem.getQuantity());

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<CartItem> getCartItemsByCartId(int cart_id) {

        List<CartItem> list = new ArrayList<>();

        String query = "SELECT * FROM cart_item WHERE cart_id = ?";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, cart_id);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {

                    CartItem cartItem = new CartItem();

                    cartItem.setCart_item_id(rs.getInt("cart_item_id"));
                    cartItem.setCart_id(rs.getInt("cart_id"));
                    cartItem.setProduct_id(rs.getInt("product_id"));
                    cartItem.setQuantity(rs.getInt("quantity"));

                    list.add(cartItem);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public CartItem getCartItemById(int cart_item_id) {

        String query = "SELECT * FROM cart_item WHERE cart_item_id = ?";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, cart_item_id);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {

                    CartItem cartItem = new CartItem();

                    cartItem.setCart_item_id(rs.getInt("cart_item_id"));
                    cartItem.setCart_id(rs.getInt("cart_id"));
                    cartItem.setProduct_id(rs.getInt("product_id"));
                    cartItem.setQuantity(rs.getInt("quantity"));

                    return cartItem;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean updateQuantity(int cart_item_id, int quantity) {

        if (quantity <= 0) {
            System.out.println("Quantity must be greater than 0.");
            return false;
        }

        String query = "UPDATE cart_item SET quantity = ? "
                + "WHERE cart_item_id = ?";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, quantity);
            pstmt.setInt(2, cart_item_id);

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCartItem(int cart_item_id) {

        String query = "DELETE FROM cart_item WHERE cart_item_id = ?";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, cart_item_id);

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean cartItemExists(int cart_item_id) {

        String query = "SELECT 1 FROM cart_item WHERE cart_item_id = ?";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, cart_item_id);

            try (ResultSet rs = pstmt.executeQuery()) {

                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}