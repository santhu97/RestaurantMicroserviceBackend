/**
 * 
 */
package com.mindtree.search.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.search.entity.Location;

/**
 * @author M1056182
 *
 */
@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

}
