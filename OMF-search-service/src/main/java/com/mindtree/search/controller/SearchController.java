/**
 * 
 */
package com.mindtree.search.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.search.dto.CartDto;
import com.mindtree.search.dto.FoodInCartDto;
import com.mindtree.search.dto.ResturantDetailsDto;
import com.mindtree.search.exception.SearchServiceAppException;
import com.mindtree.search.service.SearchService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author M1056182
 *
 **/

@RestController
@Api(value = "Search Service", description = "List of Services available for getting restaurants and item information.", tags = { "Search Service" })
@RequestMapping("/api/v1/search/")
public class SearchController {
	
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
	
	@Autowired
	private SearchService searchService;
	
	@ApiOperation(value = " Search Restuarant By Food Type....", tags = {"Search"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Fetched Retuarants having Food Type"),
			@ApiResponse(code = 400,message = "something went wrong ..") })
	@Cacheable(value = "foodlist",key = "#foodType")
	@GetMapping("/foodType/{foodType}")
	public ResponseEntity<?> searchBasedOnFoodTypes(@PathVariable("foodType") String foodType) throws SearchServiceAppException{
		logger.info(String.format("Processing Search By Food Type  - %s",foodType));
		return new ResponseEntity<>(searchService.searchBasedOnFoodTypes(foodType),HttpStatus.OK);
	}
	
	@ApiOperation(value = " Search Restuarant By Budget....",tags = {"Search"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Fetched Retuarants within Budget..."),
			@ApiResponse(code = 400,message = "something went wrong ..") })
	@Cacheable(value = "foodlist",key = "#budget")
	@GetMapping("/budget/{budget}")
	public ResponseEntity<?> searchByBudget(@PathVariable double budget) throws SearchServiceAppException{
		logger.info(String.format("Processing search by food budget under %s",budget));
		return new ResponseEntity<>(searchService.searchByBudget(budget),HttpStatus.OK);
	}
	
	@ApiOperation(value = " Search Restuarant By Delivery Rating....",tags = {"Search"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Fetched Retuarants around Delivery Rating..."),
			@ApiResponse(code = 400,message = "something went wrong ..") })
	@Cacheable(value = "foodlist",key = "#rating")
	@GetMapping("/rating/{rating}")
	public ResponseEntity<?> searchByRating(@PathVariable double rating) throws SearchServiceAppException{
		logger.info("Processing search by Rating :"+rating);
		return new ResponseEntity<>(searchService.searchByRating(rating),HttpStatus.OK);
	}
	
	@ApiOperation(value = " Search Restuarant By Name or food Name ....",tags = {"Search"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Fetched Retuarants Matching Restuarant Name or Food Name..."),
			@ApiResponse(code = 400,message = "something went wrong ..") })
	@GetMapping("/searchBar/{queryValue}")
	public ResponseEntity<?> searchBar(@PathVariable String queryValue) throws SearchServiceAppException{
		logger.info("Processing search by Food Type or Restuarant Name :"+queryValue);
		return new ResponseEntity<>(searchService.searchBar(queryValue),HttpStatus.OK);
	}
	
	@ApiOperation(value = " Search Restuarant By Distance ....",tags = {"Search"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Fetched Retuarants within a range of distance..."),
			@ApiResponse(code = 400,message = "something went wrong ..") })
	@GetMapping("/distance/{distance}")
	public ResponseEntity<?> searchByDistance(@PathVariable double distance,@RequestParam double lattitute,@RequestParam double logitude) throws SearchServiceAppException{
		logger.info("Processing search by Distance of restuarant from user Location ");
		return new ResponseEntity<>(searchService.searchByDistance(distance,lattitute,logitude),HttpStatus.OK);
	}
	
	@ApiOperation(value = " Search Restuarant Restaurant ID ....",tags = {"proxy"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Fetched Retuarants within restaurant ID..."),
			@ApiResponse(code = 400,message = "something went wrong ..") })
	@Cacheable(value = "foodlist",key = "#restaurantId")
	@GetMapping("/restaurant/{restaurantId}")
	public ResponseEntity<ResturantDetailsDto> getrestaurantById(@PathVariable String restaurantId) throws SearchServiceAppException{
		logger.warn("Processing fetching restuarant by Id - Initiated from Order-Service..!");
		return new ResponseEntity<>(searchService.getrestaurantById(restaurantId),HttpStatus.OK);
	}
	
	@ApiOperation(value = " Fetch total total value for list of Food List ....",tags = {"proxy"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Fetched total value for list of Food List..."),
			@ApiResponse(code = 400,message = "something went wrong ..") })
	@PostMapping("/getTotalCost")
	public ResponseEntity<Double> getTotalCost(@RequestBody List<FoodInCartDto> foodList) throws SearchServiceAppException{
		logger.warn("Processing Calculating Total cast of cart INCL Tax-GST..- Inititaed from Order-Service.");
		return new ResponseEntity<>(searchService.getTotalCost(foodList),HttpStatus.OK);
	}
	
	
	@ApiOperation(value = " Varify Food with respective Restaurant ....",tags = {"proxy"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Fetched Retuarants within restaurant ID..."),
			@ApiResponse(code = 400,message = "something went wrong ..") })
	@PostMapping("/restaurant/verify")
	public ResponseEntity<CartDto> varifyFoodAndRestaurant(@RequestBody CartDto cartDto) throws SearchServiceAppException{
		logger.warn("Processing Verifying food with restaurant - Initiated from User-service ..!");
		return new ResponseEntity<CartDto>(searchService.verifyFoodAndRestaurant(cartDto),HttpStatus.OK);
	}
	
	
}
