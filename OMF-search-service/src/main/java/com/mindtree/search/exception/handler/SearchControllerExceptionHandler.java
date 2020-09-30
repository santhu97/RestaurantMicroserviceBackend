/**
 * 
 */
package com.mindtree.search.exception.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindtree.search.controller.SearchController;
import com.mindtree.search.exception.SearchServiceAppException;

/**
 * @author M1056182
 *
 */
@RestControllerAdvice(assignableTypes = SearchController.class)
public class SearchControllerExceptionHandler {
	
	@ExceptionHandler(SearchServiceAppException.class)
	public ResponseEntity<Map<String, Object>> searchControllerExceptionHandler(Exception e,Throwable t){
		Map<String, Object> response = new HashMap<>();
		response.put("Error", true);
		response.put("Message", e.getMessage());
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}

}
