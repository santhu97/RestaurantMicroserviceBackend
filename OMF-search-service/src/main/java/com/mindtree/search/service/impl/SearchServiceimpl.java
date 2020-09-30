/**
 * 
 */
package com.mindtree.search.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mindtree.search.dao.SearchDao;
import com.mindtree.search.dto.CartDto;
import com.mindtree.search.dto.FoodDto;
import com.mindtree.search.dto.FoodInCartDto;
import com.mindtree.search.dto.RestuarantDto;
import com.mindtree.search.dto.ResturantDetailsDto;
import com.mindtree.search.entity.Food;
import com.mindtree.search.entity.Restaurant;
import com.mindtree.search.exception.FoodNotFoundException;
import com.mindtree.search.exception.FoodNotInRestaurant;
import com.mindtree.search.exception.ResturantNotFoundException;
import com.mindtree.search.exception.SearchBarException;
import com.mindtree.search.exception.SearchByBudgetException;
import com.mindtree.search.exception.SearchByDistanceException;
import com.mindtree.search.exception.SearchByFoodTypeException;
import com.mindtree.search.exception.SearchByRatingException;
import com.mindtree.search.exception.SearchServiceAppException;
import com.mindtree.search.service.SearchService;

/**
 * @author M1056182
 *
 */
@Service
public class SearchServiceimpl implements SearchService {

	private static final Logger logger = LoggerFactory.getLogger(SearchServiceimpl.class);

	@Autowired
	private SearchDao searchDao;

	ModelMapper model = new ModelMapper();
	
	private double gstTax = 18.00;

	@Override
	public Object searchBasedOnFoodTypes(String foodType) throws SearchServiceAppException {
		logger.info("Proccessing Search by Food Type in Service Layer.....!");
		Optional<List<Restaurant>> restuarantList = searchDao.searchBasedOnFoodTypes(foodType);
		restuarantList.orElseThrow(()->new SearchByFoodTypeException("Currently No Returants Serve with Food Type :"+foodType));
		logger.info("Returned %d Restaurants whith Food Type : {1}",restuarantList.get().size(),foodType);		
		List<RestuarantDto> foodListSearched = restuarantList.get().stream().map(restuarant->fetchRestuarantWithFoodType(restuarant,foodType)).collect(Collectors.toList());
		logger.info("SuccessFully Returned List Of Restaurant..");
		return foodListSearched;
	}


	@Override
	public Object searchByBudget(double budget) throws SearchServiceAppException{
		logger.info("Processing Search Restaurants by Budget in Service Layer...!");
		Optional<List<Restaurant>> resturantList = searchDao.searchByBudget(budget);
		resturantList.orElseThrow(()->new SearchByBudgetException("Currently No Retuarants Serving within Budget "+budget));
		List<RestuarantDto> foodListWithinBudget = resturantList.get().stream().map(restuarant->fetchResturantWithinBudget(restuarant, budget)).collect(Collectors.toList());
		logger.info("SuccessFully Returned List Of Restaurant..");
		return foodListWithinBudget;
	}


	@Override
	public Object searchByRating(double rating) throws SearchServiceAppException{
		if(rating< 0 || rating >5) {
			logger.error("Rating should be within 1 to 5.....!");
			throw new SearchByRatingException("Rating range Should be within 1 to 5.....!");
		}
		Optional<List<Restaurant>> resturantList = searchDao.searchByRating(rating);
		logger.info("Fetching restaurant List within that rating...!");
		resturantList.orElseThrow(()->new SearchByRatingException("OOPS ...! No Restuarant Found with that Rating "+rating));
		return resturantList;
	}


	@Override
	public Object searchBar(String queryValue) throws SearchServiceAppException {
		logger.info("Proceesing search on Query value for matching food name or restaurant name...!");
		Optional<List<Restaurant>> resturantList = searchDao.searchBar(queryValue);
		resturantList.orElseThrow(()-> new SearchBarException("OOPS ....! No Match Found..!"));
		RestuarantDto restuarantDto = new RestuarantDto();
		List<RestuarantDto> restuarantDtoList = new ArrayList<>();
		for (Restaurant restuarant : resturantList.get()) {
			restuarantDto = model.map(restuarant, RestuarantDto.class);
			List<FoodDto> foodDto = new ArrayList<>();
			for (Food food : restuarant.getFoods()) {
				foodDto.add(model.map(food, FoodDto.class));
			}
			restuarantDto.setFoodList(foodDto);
			restuarantDtoList.add(restuarantDto);
		}
		logger.info("Returning a list of restaurants matching query value...!");
		return restuarantDtoList;
	}



	public RestuarantDto fetchResturantWithinBudget(Restaurant restuarant, Double budget) {
		logger.info("Processing search by Budget initated from parent method...");
		RestuarantDto restuarantDto = model.map(restuarant, RestuarantDto.class);
		restuarantDto.setFoodList(null);
		List<FoodDto> foodlist = new ArrayList<>();
		for (Food food : restuarant.getFoods()) {
			if(food.getFoodPrice() <= budget) {
				foodlist.add(model.map(food, FoodDto.class));
			}
		}
		restuarantDto.setFoodList(foodlist);
		logger.info("Fetching all the restaurants within the budget..!");
		return restuarantDto;
	}

