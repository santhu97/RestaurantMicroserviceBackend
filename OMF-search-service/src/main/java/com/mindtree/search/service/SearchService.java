package com.mindtree.search.service;

import java.util.List;

import com.mindtree.search.dto.CartDto;
import com.mindtree.search.dto.FoodDto;
import com.mindtree.search.dto.FoodInCartDto;
import com.mindtree.search.dto.ResturantDetailsDto;
import com.mindtree.search.exception.SearchServiceAppException;

public interface SearchService {

	Object searchBasedOnFoodTypes(String foodType) throws SearchServiceAppException;

	Object searchByBudget(double budget) throws SearchServiceAppException;

	Object searchByRating(double rating) throws SearchServiceAppException;

	Object searchBar(String queryValue) throws SearchServiceAppException;

	Object searchByDistance(double distance, double lattitute, double logitude) throws SearchServiceAppException;

	ResturantDetailsDto getrestaurantById(String restaurantId) throws SearchServiceAppException;

	Double getTotalCost(List<FoodInCartDto> foodList) throws SearchServiceAppException;

	CartDto verifyFoodAndRestaurant(CartDto cartDto) throws SearchServiceAppException;


}
