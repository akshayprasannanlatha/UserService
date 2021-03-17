package com.userservice.userservice.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userservice.userservice.dto.BuyerDTO;
import com.userservice.userservice.dto.LoginDTO;
import com.userservice.userservice.dto.SellerDTO;
import com.userservice.userservice.entity.Buyer;
import com.userservice.userservice.entity.Seller;
import com.userservice.userservice.entity.WishlistEntity;
import com.userservice.userservice.repository.BuyerRepository;
import com.userservice.userservice.repository.SellerRepository;
import com.userservice.userservice.validator.BuyerValidator;
import com.userservice.userservice.validator.SellerValidator;

@Service
public class CustomerUserService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	BuyerRepository buyRepo;
	
	@Autowired
	SellerRepository sellRepo;
	
//	Register Buyer
	public Integer createBuyer(BuyerDTO buyerDTO) throws Exception {
		BuyerValidator.validateBuyer(buyerDTO);
		Buyer buyer=new Buyer();
		buyerDTO.setPhoneNumber(buyerDTO.getPhoneNumber().substring(4));
		logger.info("Creation request for Buyer {}", buyerDTO);
//		List<Buyer> buyerList = buyRepo.getByPhoneNumber(buyer1.getPhoneNumber());
//		if (!buyerList.isEmpty()) {
//			for(Buyer buyerEntity:buyerList) {
			
//				if (!buyerEntity.equals(buyerDTO.getPhoneNumber())) {
//					throw new Exception("CART.PRESENT");}}}
		
		 buyer = buyerDTO.createEntityBuyer();
		
		return buyRepo.save(buyer).getBuyerId();
	}

	

	// Login buyer
	
	public boolean buyerLogin(LoginDTO buyerLoginDTO) throws Exception {
		BuyerValidator.validateBuyerLogin(buyerLoginDTO);
		logger.info("Login request for buyer {} with password {}", buyerLoginDTO.getEmail(),buyerLoginDTO.getPassword());
		List<Buyer> buyerList = buyRepo.getByEmail(buyerLoginDTO.getEmail());
		if (!buyerList.isEmpty()) {
			for(Buyer buyerEntity:buyerList) {
			
				if (buyerEntity.getUserPassword().equals(buyerLoginDTO.getPassword())) {
					return true;
				}
			}
		}

		return false;
	}
	
	//find buyer by id
	public boolean findBuyerId(Integer buyerId)
	{
		logger.info("find request for buyer {} with buyerId{}", buyerId);
		Optional<Buyer> buyerEntity =buyRepo.findById(buyerId);
		if (buyerEntity.isPresent())
		{
			return true;
		}
		return false;
	}
	
//	Register seller
	public Integer createSeller(SellerDTO sellerDTO) throws Exception {
		SellerValidator.validateSeller(sellerDTO);
		sellerDTO.setPhoneNumber(sellerDTO.getPhoneNumber().substring(4));
		logger.info("Creation request for seller {}", sellerDTO);
		sellerDTO.setIsActive(1);
		Seller seller = sellerDTO.createEntitySeller();
		
		return sellRepo.save(seller).getSellerId();
	}
	
//	login Seller
	public boolean sellerLogin(LoginDTO sellerLoginDTO) throws Exception {
		SellerValidator.validateSellerLogin(sellerLoginDTO);
		logger.info("Login request for seller {} with password {}", sellerLoginDTO.getEmail(),sellerLoginDTO.getPassword());
		List<Seller> sellerList = sellRepo.getByEmail(sellerLoginDTO.getEmail());
		if (!sellerList.isEmpty()) {
			for(Seller sellerEntity:sellerList) {
				if(sellerEntity.getActive()==0) {
					throw new Exception("Seller.DEACTIVATED");
				}
				if (sellerEntity.getUserPassword().equals(sellerLoginDTO.getPassword())) {
					return true;
				}
			}
		}

		return false;
	}

	//find seller by id
			public boolean findSellerId(Integer sellerId)
			{
				logger.info("find request for seller {} with sellerId{}", sellerId);
				Optional<Seller> sellerEntity =sellRepo.findById(sellerId);
				if (sellerEntity.isPresent())
				{
					return true;
				}
				return false;
			}
			
		// deactivate seller
			public boolean deactivateSeller(Integer sellerId) {
				logger.info("deactivate request for seller {} with sellerId{}", sellerId);
				Optional<Seller> sellerEntity =sellRepo.findById(sellerId);
				if (sellerEntity.isPresent())
				{
					Seller seller=sellerEntity.get();
					seller.setActive(0);
					sellRepo.save(seller);
					return true;
					
				}
				return false;
				
			}
		// activate seller
			public boolean activateSeller(LoginDTO sellerLoginDTO) {
				logger.info("activate request for seller {} with email{}", sellerLoginDTO.getEmail());
				List<Seller> sellerEntities =sellRepo.getByEmail(sellerLoginDTO.getEmail());
				if(!sellerEntities.isEmpty()) {
					for(Seller sellerEntity:sellerEntities) {
						if (sellerEntity.getUserPassword().equals(sellerLoginDTO.getPassword())) {
							sellerEntity.setActive(1);
							sellRepo.save(sellerEntity);
							
						}
					}
					return true;
				}
				return false;
			}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			public void updateBuyerPrivilege(String email,int privilege) throws Exception {
				Buyer buyerEntity= buyRepo.findByEmail(email);
				if(buyerEntity!=null){
					if(buyerEntity.getIsPrivilaged()==1) {
						if(privilege!=1) {
							buyerEntity.setIsPrivilaged(1);
						    buyRepo.save(buyerEntity);
							
						}
						else {
						throw new Exception("Buyer.ALREADY_PRIVILEGE");
						}
					}
					else if((buyerEntity.getIsPrivilaged())!=1) {
						if(privilege==1) {
							if(buyerEntity.getRewardPoints()<10000) {
								throw new Exception("Buyer.INSUFFICIENT_REWARD_POINTS");
							}
							else {
								buyerEntity.setIsPrivilaged(1);
								buyerEntity.setRewardPoints(buyerEntity.getRewardPoints()-10000);
								buyRepo.save(buyerEntity);
							}
							
						}
						else {
							
							throw new Exception("Buyer.ALREADY_NOT_PRIVILEGE");
							
						}
					}
				buyerEntity.setIsPrivilaged(1);
			    buyRepo.save(buyerEntity);
				}
				else {
					throw new Exception("Buyer.INVALID_EMAIL");
				}
				
			}
	 	
	}
