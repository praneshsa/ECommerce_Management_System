package DAO;

import java.util.List;
import DTO.OrderItem;

public interface OrderItemDAO {

    boolean addOrderItem(OrderItem orderItem);

    List<OrderItem> getOrderItemsByOrderId(int order_id);

    OrderItem getOrderItemById(int order_item_id);

    boolean updateQuantity(int order_item_id, int quantity);

    boolean deleteOrderItem(int order_item_id);

    boolean orderItemExists(int order_item_id);
}