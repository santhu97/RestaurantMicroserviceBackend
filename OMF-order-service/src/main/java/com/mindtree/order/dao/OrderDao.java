package com.mindtree.order.dao;

import java.util.Optional;

import com.mindtree.order.entity.Cart;
import com.mindtree.order.entity.CartItems;
import com.mindtree.order.entity.Order;

public interface OrderDao {

	Optional<Cart> getUserCart(String userId);

	void persistCart(Cart cart);

	void updateCart(Cart cart);

	Order persistOrder(Order order);

	void deleteCartUponSuccessOrder(int cartId);

	void flushOldCarts();


//	void deleteCartUponSuccessOrder1(String userId);

}
