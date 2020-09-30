/**
 * 
 */
package com.mindtree.search.dto;

/**
 * @author M1056182
 *
 */
public class ResturantDetailsDto {

	private String restuarantName;
	
	private String restaurantAddress;
	
	private double timePerKm;

	public String getRestuarantName() {
		return restuarantName;
	}

	public void setRestuarantName(String restuarantName) {
		this.restuarantName = restuarantName;
	}

	public String getRestaurantAddress() {
		return restaurantAddress;
	}

	public void setRestaurantAddress(String restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
	}

	public double getTimePerKm() {
		return timePerKm;
	}

	public void setTimePerKm(double timePerKm) {
		this.timePerKm = timePerKm;
	}

	public ResturantDetailsDto(String restuarantName, String restaurantAddress, double timePerKm) {
		super();
		this.restuarantName = restuarantName;
		this.restaurantAddress = restaurantAddress;
		this.timePerKm = timePerKm;
	}

	public ResturantDetailsDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
