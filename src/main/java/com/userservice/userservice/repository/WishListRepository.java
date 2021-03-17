package com.userservice.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.userservice.userservice.entity.WishListId;
import com.userservice.userservice.entity.WishlistEntity;


public interface WishListRepository extends JpaRepository<WishlistEntity, WishListId>{

	List<WishlistEntity> findByBuyerId(Integer buyerId);

	//CartEntity findByBuyerIdProductId(Integer buyerId, Integer productId);

	

}
