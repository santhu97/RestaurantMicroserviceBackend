/**
 * 
 */
package com.mindtree.search.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.search.entity.Food;

/**
 * @author M1056182
 *
 */
@Repository
public interface FoodRepository extends JpaRepository<Food, Integer>{

	Optional<Food> findById(int foodId);

//	Optional<Food> findByType(String foodType);

}
