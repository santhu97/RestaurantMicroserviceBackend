/**
 * 
 */
package com.mindtree.order.exception.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindtree.order.controller.OrderController;
import com.mindtree.order.exception.OrderServiceAppException;
import com.mindtree.order.exception.ProxyException;

/**
 * @author M1056182
 *
 */
@RestControllerAdvice(assignableTypes = OrderController.class)
public class SearchControllerExceptionHandler {
	
	@ExceptionHandler(OrderServiceAppException.class)
	public ResponseEntity<Map<String, Object>> searchControllerExceptionHandler(Exception e,Throwable t){
		Map<String, Object> response = new HashMap<>();
		response.put("Error", true);
		response.put("Message", e.getMessage());
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ProxyException.class)
	public ResponseEntity<String> proxyControllerExceptionHandler(Exception e,Throwable t){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}

}
