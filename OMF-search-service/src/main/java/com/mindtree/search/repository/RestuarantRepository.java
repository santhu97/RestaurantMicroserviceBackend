/**
 * 
 */
package com.mindtree.search.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mindtree.search.entity.Restaurant;

/**
 * @author M1056182
 *
 */
@Repository
public interface RestuarantRepository extends JpaRepository<Restaurant, String>{

	@Query(value = "SELECT DISTINCT r.* FROM restuarant r JOIN restaurant_food rf ON r.restaurant_id = rf.restaurant_id JOIN food f ON rf.food_id = f.food_id WHERE f.food_type=:foodType",nativeQuery = true)
	Optional<List<Restaurant>> findByFoodType(String foodType);

	@Query(value = "SELECT DISTINCT r.* FROM restuarant r JOIN restaurant_food rf ON r.restaurant_id = rf.restaurant_id JOIN food f ON rf.food_id = f.food_id WHERE f.food_price < :budget",nativeQuery = true)
	Optional<List<Restaurant>> searchByBudget(double budget);

	@Query(value = "SELECT DISTINCT * FROM restuarant where rating >= :rating",nativeQuery = true)
	Optional<List<Restaurant>> searchByRating(double rating);

	@Query(value = "SELECT DISTINCT r.* FROM restuarant r JOIN restaurant_food rf ON r.restaurant_id = rf.restaurant_id JOIN food f ON rf.food_id = f.food_id WHERE f.food_name LIKE %:queryValue% or r.restaurant_name LIKE %:queryValue%",nativeQuery = true)
	Optional<List<Restaurant>> searchBar(@Param("queryValue")String queryValue);

	@Query(value = "SELECT * FROM restuarant",nativeQuery = true)
	List<Restaurant> findAllRestuarants();

}
