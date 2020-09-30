package com.mindtree.search.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "food")
public class Food {

	
	@Id
	@Column(name = "food_Id")
	private int foodId;
	
	@Column(name = "food_name")
	private String foodName;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "food_price")
	private double foodPrice;
	
	@Column(name = "food_type")
	private String foodType;
	
	@JsonIgnore
	@ManyToMany(cascade = { CascadeType.ALL },fetch = FetchType.EAGER)
	 @JoinTable(
		        name = "restaurant_food", 
		        joinColumns = { @JoinColumn(name = "foodId") }, 
		        inverseJoinColumns = { @JoinColumn(name = "restaurantId") }
		    )
	private List<Restaurant> restaurants;


	

	public int getFoodId() {
		return foodId;
	}


	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}


	/**
	 * @return the foodName
	 */
	public String getFoodName() {
		return foodName;
	}


	/**
	 * @param foodName the foodName to set
	 */
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}


	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}


	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	/**
	 * @return the foodPrice
	 */
	public double getFoodPrice() {
		return foodPrice;
	}


	/**
	 * @param foodPrice the foodPrice to set
	 */
	public void setFoodPrice(double foodPrice) {
		this.foodPrice = foodPrice;
	}


	/**
	 * @return the foodType
	 */
	public String getFoodType() {
		return foodType;
	}


	/**
	 * @param foodType the foodType to set
	 */
	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}


	/**
	 * @return the restaurants
	 */
	public List<Restaurant> getRestaurants() {
		return restaurants;
	}


	/**
	 * @param restaurants the restaurants to set
	 */
	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}


	/**
	 * @param foodId
	 * @param foodName
	 * @param quantity
	 * @param foodPrice
	 * @param foodType
	 * @param restaurants
	 */
	public Food(int foodId, String foodName, int quantity, double foodPrice, String foodType,
			List<Restaurant> restaurants) {
		super();
		this.foodId = foodId;
		this.foodName = foodName;
		this.quantity = quantity;
		this.foodPrice = foodPrice;
		this.foodType = foodType;
		this.restaurants = restaurants;
	}


	/**
	 * 
	 */
	public Food() {
		super();
	}
	
	
	
	
	
	
}
