/**
 * 
 */
package com.mindtree.user.dto;

/**
 * @author M1056182
 *
 */
public class UserDto {
	
	private String address;
	
	private long phoneNumber;


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
	 * @return the phoneNumber
	 */
	public long getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	


	/**
	 * @param address
	 * @param phoneNumber
	 */
	public UserDto(String address, long phoneNumber) {
		super();
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	/**
	 * 
	 */
	public UserDto() {
		super();
	}
	
	

}