	public RestuarantDto fetchRestuarantWithFoodType(Restaurant restuarant,String foodType) {
		RestuarantDto restuarantDto = model.map(restuarant, RestuarantDto.class);
		restuarantDto.setFoodList(null);
		List<FoodDto> foodlist = new ArrayList<>();
		for (Food food : restuarant.getFoods()) {
			if(food.getFoodType().equalsIgnoreCase(foodType)) {
				foodlist.add(model.map(food, FoodDto.class));
			}
		}
		restuarantDto.setFoodList(foodlist);
		return restuarantDto;
	}


	@Override
	public Object searchByDistance(double distance, double lattitute, double logitude) throws SearchServiceAppException {
		logger.info("Proceesing searching restaurant by distance wrt,,cureet loctation of the user..!");
		List<Restaurant> listOfRestuarants = searchDao.fetchRestuarants();
		List<RestuarantDto> listOfRestuarantDto = new ArrayList<>();
		if(listOfRestuarants.isEmpty()) {
			logger.error("Currently No Restaurants are being registered in our APP..!");
			throw new SearchByDistanceException("Currently No restuarants registered in Our APP....!");
		}
		for (Restaurant restaurant : listOfRestuarants) {
			double dist = distance(lattitute, restaurant.getLocation().getLattitude(), logitude, restaurant.getLocation().getLongitude());
			if(dist < distance) {
				listOfRestuarantDto.add(model.map(restaurant, RestuarantDto.class));
			}
		}
		if(listOfRestuarantDto.isEmpty()) {
			logger.error("No Restaurants are found near by user location...!");
			throw new SearchByDistanceException("OOPS.....! NO Returants Found within "+distance+" KM Range..... Try with different range of distance");
		}
		return listOfRestuarantDto;
	}

	public double distance(double lattitude1, double lattitude2, double longitude1, double longitude2) 
	{ 
		longitude1 = Math.toRadians(longitude1); 
		longitude2 = Math.toRadians(longitude2); 
		lattitude1 = Math.toRadians(lattitude1); 
		lattitude2 = Math.toRadians(lattitude2); 

		// Haversine formula  
		double dlon = longitude2 - longitude1;  
		double dlat = lattitude2 - lattitude1; 
		double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lattitude1) * Math.cos(lattitude2) * Math.pow(Math.sin(dlon / 2),2); 
		double cal_dist = 2 * Math.asin(Math.sqrt(a)); 

		// Radius of earth in kilometers. Use 3956  
		// for miles 
		double earth_radius = 6371; 

		// calculate the result 
		return(cal_dist * earth_radius); 
	}


	@Override
	public ResturantDetailsDto getrestaurantById(String restaurantId) throws SearchServiceAppException {
		Optional<Restaurant> restaurant = searchDao.fetchRestuarantsById(restaurantId);
		restaurant.orElseThrow(()->new ResturantNotFoundException("Restuarant Not Found with Restuarant ID: "+restaurantId));
		ResturantDetailsDto restuarntDetails = new ResturantDetailsDto();
		restuarntDetails.setRestuarantName(restaurant.get().getRestaurantName());
		restuarntDetails.setRestaurantAddress(restaurant.get().getLocation().getAddress());
		restuarntDetails.setTimePerKm(restaurant.get().getTimePerkm());
		return restuarntDetails;
	}


	@Override
	public Double getTotalCost(List<FoodInCartDto> foodList) throws SearchServiceAppException {
		logger.info("Fetching the cart total value from food Repo..!");
		double totalValue = 0.0;
		double taxedAmount;
		for (FoodInCartDto foodInCartDto : foodList) {
			Optional<Food> food = searchDao.fetchFoodItem(foodInCartDto.getFoodId());
			food.orElseThrow(()-> new FoodNotFoundException("Food Not Found With Id :"+food.get().getFoodId()));
			totalValue += food.get().getFoodPrice()*foodInCartDto.getQuantity();
		}
		//Calculate TAX Amount
		taxedAmount = (gstTax/100.00) * (totalValue);
		System.err.println(totalValue+taxedAmount);
		return totalValue+taxedAmount;
	}


	@Override
	public CartDto verifyFoodAndRestaurant(CartDto cartDto) throws SearchServiceAppException {
		Optional<Restaurant> restaurant = searchDao.fetchRestuarantsById(""+cartDto.getRestaurantId());
		restaurant.orElseThrow(()->new ResturantNotFoundException("Restuarant Not Found with Restuarant ID: "+cartDto.getRestaurantId()));
		int count = 0; 
		List<Integer> foodInRestaurant = new ArrayList<>();
		foodInRestaurant = restaurant.get().getFoods().stream().map(x -> x.getFoodId()).collect(Collectors.toList());
		
		for (FoodInCartDto food : cartDto.getFoodList()) {
			if(!foodInRestaurant.contains(food.getFoodId())) {
				System.out.println(food.getFoodId());
				logger.error("Food NotIn Restaurant exception thrown... caz food is not peresent in that rstaurant..");
				throw new FoodNotInRestaurant(String.format("Food id %d is not present in restaurant",food.getFoodId()));
			}
		}
		return cartDto;
	}  






}
