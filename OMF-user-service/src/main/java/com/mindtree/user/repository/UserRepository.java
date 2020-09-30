/**
 * 
 */
package com.mindtree.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mindtree.user.entity.User;

/**
 * @author M1056182
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	@Query(value = "SELECT * FROM user WHERE email=:email",nativeQuery = true)
	Optional<User> findByEmail(String email);

}
