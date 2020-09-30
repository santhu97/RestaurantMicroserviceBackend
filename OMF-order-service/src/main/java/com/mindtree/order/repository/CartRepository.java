/**
 * 
 */
package com.mindtree.order.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.order.entity.Cart;

/**
 * @author M1056182
 *
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{

	@Query(value = "SELECT * FROM omf_order.cart \r\n" + 
			"JOIN omf_order.cart_items ON cart.cart_id = cart_items.cart_cart_id \r\n" + 
			"where cart.user_id=:userId",nativeQuery = true)
	Optional<Cart> fetchUserCart(String userId);


	void deleteByUserId(int cartId);

	@Modifying
    @Transactional
	@Query(value = "DELETE FROM cart WHERE user_id =:userId",nativeQuery = true)
	void deletByUserId(String userId);

}
