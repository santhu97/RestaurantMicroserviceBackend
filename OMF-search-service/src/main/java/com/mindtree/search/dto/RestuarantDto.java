/**
 * 
 */
package com.mindtree.search.dto;

import java.util.List;

import com.mindtree.search.entity.Food;
import com.mindtree.search.entity.Location;
import com.mindtree.search.entity.Review;

/**
 * @author M1056182
 *
 */
public class RestuarantDto {
	
	private String restaurantId;
	
	private String restaurantName;
	
	private Location location;
	
	private double rating;
	
	private String phoneNumber;
	
	private double minimumOrderAmount;
	
	private double timePerkm;
	
	private int offerInPercentage;
	
	private List<FoodDto> foodList;

	private List<ReviewDto> reviews;

	/**
	 * @return the restaurantId
	 */
	public String getRestaurantId() {
		return restaurantId;
	}

	/**
	 * @param restaurantId the restaurantId to set
	 */
	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

	/**
	 * @return the restaurantName
	 */
	public String getRestaurantName() {
		return restaurantName;
	}

	/**
	 * @param restaurantName the restaurantName to set
	 */
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * @return the rating
	 */
	public double getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(double rating) {
		this.rating = rating;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the minimumOrderAmount
	 */
	public double getMinimumOrderAmount() {
		return minimumOrderAmount;
	}

	/**
	 * @param minimumOrderAmount the minimumOrderAmount to set
	 */
	public void setMinimumOrderAmount(double minimumOrderAmount) {
		this.minimumOrderAmount = minimumOrderAmount;
	}

	/**
	 * @return the timePerkm
	 */
	public double getTimePerkm() {
		return timePerkm;
	}

	/**
	 * @param timePerkm the timePerkm to set
	 */
	public void setTimePerkm(double timePerkm) {
		this.timePerkm = timePerkm;
	}

	/**
	 * @return the offerInPercentage
	 */
	public int getOfferInPercentage() {
		return offerInPercentage;
	}

	/**
	 * @param offerInPercentage the offerInPercentage to set
	 */
	public void setOfferInPercentage(int offerInPercentage) {
		this.offerInPercentage = offerInPercentage;
	}

	/**
	 * @return the foodList
	 */
	public List<FoodDto> getFoodList() {
		return foodList;
	}

	/**
	 * @param foodList the foodList to set
	 */
	public void setFoodList(List<FoodDto> foodList) {
		this.foodList = foodList;
	}

	/**
	 * @return the reviews
	 */
	public List<ReviewDto> getReviews() {
		return reviews;
	}

	/**
	 * @param reviews the reviews to set
	 */
	public void setReviews(List<ReviewDto> reviews) {
		this.reviews = reviews;
	}

	/**
	 * @param restaurantId
	 * @param restaurantName
	 * @param location
	 * @param rating
	 * @param phoneNumber
	 * @param minimumOrderAmount
	 * @param timePerkm
	 * @param offerInPercentage
	 * @param foodList
	 * @param reviews
	 */
	public RestuarantDto(String restaurantId, String restaurantName, Location location, double rating,
			String phoneNumber, double minimumOrderAmount, double timePerkm, int offerInPercentage,
			List<FoodDto> foodList, List<ReviewDto> reviews) {
		super();
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.location = location;
		this.rating = rating;
		this.phoneNumber = phoneNumber;
		this.minimumOrderAmount = minimumOrderAmount;
		this.timePerkm = timePerkm;
		this.offerInPercentage = offerInPercentage;
		this.foodList = foodList;
		this.reviews = reviews;
	}

	/**
	 * 
	 */
	public RestuarantDto() {
		super();
	}

	@Override
	public String toString() {
		return "RestuarantDto [restaurantId=" + restaurantId + ", restaurantName=" + restaurantName + ", location="
				+ location + ", rating=" + rating + ", phoneNumber=" + phoneNumber + ", minimumOrderAmount="
				+ minimumOrderAmount + ", timePerkm=" + timePerkm + ", offerInPercentage=" + offerInPercentage
				+ ", foodList=" + foodList + ", reviews=" + reviews + "]";
	}

	
	
}
