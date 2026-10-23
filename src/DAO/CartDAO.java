package DAO;

import DTO.Cart;

public interface CartDAO {

    boolean addCart(Cart cart);

    Cart getCartByUserId(int user_id);

    boolean deleteCart(int cart_id);

    boolean cartExists(int cart_id);
}