/**
 * 
 */
package com.mindtree.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.user.dto.CartDto;
import com.mindtree.user.dto.PaymentDto;
import com.mindtree.user.dto.UserDto;
import com.mindtree.user.exception.UserAppException;
import com.mindtree.user.service.UserService;
import com.netflix.client.ClientException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.exception.HystrixTimeoutException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author M1056182
 *
 */
@RestController
@Api(value = "Search Service", description = "List of Services available for user information.", tags = { "User  Service" })
@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);


	@GetMapping({"/home","/"})
	public String hello() {
		return " <h1> Welcome All to MINDTREE Restaurant  and CAFE <h1> <div/>";
	}

	@ApiOperation(value = " Update The Google Logged in Customer Details....", tags = {" Update Profile"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Updated User related information"),
			@ApiResponse(code = 400,message = "something went wrong Please Sign In through Google First..") })
	@PostMapping("/UpdateProfile")
	public ResponseEntity<String> updateProfile(@RequestBody UserDto userDto, @AuthenticationPrincipal OAuth2User oAuth2User){
		logger.info("USER (Google logged) is trying update his profile....");
		return new ResponseEntity<String>(userService.updateProfile(userDto,oAuth2User),HttpStatus.OK);
	}

	@ApiOperation(value = " Fetching Customer Details by customer EMAIL....")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Fetched User related information"),
			@ApiResponse(code = 400,message = "something went wrong ..") })
	@GetMapping("/getUserByEmail/{email}")
	public ResponseEntity<?> getUserByEmail(@PathVariable String email) throws UserAppException{
		return new ResponseEntity<>(userService.getUserByEmail(email),HttpStatus.OK);
	}

	/**
	 * Food Order and Cart For registered users
	 *
	 */

	@ApiOperation(value = " Add Food to Cart ....", tags = {"Order"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Added Into Cart....."),
			@ApiResponse(code = 400,message = "something went wrong ..") })
	@PostMapping("/addToCart")
	public ResponseEntity<?> addToCart(@RequestBody CartDto cart,@AuthenticationPrincipal OAuth2User oAuth2User) throws UserAppException{
		logger.info("Logged in user Adding food Item to respective cart...!");
		return new ResponseEntity<>(userService.addToCart(cart,oAuth2User),HttpStatus.OK);
	}

	@ApiOperation(value = " Confirm Order with Pay Now Option ....", tags = {"Order"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Order Confirmation SuccessFull...."),
			@ApiResponse(code = 400,message = "something went wrong ..") })
	@HystrixCommand(fallbackMethod = "confirmOrderPaynowMissed", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "2000")})
	@PostMapping("/confirmOrderPaynow")
	public ResponseEntity<?> confirmOrderPaynow(@RequestBody PaymentDto paymentDto,@AuthenticationPrincipal OAuth2User oAuth2User) throws UserAppException{
		logger.info("Logged in user is confirming the order BY choosing pay now option...!");
		return new ResponseEntity<>(userService.confirmOrder(paymentDto,oAuth2User),HttpStatus.OK);
	}


	@ApiOperation(value = " Confirm Order with Pay Later Option ....", tags = {"Order"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Order Confirmation SuccessFull...."),
			@ApiResponse(code = 400,message = "something went wrong ..") })
	@HystrixCommand(fallbackMethod = "confirmOrderPayLaterMissed", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "2000")})
	@PostMapping("/confirmOrderPayLater")
	public ResponseEntity<?> confirmOrderPaynow(@AuthenticationPrincipal OAuth2User oAuth2User) throws UserAppException{
		logger.info("Logged in user is confirming order by choosing pay later option..!");
		return new ResponseEntity<>(userService.confirmOrder(oAuth2User),HttpStatus.OK);
	}

	/**
	 * Anonymous User Operations
	 *
	 */

	@ApiOperation(value = " Add Food to Cart ....", tags = {"Anonymous User Order"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Added Into Cart....."),
			@ApiResponse(code = 400,message = "something went wrong ..") })
	@PostMapping("/anonymous/addToCart/{phoneNumber}")
	public ResponseEntity<?> anonymousAddToCart(@RequestBody CartDto cart,@PathVariable String phoneNumber) throws UserAppException{
		logger.info("ANONYMOUS user is trying to add into cart by giving a Phone number as refernce..!");
		return new ResponseEntity<>(userService.addToCart(cart,phoneNumber),HttpStatus.OK);
	}

	@ApiOperation(value = " Confirm Order with Pay Now Option ....", tags = {"Anonymous User Order"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Order Confirmation SuccessFull...."),
			@ApiResponse(code = 400,message = "something went wrong ..") })
	@HystrixCommand(fallbackMethod = "confirmOrderPayNowMissedAnonymous",commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "2000")})
	@PostMapping("/anonymous/confirmOrderPaynow/{phoneNumber}/{name}/{email}")
	public ResponseEntity<?> confirmOrderPaynow(@RequestBody PaymentDto paymentDto,@PathVariable String phoneNumber,@PathVariable String name,@PathVariable String email) throws UserAppException{
		logger.info("ANONYMOUS user is trying to confirm ordre by choosing pay now option..!");
		return new ResponseEntity<>(userService.confirmOrder(paymentDto,phoneNumber,name,email),HttpStatus.OK);
	}

	@ApiOperation(value = " Confirm Order with Pay Later Option ....", tags = {"Anonymous User Order"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Order Confirmation SuccessFull...."),
			@ApiResponse(code = 400,message = "something went wrong ..") })
	@HystrixCommand(fallbackMethod = "confirmOrderPayLaterMissedAnonymous",commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "2000")})
	@PostMapping("/anonymous/confirmOrderPayLater/{phoneNumber}/{name}/{email}")
	public ResponseEntity<?> confirmOrderPayLater(@PathVariable String phoneNumber,@PathVariable String name,@PathVariable String email) throws UserAppException{
		logger.info("ANONYMOUS user is confirming order by choosing pay later option..!");
		return new ResponseEntity<>(userService.confirmOrder(phoneNumber,name,email),HttpStatus.OK);
	}

	/**
	 * Hystrix FallBack Methods
	 *
	 */
	
	
	
	public ResponseEntity<?> confirmOrderPaynowMissed(@RequestBody PaymentDto paymentDto,@AuthenticationPrincipal OAuth2User oAuth2User,Throwable throwable) throws UserAppException{
		if( (throwable instanceof HystrixTimeoutException)  || (throwable instanceof RuntimeException)){
			logger.error("Request Time Out Occured from order Service.. ");
			return new ResponseEntity<>("Request Time Out Please Try After some time...!",
					HttpStatus.REQUEST_TIMEOUT);
		}
		return new ResponseEntity<>(throwable.getLocalizedMessage(),HttpStatus.GATEWAY_TIMEOUT);

	}
	
	public ResponseEntity<?> confirmOrderPayLaterMissed(@AuthenticationPrincipal OAuth2User oAuth2User,Throwable throwable) throws UserAppException{
		if ((throwable instanceof HystrixTimeoutException)  || (throwable instanceof RuntimeException)) {
			logger.error("Request timed out occured from order service...!");
			return new ResponseEntity<>("Request Time Out Please Try After some time...!",
					HttpStatus.REQUEST_TIMEOUT);
		}
		return new ResponseEntity<>(throwable.getLocalizedMessage(),HttpStatus.GATEWAY_TIMEOUT);

	}


	public ResponseEntity<?> confirmOrderPayNowMissedAnonymous(@RequestBody PaymentDto paymentDto,@PathVariable String phoneNumber,@PathVariable String name,@PathVariable String email,Throwable throwable) throws UserAppException{
		if( (throwable instanceof HystrixTimeoutException) || (throwable instanceof RuntimeException)){
			logger.info("Request timed out occured from order service.. ");
			return new ResponseEntity<>("Request Time Out Please Try After some time...!",
					HttpStatus.REQUEST_TIMEOUT);

		}
		return new ResponseEntity<>(throwable.getLocalizedMessage(),HttpStatus.GATEWAY_TIMEOUT);
	}
	
	public ResponseEntity<?> confirmOrderPayLaterMissedAnonymous(@PathVariable String phoneNumber,@PathVariable String name,@PathVariable String email,Throwable throwable) throws UserAppException{
		if( (throwable instanceof HystrixTimeoutException) || (throwable instanceof RuntimeException)){
			logger.info("Request timed out occured from order service.. ");
			return new ResponseEntity<>("Request Time Out Please Try After some time...!",
					HttpStatus.REQUEST_TIMEOUT);

		}
		return new ResponseEntity<>(throwable.getLocalizedMessage(),HttpStatus.GATEWAY_TIMEOUT);
	}
	

}





