package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DB.DBConnection;
import DTO.Order;

public class OrderDAOImpl implements OrderDAO {


    public void createOrderTable() {

        String query = "CREATE TABLE IF NOT EXISTS orders ("
                + "order_id INT PRIMARY KEY, "
                + "user_id INT NOT NULL, "
                + "order_date DATE NOT NULL, "
                + "total_amount DOUBLE NOT NULL, "
                + "status VARCHAR(30) NOT NULL"
                + ")";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.executeUpdate();

            System.out.println("Orders table created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addOrder(Order order) {

        String query = "INSERT INTO orders "
                + "(order_id, user_id, order_date, total_amount, status) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, order.getOrder_id());
            pstmt.setInt(2, order.getUser_id());
            pstmt.setString(3, order.getOrder_date());
            pstmt.setDouble(4, order.getTotal_amount());
            pstmt.setString(5, order.getStatus());

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Order> getAllOrders() {

        List<Order> list = new ArrayList<>();

        String query = "SELECT * FROM orders";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {

                Order order = new Order();

                order.setOrder_id(rs.getInt("order_id"));
                order.setUser_id(rs.getInt("user_id"));
                order.setOrder_date(rs.getString("order_date"));
                order.setTotal_amount(rs.getDouble("total_amount"));
                order.setStatus(rs.getString("status"));

                list.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Order getOrderById(int order_id) {

        String query = "SELECT * FROM orders WHERE order_id = ?";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, order_id);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {

                    Order order = new Order();

                    order.setOrder_id(rs.getInt("order_id"));
                    order.setUser_id(rs.getInt("user_id"));
                    order.setOrder_date(rs.getString("order_date"));
                    order.setTotal_amount(rs.getDouble("total_amount"));
                    order.setStatus(rs.getString("status"));

                    return order;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Order> getOrdersByUserId(int user_id) {

        List<Order> list = new ArrayList<>();

        String query = "SELECT * FROM orders WHERE user_id = ?";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, user_id);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {

                    Order order = new Order();

                    order.setOrder_id(rs.getInt("order_id"));
                    order.setUser_id(rs.getInt("user_id"));
                    order.setOrder_date(rs.getString("order_date"));
                    order.setTotal_amount(rs.getDouble("total_amount"));
                    order.setStatus(rs.getString("status"));

                    list.add(order);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean updateOrderStatus(int order_id, String status) {

        String query = "UPDATE orders SET status = ? WHERE order_id = ?";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, status);
            pstmt.setInt(2, order_id);

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteOrder(int order_id) {

        String query = "DELETE FROM orders WHERE order_id = ?";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, order_id);

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean orderExists(int order_id) {

        String query = "SELECT 1 FROM orders WHERE order_id = ?";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, order_id);

            try (ResultSet rs = pstmt.executeQuery()) {

                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}