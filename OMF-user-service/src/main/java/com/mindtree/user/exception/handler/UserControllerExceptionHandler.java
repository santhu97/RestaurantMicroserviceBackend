/**
 * 
 */
package com.mindtree.user.exception.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindtree.user.controller.UserController;
import com.mindtree.user.exception.ProxyException;
import com.mindtree.user.exception.UserAppException;

/**
 * @author M1056182
 *
 */
@RestControllerAdvice(assignableTypes = UserController.class)
public class UserControllerExceptionHandler {
	
	@ExceptionHandler(UserAppException.class)
	public ResponseEntity<Map<String, Object>> userControllerExceptionHandler(Exception e,Throwable t){
		Map<String, Object> response = new HashMap<>();
		response.put("Error", true);
		response.put("Message", e.getMessage());
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ProxyException.class)
	public ResponseEntity<?> proxyExceptionHandler(Exception e,Throwable t){
		return new ResponseEntity<>(e.getLocalizedMessage(),HttpStatus.NOT_FOUND);
	}

}
