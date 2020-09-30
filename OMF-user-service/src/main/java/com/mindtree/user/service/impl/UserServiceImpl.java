package com.mindtree.user.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.mindtree.user.dao.UserDao;
import com.mindtree.user.dto.CartDto;
import com.mindtree.user.dto.OrderModel;
import com.mindtree.user.dto.PaymentDto;
import com.mindtree.user.dto.UserDto;
import com.mindtree.user.entity.User;
import com.mindtree.user.exception.ProxyException;
import com.mindtree.user.exception.UserAppException;
import com.mindtree.user.exception.UserNotFoundException;
import com.mindtree.user.exception.UserProfileNotUpdated;
import com.mindtree.user.exception.ValidationException;
import com.mindtree.user.proxy.OrderProxy;
import com.mindtree.user.proxy.SearchProxy;
import com.mindtree.user.service.UserService;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import feign.FeignException;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private OrderProxy orderProxy;
	
	@Autowired
	private SearchProxy searchProxy;
	
	private ModelMapper model = new ModelMapper();
	
	@Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    private static final String CREATE_ACCOUNT_TOPIC = "addToCart";
    
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


	@Override
	public String updateProfile(UserDto userDto, OAuth2User oAuth2User) {
		logger.info("Processing update profile of logged in user in service layer");
		Optional<User> userFetched = userDao.getUserByEmail(oAuth2User.getAttribute("email"));
		if(userFetched.isPresent()) {
			if(!(userDto.getAddress()==null)) {
			userFetched.get().setAddress(userDto.getAddress());
			}
			if(!(userDto.getPhoneNumber()==0L)) {
			userFetched.get().setPhoneNumber(userDto.getPhoneNumber());
			}
			userDao.persistUser(userFetched.get());
		}
		else {
			logger.info("Processing persisting of logged in user");
			User user = model.map(userDto, User.class);
			user.setUserId(oAuth2User.getName());
			user.setEmail(oAuth2User.getAttribute("email"));
			user.setFirstName(oAuth2User.getAttribute("given_name"));
			user.setLastName(oAuth2User.getAttribute("family_name"));
			userDao.persistUser(user);
		}
		logger.info("User Profile successfullly updated.....!");
		return "User SuccessFully updated.....!";
	}

	@Override
	public Object getUserByEmail(String email) throws UserAppException {
		logger.info("Fetching registered User details  by email ID");
		Optional<User> userFetched = userDao.getUserByEmail(email);
		if(!userFetched.isPresent()) {
			logger.error("Error occured while fetching the user from DB..!");
			throw new UserNotFoundException("User is not Found in database..!");
		}
		return userFetched.get();
	}

	@Override
	public Object addToCart(CartDto cart,OAuth2User oAuth2User) throws UserAppException {
		logger.info("Processing Add to cart...! from user service to order service....");
		checkProfile(oAuth2User.getAttribute("email"));
		cart.setUserId(oAuth2User.getName());
		try
		{
			logger.info("Verifying the availability of the food in restaurant...!");
		searchProxy.verifyFoodAndRestaurant(cart);
		}
		catch (FeignException e) {
			logger.error("Food is not found in that particular retaurant....");
			throw new ProxyException(e.contentUTF8());
		}
			logger.info("Sending Cart details to the KAFKA topic to process...");
			kafkaTemplate.send(CREATE_ACCOUNT_TOPIC,cart);
//		return orderProxy.addToCart(cart);
		return "Food Added to Cart SuccessFully...!";
	}
	
	//For Anonymous User
	@Override
	public Object addToCart(CartDto cart, String phoneNumber) throws UserAppException {
		logger.info("Processing add to cart by Anonymous user...!");
		cart.setUserId(phoneNumber);
		try
		{
			logger.info("Verifying the availability of the food in restaurant...!");
		searchProxy.verifyFoodAndRestaurant(cart);
		}
		catch (FeignException e) {
			logger.error("Food is not found in that particular retaurant....");
			throw new ProxyException(e.contentUTF8());
		}
		logger.info("Sending Cart details to the KAFKA topic to process...");
		kafkaTemplate.send(CREATE_ACCOUNT_TOPIC,cart);
//		return orderProxy.addToCart(cart);
		return "Food Added to Cart SuccessFully...!";
	}
	
	

	@Override
	public Object confirmOrder(PaymentDto paymentDto, OAuth2User oAuth2User) throws UserAppException {
		logger.info("Confirm order in service layer....");
		validatepayment(paymentDto);
		User user = checkProfile(oAuth2User.getAttribute("email"));
		try {
			logger.info("Communicating with Order service for placing an Order....!");
		OrderModel orderResponse = orderProxy.confirmOrderPaynow(paymentDto, oAuth2User.getName(),oAuth2User.getAttribute("given_name"),Long.toString(user.getPhoneNumber())).getBody();
		return orderResponse;
		}catch (FeignException e) {
			logger.error("Error occured while placing an order...!");
				throw new ProxyException(e.contentUTF8());
		}
	}
	
	private void validatepayment(PaymentDto paymentDto) throws UserAppException {
		if(Long.toString(paymentDto.getCardNumber()).length() != 12){
            throw new ValidationException("Debit card number should be 12 digit long");
        }
         if(Integer.toString(paymentDto.getCvv()).length() != 3){
            throw new ValidationException("CVV should be 3 digit long");
         }
         if(Integer.toString(paymentDto.getExpireyMonth()).length() != 2) {
             throw new ValidationException("Month should be 2 digits long");
         }
         if(Integer.toString(paymentDto.getExpiryYear()).length() != 4) {
             throw new ValidationException("Year should be 4 digit long");
         }
	}

	//Anonymous Order
	@Override
	public Object confirmOrder(PaymentDto paymentDto, String phoneNumber, String name, String email) throws UserAppException {
		logger.info("Confirm order in service layer....");
		try {
			logger.info("Communicating with Order service for placing an Order....!");
			OrderModel orderResponse = orderProxy.confirmOrderPaynow(paymentDto, phoneNumber,name,null).getBody();
			return orderResponse;
			}catch (FeignException e) {
				logger.error("Error occured while placing an order...!");
				throw new ProxyException(e.contentUTF8());
			}
	}

	@Override
	public Object confirmOrder(OAuth2User oAuth2User) throws UserAppException {
		logger.info("Confirm order in service layer....");
		User user = checkProfile(oAuth2User.getAttribute("email"));
		try {
			logger.info("Communicating with Order service for placing an Order....!");
		OrderModel orderResponse = orderProxy.confirmOrderPaynow(oAuth2User.getName(),oAuth2User.getAttribute("given_name"),Long.toString(user.getPhoneNumber())).getBody();
		return orderResponse;
	}catch (FeignException e) {
		logger.error("Error occured while placing an order...!");
		throw new ProxyException(e.contentUTF8());
	}
	}
	
	@Override
	public Object confirmOrder(String phoneNumber, String name, String email) throws UserAppException {
		logger.info("Confirm order in service layer....");
		try {
			logger.info("Communicating with Order service for placing an Order....!");
			OrderModel orderResponse = orderProxy.confirmOrderPaynow(phoneNumber,name,null).getBody();
			return orderResponse;
		}catch (FeignException e) {
			logger.error("Error occured while placing an order...!");
			throw new ProxyException(e.contentUTF8());
		}
	}

	
	public User checkProfile(String email) throws UserAppException {
		logger.info("verifying user from DB based on email id of the user.....!");
		Optional<User> userFetched = userDao.getUserByEmail(email);
		if((!userFetched.isPresent()) || (userFetched.get().getAddress()==null) || (userFetched.get().getPhoneNumber()==0L)) {
			logger.error("Error occured as user profile not updated...!");
			throw new UserProfileNotUpdated("First you need to update profile then you can proceed....!");
		}
		return userFetched.get();
	}
	
	



	

}
