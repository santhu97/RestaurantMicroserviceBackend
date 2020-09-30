/**
 * 
 */
package com.mindtree.user.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mindtree.user.dto.CartDto;
import com.mindtree.user.exception.SearchServiceAppException;

/**
 * @author M1056182
 *
 */
//@FeignClient(value = "searchservice",url = "http://localhost:8083/api/v1/search")
@FeignClient("searchservice")
public interface SearchProxy {
	
	@PostMapping("/api/v1/search/restaurant/verify")
	public ResponseEntity<?> verifyFoodAndRestaurant(@RequestBody CartDto cartDto) throws SearchServiceAppException;

}
