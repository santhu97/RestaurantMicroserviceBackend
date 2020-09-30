package com.mindtree.user.dto;

public class FoodDto {

	private int foodId;
	
	private int quantity = 1;
	

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
		if(quantity==0) {
			quantity = 1;
		}
		else {
		this.quantity = quantity;
		}
		}


	public FoodDto() {
		super();
	}
	
	
	
}
