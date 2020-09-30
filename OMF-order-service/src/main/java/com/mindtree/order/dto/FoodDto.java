package com.mindtree.order.dto;

public class FoodDto {

	private int foodId;
	
	private int quantity;
	

	public FoodDto(int foodId, int quantity) {
		super();
		this.foodId = foodId;
		this.quantity = quantity;
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


	public FoodDto() {
		super();
	}
	
	
	
}
