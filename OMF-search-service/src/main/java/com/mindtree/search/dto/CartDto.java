/**
 * 
 */
package com.mindtree.search.dto;

import java.util.List;

/**
 * @author M1056182
 *
 */
public class CartDto {
	
	private String userId;
	
	private int restaurantId;
	
	private List<FoodInCartDto> foodList;

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

	public List<FoodInCartDto> getFoodList() {
		return foodList;
	}

	public void setFoodList(List<FoodInCartDto> foodList) {
		this.foodList = foodList;
	}

	public CartDto(String userId, int restaurantId, List<FoodInCartDto> foodList) {
		super();
		this.userId = userId;
		this.restaurantId = restaurantId;
		this.foodList = foodList;
	}

	public CartDto() {
		super();
	}
	
	

}
