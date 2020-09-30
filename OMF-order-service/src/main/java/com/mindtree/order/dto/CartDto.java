package com.mindtree.order.dto;

import java.util.List;

public class CartDto {

	private String userId;
	
	private int restaurantId;
	
	private List<FoodDto> foodList;

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

	public List<FoodDto> getFoodList() {
		return foodList;
	}

	public void setFoodList(List<FoodDto> foodList) {
		this.foodList = foodList;
	}

	public CartDto(String userId, int restaurantId, List<FoodDto> foodList) {
		super();
		this.userId = userId;
		this.restaurantId = restaurantId;
		this.foodList = foodList;
	}

	public CartDto() {
		super();
	}

	@Override
	public String toString() {
		return "CartDto [userId=" + userId + ", restaurantId=" + restaurantId + ", foodList=" + foodList + "]";
	}
	
	
	
	
	

	
	
}
