/**
 * 
 */
package com.mindtree.user.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mindtree.user.dto.CartDto;
import com.mindtree.user.dto.OrderModel;
import com.mindtree.user.dto.PaymentDto;
import com.mindtree.user.exception.OrderServiceAppException;

/**
 * @author M1056182
 *
 */
//@FeignClient(value = "orderservice",url = "http://localhost:8084/api/v1/order")
@FeignClient("orderservice")
public interface OrderProxy {
	
	@PostMapping("/api/v1/order/addToCart")
	public ResponseEntity<?> addToCart(@RequestBody CartDto cart) throws OrderServiceAppException;
	
	@PostMapping("/api/v1/order/confirmOrderPaynow/{userId}/{userName}/{phone}")
	public ResponseEntity<OrderModel> confirmOrderPaynow(@RequestBody PaymentDto paymentDto,@PathVariable String userId,@PathVariable String userName,@PathVariable String phone) throws OrderServiceAppException;
	
	
	@PostMapping("/api/v1/order/confirmOrderPayLater/{userId}/{userName}/{phone}")
	public ResponseEntity<OrderModel> confirmOrderPaynow(@PathVariable String userId,@PathVariable String userName,@PathVariable String phone) throws OrderServiceAppException;

}
