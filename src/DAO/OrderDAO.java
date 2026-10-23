package DAO;

import java.util.List;

import DTO.Order;

public interface OrderDAO {

    boolean addOrder(Order order);

    List<Order> getAllOrders();

    Order getOrderById(int order_id);

    List<Order> getOrdersByUserId(int user_id);

    boolean updateOrderStatus(int order_id, String status);

    boolean deleteOrder(int order_id);

    boolean orderExists(int order_id);
}