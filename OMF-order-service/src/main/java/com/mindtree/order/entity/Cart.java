/**
 * 
 */
package com.mindtree.order.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author M1056182
 *
 */
@Entity
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartId;
	
	private String userId;
	
	private int restaurantId;
	
	private double cartValue;
	
	@OneToMany(mappedBy = "cart",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<CartItems> foodList;
	
	
	
	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public double getCartValue() {
		return cartValue;
	}

	public void setCartValue(double cartValue) {
		this.cartValue = cartValue;
	}

	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public List<CartItems> getFoodList() {
		return foodList;
	}

	public void setFoodList(List<CartItems> foodList) {
		this.foodList = foodList;
	}



	public Cart(int cartId, String userId, int restaurantId, double cartValue, List<CartItems> foodList) {
		super();
		this.cartId = cartId;
		this.userId = userId;
		this.restaurantId = restaurantId;
		this.cartValue = cartValue;
		this.foodList = foodList;
	}

	public Cart() {
		super();
	}

	
	
	
	


}
