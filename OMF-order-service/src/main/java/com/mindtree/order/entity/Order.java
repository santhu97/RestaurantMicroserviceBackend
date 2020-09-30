package com.mindtree.order.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "order_db")
public class Order {
	
	public enum PaymentStatus{
		PAY_NOW,PAY_LATER
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_Id")
	private int orderId;
	
	private String userId;
	
	private String userName;
	
	private String restaurantId;
	
	private String restaurantName;
	
	private String restaurantAddress;
	
	private double totalPrice;
	
	private String deliveryPerson;
	
	@Column(name = "orderedDate", columnDefinition = "TIMESTAMP")
	@CreationTimestamp
	private LocalDateTime orderedDate;
	
	private PaymentStatus isPaymentDone;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getRestaurantAddress() {
		return restaurantAddress;
	}

	public void setRestaurantAddress(String restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getDeliveryPerson() {
		return deliveryPerson;
	}

	public void setDeliveryPerson(String deliveryPerson) {
		this.deliveryPerson = deliveryPerson;
	}

	public LocalDateTime getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(LocalDateTime orderedDate) {
		this.orderedDate = orderedDate;
	}

	public PaymentStatus getIsPaymentDone() {
		return isPaymentDone;
	}

	public void setIsPaymentDone(PaymentStatus isPaymentDone) {
		this.isPaymentDone = isPaymentDone;
	}

	public Order(int orderId, String userId, String userName, String restaurantId, String restaurantName,
			String restaurantAddress, double totalPrice, String deliveryPerson, LocalDateTime orderedDate,
			PaymentStatus isPaymentDone) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.userName = userName;
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.restaurantAddress = restaurantAddress;
		this.totalPrice = totalPrice;
		this.deliveryPerson = deliveryPerson;
		this.orderedDate = orderedDate;
		this.isPaymentDone = isPaymentDone;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
