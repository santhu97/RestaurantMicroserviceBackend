package com.mindtree.order.serviceimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.order.dao.OrderDao;
import com.mindtree.order.dto.CartDto;
import com.mindtree.order.dto.FoodDto;
import com.mindtree.order.dto.PaymentDto;
import com.mindtree.order.dto.ResturantDetailsDto;
import com.mindtree.order.entity.Cart;
import com.mindtree.order.entity.CartItems;
import com.mindtree.order.entity.Order;
import com.mindtree.order.entity.Order.PaymentStatus;
import com.mindtree.order.exception.OrderServiceAppException;
import com.mindtree.order.exception.ProxyException;
import com.mindtree.order.exception.RestaurantMismatchException;
import com.mindtree.order.exception.SearchServiceAppException;
import com.mindtree.order.exception.UserCartNoFoundException;
import com.mindtree.order.proxy.SearchProxy;
import com.mindtree.order.service.OrderService;
import com.mindtree.order.util.DeliveryPerson;

import feign.FeignException;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private SearchProxy searchProxy;
	
	private ModelMapper model = new ModelMapper();
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	Cart cart;
	
	@KafkaListener(topics = "addToCart", groupId = "group_id")
	public void addToCartFromKafka(String byteData) throws Exception {
		try {
			CartDto cartDto = objectMapper.readValue(byteData, CartDto.class);
			System.err.println(cartDto);
			addToCart(cartDto);
		} catch (JsonProcessingException | ServiceException ex) {
			System.out.println("Error occured while adding to cart ::" + ex.getMessage());
		}
	}
	@Override
	public Object addToCart(CartDto cartDto) throws OrderServiceAppException {
		Optional<Cart> userCart = orderDao.getUserCart(cartDto.getUserId());
		Double totalCartValue = 0.0;
		List<CartItems> userFoodList = new ArrayList<>();
		if(!userCart.isPresent()){
			cart = new Cart();
			cart.setCartValue(0.0); 
			cart.setRestaurantId(cartDto.getRestaurantId());
			cart.setUserId(cartDto.getUserId());
			try {
				totalCartValue = searchProxy.getTotalCost(cartDto.getFoodList()).getBody();
				}catch(FeignException e) {
					throw new ProxyException(e.contentUTF8());
				}
			cart.setCartValue(totalCartValue);
			for (FoodDto newFood : cartDto.getFoodList()) {
				userFoodList.add(model.map(newFood, CartItems.class));
			}
			for (CartItems cartItems : userFoodList) {
				cartItems.setCart(cart);
			}
			cart.setFoodList(userFoodList);
			orderDao.persistCart(cart);
			return "Cart SuccessFully Saved";
		}       
		else {
			userFoodList = userCart.get().getFoodList();
			if(cartDto.getRestaurantId() != userCart.get().getRestaurantId()) {
				throw new RestaurantMismatchException("Cannot add from diifrent restaurants at a time.... Please proceed with order for old cart then try..!");
			}
			try {
				totalCartValue = searchProxy.getTotalCost(cartDto.getFoodList()).getBody();
				}catch(FeignException e) {
					throw new ProxyException(e.contentUTF8());
				}
			userCart.get().setCartValue(userCart.get().getCartValue() + totalCartValue);
			for (CartItems cartItems : userCart.get().getFoodList()) {
				for (FoodDto newFood : cartDto.getFoodList()) {
					if(newFood.getFoodId()==cartItems.getFoodId()) {
						cartItems.setQuantity(cartItems.getQuantity() + newFood.getQuantity());
					}
					else {
						userFoodList.add(model.map(newFood, CartItems.class));
					}
				}
			}
			userCart.get().setFoodList(userFoodList);
			orderDao.updateCart(userCart.get());
		}
		return cartDto;
	}
	
	

	@Override
	public Order confirmOrder(PaymentDto paymentDto, String userId,String userName,String phone) throws OrderServiceAppException, MessagingException {
		Order order = getCart(userId);
		order.setIsPaymentDone(PaymentStatus.PAY_NOW);
		order.setUserName(userName);
		deleteCartUponSuccessOrder(userId);
		order= orderDao.persistOrder(order);
		return order;
	}
	

	@Override
	public Order confirmOrder(String userId, String userName,String phone) throws OrderServiceAppException {
		Order order = getCart(userId);
		order.setIsPaymentDone(PaymentStatus.PAY_LATER);
		order.setUserName(userName);
		deleteCartUponSuccessOrder(userId);
		order = orderDao.persistOrder(order);
		return order;
	}
	
	public void deleteCartUponSuccessOrder(String userId) {
		Optional<Cart> userCart = orderDao.getUserCart(userId);
		orderDao.deleteCartUponSuccessOrder(userCart.get().getCartId());
	}

	
	public Order getCart(String userId) throws OrderServiceAppException {
		Optional<Cart> userCart = orderDao.getUserCart(userId);
		userCart.orElseThrow(()->new UserCartNoFoundException("Cart Is Empty Cannot process Order Please Add food to cart then confirm order...!"));
		Order order = new Order();
		DeliveryPerson delivery = new DeliveryPerson();
		order.setUserId(userId);
		order.setOrderedDate(LocalDateTime.now());
		order.setTotalPrice(userCart.get().getCartValue());
		try {
		ResturantDetailsDto resturantDetails = searchProxy.getrestaurantById(Integer.toString(userCart.get().getRestaurantId())).getBody();
		order.setRestaurantId(Integer.toString(userCart.get().getRestaurantId()));
		order.setRestaurantName(resturantDetails.getRestuarantName());
		order.setRestaurantAddress(resturantDetails.getRestaurantAddress());
		}
		catch (FeignException e) {
			throw new OrderServiceAppException(e.contentUTF8());
		}
		
		order.setDeliveryPerson(delivery.assignDeliverPerson());
		return order;

	}
	

	



	

}
