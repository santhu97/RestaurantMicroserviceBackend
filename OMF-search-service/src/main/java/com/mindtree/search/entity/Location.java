package com.mindtree.search.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Location {
	
	@Id
	@Column(name = "locationId")
	private int locationId;
	
	@Column(name = "lattitude")
	private double lattitude;
	
	@Column(name = "longitude")
	private double longitude;
	
	@Column(name = "address")
	private String address;

	/**
	 * @return the locationId
	 */
	public int getLocationId() {
		return locationId;
	}

	/**
	 * @param locationId the locationId to set
	 */
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	/**
	 * @return the lattitude
	 */
	public double getLattitude() {
		return lattitude;
	}

	/**
	 * @param lattitude the lattitude to set
	 */
	public void setLattitude(double lattitude) {
		this.lattitude = lattitude;
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @param locationId
	 * @param lattitude
	 * @param longitude
	 * @param address
	 */
	public Location(int locationId, double lattitude, double longitude, String address) {
		super();
		this.locationId = locationId;
		this.lattitude = lattitude;
		this.longitude = longitude;
		this.address = address;
	}

	/**
	 * 
	 */
	public Location() {
		super();
	}
	
	
	
}
