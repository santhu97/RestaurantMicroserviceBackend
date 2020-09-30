package com.mindtree.search.dto;

public class FoodInCartDto {

	private int foodId;
	
	private int quantity;

	



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

	public FoodInCartDto(int foodId, int quantity) {
		super();
		this.foodId = foodId;
		this.quantity = quantity;
	}

	public FoodInCartDto() {
		super();
	}
	
	
	
}
