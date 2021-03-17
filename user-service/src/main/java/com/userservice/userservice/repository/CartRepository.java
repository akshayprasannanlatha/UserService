package com.userservice.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userservice.userservice.entity.CartEntity;
import com.userservice.userservice.entity.CartId;



public interface CartRepository extends JpaRepository<CartEntity, CartId>{

	List<CartEntity> findByBuyerId(Integer buyerId);

	//CartEntity findByBuyerIdProductId(Integer buyerId, Integer productId);

	

}
