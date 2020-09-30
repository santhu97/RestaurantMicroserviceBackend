/**
 * 
 */
package com.mindtree.search.dao.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.search.dao.SearchDao;
import com.mindtree.search.entity.Food;
import com.mindtree.search.entity.Restaurant;
import com.mindtree.search.repository.FoodRepository;
import com.mindtree.search.repository.RestuarantRepository;
import com.mindtree.search.service.SearchService;
import com.mindtree.search.service.impl.SearchServiceimpl;

/**
 * @author M1056182
 *
 */
@Service
public class SearchDaoImpl implements SearchDao{
	
	private static final Logger logger = LoggerFactory.getLogger(SearchDaoImpl.class);

	
	@Autowired
	private RestuarantRepository restuarantRepo;
	
	
	@Autowired
	private FoodRepository foodRepo;

	@Override
	public Optional<List<Restaurant>> searchBasedOnFoodTypes(String foodType) {
		logger.info("Fetchig restaurant based on food type from Restaurant Repo..!");
		return restuarantRepo.findByFoodType(foodType);
		
	}

	@Override
	public Optional<List<Restaurant>> searchByBudget(double budget) {
		logger.info("Fetching restaurant based in Budget from restaurant repository...!");
		return restuarantRepo.searchByBudget(budget);
	}

	@Override
	public Optional<List<Restaurant>> searchByRating(double rating) {
		logger.info("Fetching restaurant based in rating from restaurant repository...!");
		return restuarantRepo.searchByRating(rating);
	}

	@Override
	public Optional<List<Restaurant>> searchBar(String queryValue) {
		logger.info("Fetching restaurant Query Value in Budget from restaurant repository...!");
		return restuarantRepo.searchBar(queryValue);
	}

	@Override
	public List<Restaurant> fetchRestuarants() {
		logger.info("Fetching all restaurant  from restaurant repository...!");
		return restuarantRepo.findAllRestuarants();
	}

	@Override
	public Optional<Restaurant> fetchRestuarantsById(String restaurantId) {
		logger.info("Fetching restaurant by restauranrt ID from restaurant repository...!");
		return restuarantRepo.findById(restaurantId);
	}

	@Override
	public Optional<Food> fetchFoodItem(int foodId) {
		logger.info("Fetching Food based on food ID from food repository...!");
		return foodRepo.findById(foodId);
	}


	

	

}
