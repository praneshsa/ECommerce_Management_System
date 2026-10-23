package DAO;

import java.util.List;

import DTO.CartItem;

public interface CartItemDAO {

    boolean addCartItem(CartItem cartItem);

    List<CartItem> getCartItemsByCartId(int cart_id);

    CartItem getCartItemById(int cart_item_id);

    boolean updateQuantity(int cart_item_id, int quantity);

    boolean deleteCartItem(int cart_item_id);

    boolean cartItemExists(int cart_item_id);
}