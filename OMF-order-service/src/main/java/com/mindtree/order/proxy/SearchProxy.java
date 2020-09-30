package com.mindtree.order.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mindtree.order.dto.FoodDto;
import com.mindtree.order.dto.ResturantDetailsDto;
import com.mindtree.order.exception.SearchServiceAppException;

//@FeignClient(value = "search-service",url = "http://localhost:8083/api/v1/search")
@FeignClient("searchservice")
public interface SearchProxy {
	
	@GetMapping("/api/v1/search/restaurant/{restaurantId}")
	public ResponseEntity<ResturantDetailsDto> getrestaurantById(@PathVariable String restaurantId) throws SearchServiceAppException;


	@PostMapping("/api/v1/search/getTotalCost")
	public ResponseEntity<Double> getTotalCost(@RequestBody List<FoodDto> foodList) throws SearchServiceAppException;

}
