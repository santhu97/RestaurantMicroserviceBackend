/**
 * 
 */
package com.mindtree.order.controller;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.order.dto.CartDto;
import com.mindtree.order.dto.PaymentDto;
import com.mindtree.order.entity.Order;
import com.mindtree.order.exception.OrderServiceAppException;
import com.mindtree.order.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author M1056182
 *
 */
@RestController
@Api(value = "Order Service", description = "List of Services available for Ordering a food.", tags = { "Order Service" })
@RequestMapping("/api/v1/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	
	@ApiOperation(value = " Add Food to Cart ....", tags = {"Add To Cart"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Added Into Cart....."),
			@ApiResponse(code = 400,message = "something went wrong ..") })
	@PostMapping("/addToCart")
	public ResponseEntity<?> addToCart(@RequestBody CartDto cart) throws Exception{
		return new ResponseEntity<>(orderService.addToCart(cart),HttpStatus.OK);
	}
	
	

	@ApiOperation(value = " confirm order with PAyment....", tags = {"cofirm order with Pay Now"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully ordered food...."),
			@ApiResponse(code = 400,message = "something went wrong ..") })
	@PostMapping("/confirmOrderPaynow/{userId}/{userName}/{phone}")
	public ResponseEntity<?> confirmOrderPaynow(@RequestBody PaymentDto paymentDto,@PathVariable String userId,@PathVariable String userName,@PathVariable String phone) throws OrderServiceAppException, MessagingException, IOException{
		return new ResponseEntity<>(orderService.confirmOrder(paymentDto,userId,userName,phone),HttpStatus.OK);
	}
	
	

	@ApiOperation(value = " confirm order with out PAyment....", tags = {"cofirm order with Pay Later"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully ordered food with Cash on Delivery...."),
			@ApiResponse(code = 400,message = "something went wrong ..") })
	@PostMapping("/confirmOrderPayLater/{userId}/{userName}/{phone}")
	public ResponseEntity<?> confirmOrderPaynow(@PathVariable String userId,@PathVariable String userName,@PathVariable String phone) throws OrderServiceAppException{
		return new ResponseEntity<>(orderService.confirmOrder(userId,userName,phone),HttpStatus.OK);
	}
	

}
