/**
 * 
 */
package com.mindtree.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.order.entity.Order;

/**
 * @author M1056182
 *
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

}
