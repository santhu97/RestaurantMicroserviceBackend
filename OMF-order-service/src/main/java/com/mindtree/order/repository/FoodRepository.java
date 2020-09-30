package com.mindtree.order.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mindtree.order.entity.Cart;
import com.mindtree.order.entity.CartItems;

@Repository
public interface FoodRepository extends JpaRepository<CartItems, Integer>{


}
