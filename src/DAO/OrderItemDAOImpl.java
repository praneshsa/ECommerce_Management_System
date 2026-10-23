package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DB.DBConnection;
import DTO.OrderItem;

public class OrderItemDAOImpl implements OrderItemDAO {

    public void createOrderItemTable() {

        String query = "CREATE TABLE IF NOT EXISTS order_item ("
                + "order_item_id INT PRIMARY KEY, "
                + "order_id INT NOT NULL, "
                + "product_id INT NOT NULL, "
                + "quantity INT NOT NULL, "
                + "price DOUBLE NOT NULL"
                + ")";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.executeUpdate();

            System.out.println("OrderItem table created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addOrderItem(OrderItem orderItem) {

        String query = "INSERT INTO order_item "
                + "(order_item_id, order_id, product_id, quantity, price) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, orderItem.getOrder_item_id());
            pstmt.setInt(2, orderItem.getOrder_id());
            pstmt.setInt(3, orderItem.getProduct_id());
            pstmt.setInt(4, orderItem.getQuantity());
            pstmt.setDouble(5, orderItem.getPrice());

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(int order_id) {

        List<OrderItem> list = new ArrayList<>();

        String query = "SELECT * FROM order_item WHERE order_id = ?";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, order_id);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {

                    OrderItem orderItem = new OrderItem();

                    orderItem.setOrder_item_id(rs.getInt("order_item_id"));
                    orderItem.setOrder_id(rs.getInt("order_id"));
                    orderItem.setProduct_id(rs.getInt("product_id"));
                    orderItem.setQuantity(rs.getInt("quantity"));
                    orderItem.setPrice(rs.getDouble("price"));

                    list.add(orderItem);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public OrderItem getOrderItemById(int order_item_id) {

        String query = "SELECT * FROM order_item "
                + "WHERE order_item_id = ?";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, order_item_id);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {

                    OrderItem orderItem = new OrderItem();

                    orderItem.setOrder_item_id(rs.getInt("order_item_id"));

                    orderItem.setOrder_id(rs.getInt("order_id"));

                    orderItem.setProduct_id(rs.getInt("product_id"));

                    orderItem.setQuantity(rs.getInt("quantity"));

                    orderItem.setPrice(rs.getDouble("price"));

                    return orderItem;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean updateQuantity(int order_item_id, int quantity) {

        if (quantity <= 0) {

            System.out.println(
                    "Quantity must be greater than 0.");

            return false;
        }

        String query = "UPDATE order_item SET quantity = ? "
                + "WHERE order_item_id = ?";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, quantity);
            pstmt.setInt(2, order_item_id);

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteOrderItem(int order_item_id) {

        String query = "DELETE FROM order_item "
                + "WHERE order_item_id = ?";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, order_item_id);

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean orderItemExists(int order_item_id) {

        String query = "SELECT 1 FROM order_item "
                + "WHERE order_item_id = ?";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, order_item_id);

            try (ResultSet rs = pstmt.executeQuery()) {

                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}