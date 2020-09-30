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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "restuarant")
public class Restaurant {
	
	@Id
	@Column(name = "restaurant_id")
	private String restaurantId;
	
	@Column(name = "restaurant_name")
	private String restaurantName;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "location")
	private Location location;
	
	@Column(name = "locality")
	private String locality;
	
	@Column(name = "rating")
	private double rating;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "min_order_amount")
	private double minimumOrderAmount;
	
	@Column(name = "time_perkm")
	private double timePerkm;
	
	@Column(name = "offer_percent")
	private int offerInPercentage;
	
	  @ManyToMany(cascade = { CascadeType.ALL },fetch = FetchType.EAGER)
    @JoinTable(
        name = "restaurant_food", 
        joinColumns = { @JoinColumn(name = "restaurantId") }, 
        inverseJoinColumns = { @JoinColumn(name = "foodId") }
    )
	private List<Food> foods;
	
    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
	private List<Review> reviews;
    
    

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
	 * @return the locality
	 */
	public String getLocality() {
		return locality;
	}

	/**
	 * @param locality the locality to set
	 */
	public void setLocality(String locality) {
		this.locality = locality;
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
	 * @return the foods
	 */
	public List<Food> getFoods() {
		return foods;
	}

	/**
	 * @param foods the foods to set
	 */
	public void setFoods(List<Food> foods) {
		this.foods = foods;
	}

	/**
	 * @return the reviews
	 */
	public List<Review> getReviews() {
		return reviews;
	}

	/**
	 * @param reviews the reviews to set
	 */
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	/**
	 * @param restaurantId
	 * @param restaurantName
	 * @param location
	 * @param locality
	 * @param rating
	 * @param phoneNumber
	 * @param minimumOrderAmount
	 * @param timePerkm
	 * @param offerInPercentage
	 * @param foods
	 * @param reviews
	 */
	public Restaurant(String restaurantId, String restaurantName, Location location, String locality, double rating,
			String phoneNumber, double minimumOrderAmount, double timePerkm, int offerInPercentage, List<Food> foods,
			List<Review> reviews) {
		super();
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.location = location;
		this.locality = locality;
		this.rating = rating;
		this.phoneNumber = phoneNumber;
		this.minimumOrderAmount = minimumOrderAmount;
		this.timePerkm = timePerkm;
		this.offerInPercentage = offerInPercentage;
		this.foods = foods;
		this.reviews = reviews;
	}

	/**
	 * 
	 */
	public Restaurant() {
		super();
	}

	@Override
	public String toString() {
		return "Restaurant [restaurantId=" + restaurantId + ", restaurantName=" + restaurantName + ", location="
				+ location + ", locality=" + locality + ", rating=" + rating + ", phoneNumber=" + phoneNumber
				+ ", minimumOrderAmount=" + minimumOrderAmount + ", timePerkm=" + timePerkm + ", offerInPercentage="
				+ offerInPercentage + ", foods=" + foods + ", reviews=" + reviews + "]";
	}
    
	
	
    

}
