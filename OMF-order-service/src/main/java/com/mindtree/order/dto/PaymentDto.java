/**
 * 
 */
package com.mindtree.order.dto;

/**
 * @author M1056182
 *
 */
public class PaymentDto {

	private String cardHolderName;
	
	private long cardNumber;
	
	private int expireyMonth;
	
	private int expiryYear;
	
	private int cvv;

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getExpireyMonth() {
		return expireyMonth;
	}

	public void setExpireyMonth(int expireyMonth) {
		this.expireyMonth = expireyMonth;
	}

	public int getExpiryYear() {
		return expiryYear;
	}

	public void setExpiryYear(int expiryYear) {
		this.expiryYear = expiryYear;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public PaymentDto(String cardHolderName, long cardNumber, int expireyMonth, int expiryYear, int cvv) {
		super();
		this.cardHolderName = cardHolderName;
		this.cardNumber = cardNumber;
		this.expireyMonth = expireyMonth;
		this.expiryYear = expiryYear;
		this.cvv = cvv;
	}

	public PaymentDto() {
		super();
	}
	
	
	
	
	
}
