/**
 * 
 */
package com.mindtree.order.util;

import java.util.ArrayList;
import java.util.List;

import com.mindtree.order.entity.Order;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;

/**
 * @author M1056182
 *
 */
public class DeliveryPerson {

	 List<String> deliveryPerson = new ArrayList<>();
	 
	 private final static String ACCOUNT_SID = "ACe551c6178b7187bc5c9714c38123cad8";
	   private final static String AUTH_ID = "e957e71344b71ea4e0725d8232acbdf9";
	   
	   static {
		      Twilio.init(ACCOUNT_SID, AUTH_ID);
		   }
	
	public  String assignDeliverPerson() {
		deliveryPerson.add("Nanda");
		deliveryPerson.add("Manja");
		deliveryPerson.add("Deepak");
		deliveryPerson.add("Tushar");
		deliveryPerson.add("Kesari");
		deliveryPerson.add("Kushal");
		deliveryPerson.add("Sathya");
		deliveryPerson.add("Shreyas");
		deliveryPerson.add("Ashish");
		deliveryPerson.add("Gokul");
		deliveryPerson.add("Gouthami");
		return deliveryPerson.parallelStream().findAny().get();
	}
	
	public static void sendSMS(Order order, String phone){
		com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber("+91"+phone), new PhoneNumber("+12093179345"),
				"Dear "+order.getUserName()+","+System.lineSeparator() +" your order is successfully placed in "+order.getRestaurantName()+" and delivered by "+order.getDeliveryPerson()
		         +System.lineSeparator()+"Thank You...").create();
//		  Message message = Message.creator(
//	                new com.twilio.type.PhoneNumber("whatsapp:+919113683980"),
//	                new com.twilio.type.PhoneNumber("whatsapp:+12093179345"),
//	                "Your Order Has been Successfully Placed.....")
//	            .create();
	}







	
}
