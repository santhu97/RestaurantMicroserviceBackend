/**
 * 
 */
package com.mindtree.search.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mindtree.search.entity.Restaurant;

/**
 * @author M1056182
 *
 */
public class FoodDto {
	
	private String foodId;
	
	private String foodName;
	
	private int quantity;
	
	private double foodPrice;
	
	private String foodType;
	
	@JsonIgnore
	private List<Restaurant> restaurants;

	/**
	 * @return the foodId
	 */
	public String getFoodId() {
		return foodId;
	}

	/**
	 * @param foodId the foodId to set
	 */
	public void setFoodId(String foodId) {
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
	public FoodDto(String foodId, String foodName, int quantity, double foodPrice, String foodType,
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
	public FoodDto() {
		super();
	}

	
	
}
