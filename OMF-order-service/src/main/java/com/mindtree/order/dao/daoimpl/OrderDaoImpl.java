package com.mindtree.order.dao.daoimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.order.dao.OrderDao;
import com.mindtree.order.entity.Cart;
import com.mindtree.order.entity.Order;
import com.mindtree.order.repository.CartRepository;
import com.mindtree.order.repository.OrderRepository;

@Service
public class OrderDaoImpl implements OrderDao{
	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private OrderRepository orderRepo;

	@Override
	public Optional<Cart> getUserCart(String userId) {
		return cartRepo.fetchUserCart(userId);
	}

	@Override
	public void persistCart(Cart cart) {
		cartRepo.save(cart);
	}

	@Override
	public void updateCart(Cart cart) {
		cartRepo.saveAndFlush(cart);
	}

	@Override
	public Order persistOrder(Order order) {
		return orderRepo.save(order);
	}

	@Override
	public void deleteCartUponSuccessOrder(int cartId) {
		cartRepo.deleteById(cartId);
	}

	@Override
	public void flushOldCarts() {
		orderRepo.deleteAll();
	}

//	@Override
//	public void deleteCartUponSuccessOrder1(String userId) {
//		cartRepo.deletByUserId(userId);
		
//	}

}
