/**
 * 
 */
package com.mindtree.search.dao;

import java.util.List;
import java.util.Optional;

import com.mindtree.search.entity.Food;
import com.mindtree.search.entity.Restaurant;

/**
 * @author M1056182
 *
 */
public interface SearchDao {

	Optional<List<Restaurant>> searchBasedOnFoodTypes(String foodType);

	Optional<List<Restaurant>> searchByBudget(double budget);

	Optional<List<Restaurant>> searchByRating(double rating);

	Optional<List<Restaurant>> searchBar(String queryValue);

	List<Restaurant> fetchRestuarants();

	Optional<Restaurant> fetchRestuarantsById(String restaurantId);

	Optional<Food> fetchFoodItem(int foodId);

}
