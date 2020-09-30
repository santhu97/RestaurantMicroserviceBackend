/**
 * 
 */
package com.mindtree.search.dto;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mindtree.search.entity.Restaurant;

/**
 * @author M1056182
 *
 */
public class ReviewDto {
	
	private int reviewId;
	
	private String userId;
	
	private double rating;
	
	private String comment;
	
	@JsonIgnore
	private Restaurant restaurant;

	/**
	 * @return the reviewId
	 */
	public int getReviewId() {
		return reviewId;
	}

	/**
	 * @param reviewId the reviewId to set
	 */
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
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
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the restaurant
	 */
	public Restaurant getRestaurant() {
		return restaurant;
	}

	/**
	 * @param restaurant the restaurant to set
	 */
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	/**
	 * @param reviewId
	 * @param userId
	 * @param rating
	 * @param comment
	 * @param restaurant
	 */
	public ReviewDto(int reviewId, String userId, double rating, String comment, Restaurant restaurant) {
		super();
		this.reviewId = reviewId;
		this.userId = userId;
		this.rating = rating;
		this.comment = comment;
		this.restaurant = restaurant;
	}

	/**
	 * 
	 */
	public ReviewDto() {
		super();
	}
	
	

}
