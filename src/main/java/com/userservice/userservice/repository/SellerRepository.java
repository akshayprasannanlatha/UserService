package com.userservice.userservice.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.userservice.userservice.entity.Seller;

public interface SellerRepository extends JpaRepository<Seller, Integer> {

	List<Seller> getByEmail(String email);

	

}
