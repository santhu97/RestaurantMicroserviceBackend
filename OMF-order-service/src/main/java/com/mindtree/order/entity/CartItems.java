package com.mindtree.order.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cart_items")
public class CartItems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cart_Items_Id;
	
	private int foodId;
	
	private int quantity;
	
	@ManyToOne
	private Cart cart;

	public CartItems() {
		super();
	}



	public int getCart_Items_Id() {
		return cart_Items_Id;
	}



	public void setCart_Items_Id(int cart_Items_Id) {
		this.cart_Items_Id = cart_Items_Id;
	}



	public CartItems(int cart_Items_Id, int foodId, int quantity, Cart cart) {
		super();
		this.cart_Items_Id = cart_Items_Id;
		this.foodId = foodId;
		this.quantity = quantity;
		this.cart = cart;
	}



	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	
	
	
}
