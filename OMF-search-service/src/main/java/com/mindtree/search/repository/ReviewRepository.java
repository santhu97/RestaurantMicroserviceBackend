/**
 * 
 */
package com.mindtree.search.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.search.entity.Review;

/**
 * @author M1056182
 *
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

}
