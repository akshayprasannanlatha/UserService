package com.userservice.userservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.userservice.userservice.dto.BuyerDTO;
import com.userservice.userservice.dto.LoginDTO;
import com.userservice.userservice.dto.SellerDTO;
import com.userservice.userservice.service.CustomerUserService;

@Controller
@CrossOrigin

public class CustomerController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CustomerUserService custService;
	
	
//	@Value("${plan.uri}")
	//String planUri;

	@Autowired
	private Environment environment;
	
	// Create a new buyer
		@PostMapping(value = "/buyer/register",  consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> createBuyer(@RequestBody BuyerDTO buyerDTO) throws Exception{
			logger.info("Creation request for buyer {}", buyerDTO);
			try {
				int buyerId=custService.createBuyer(buyerDTO);
				String successMessage=environment.getProperty("BUYER_SUCCESS")+buyerId;
				return new ResponseEntity<String>(successMessage,HttpStatus.CREATED);
			}
			catch(Exception e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);
			}
		}
		// Create a new seller
		@PostMapping(value = "/seller/register",  consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> createSeller(@RequestBody SellerDTO sellerDTO) throws Exception{
			logger.info("Creation request for seller {}", sellerDTO);
			try {
				int sellerId=custService.createSeller(sellerDTO);
				String successMessage=environment.getProperty("Seller_SUCCESS")+sellerId;
				return new ResponseEntity<String>(successMessage,HttpStatus.CREATED);
			}
			catch(Exception e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);
			}
		}

		// Login buyer
		@PostMapping(value = "/buyer/login",consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Boolean> buyerLogin(@RequestBody LoginDTO buyerLoginDTO) {
			try {
				logger.info("Login request for buyer {} with password {}", buyerLoginDTO.getEmail(),buyerLoginDTO.getPassword());
				Boolean check=custService.buyerLogin(buyerLoginDTO);
				return new ResponseEntity<Boolean>(check,HttpStatus.NOT_FOUND);
			}
			catch(Exception e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);
			}
		}
	
		// Login seller
		@PostMapping(value = "/seller/login",consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Boolean> sellerlogin(@RequestBody LoginDTO sellerLoginDTO) {
			try {
				logger.info("Login request for seller {} with password {}", sellerLoginDTO.getEmail(),sellerLoginDTO.getPassword());
				Boolean check=custService.sellerLogin(sellerLoginDTO);
				return new ResponseEntity<Boolean>(check,HttpStatus.NOT_FOUND);
			}
			catch(Exception e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);
			}
		}
		// finding seller
		@GetMapping(value = "/seller/{sellerId}",consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Boolean> findSeller(@PathVariable int sellerId) {
			try {
				logger.info("find request for seller {} with sellerId{}",sellerId);
				Boolean check=custService.findSellerId(sellerId);
				return new ResponseEntity<Boolean>(check,HttpStatus.FOUND);
			}
			catch(Exception e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,environment.getProperty(e.getMessage()),e);
			}
		}
		//deactivate seller
		@DeleteMapping(value = "/seller/deactivate/{sellerId}")
	    public ResponseEntity<Boolean> deactivateSeller(@PathVariable int sellerId) {
			try {
				logger.info("deactivate request for seller {} with sellerId{}",sellerId);
				Boolean check=custService.deactivateSeller(sellerId);
				return new ResponseEntity<Boolean>(check,HttpStatus.FOUND);
			}
			catch(Exception e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,environment.getProperty(e.getMessage()),e);
			}
	        
	    }
		// activate seller
		@PostMapping(value = "/seller/activate",consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Boolean> sellerActivate(@RequestBody LoginDTO sellerLoginDTO) {
			try {
				logger.info("Activate request for seller {} with password {}", sellerLoginDTO.getEmail(),sellerLoginDTO.getPassword());
				Boolean check=custService.activateSeller(sellerLoginDTO);
				return new ResponseEntity<Boolean>(check,HttpStatus.OK);
			}
			catch(Exception e) {
				throw new ResponseStatusException(HttpStatus.NOT_MODIFIED,environment.getProperty(e.getMessage()),e);
			}
		}
		// finding buyer
		@GetMapping(value = "/buyer/{buyerId}",consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Boolean> findBuyer(@PathVariable int buyerId) {
			try {
				logger.info("find request for buyer {} with buyerId{}",buyerId);
				Boolean check=custService.findBuyerId(buyerId);
				return new ResponseEntity<Boolean>(check,HttpStatus.NOT_FOUND);
			}
			catch(Exception e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);
			}
		}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		@PutMapping(value = "/buyer/privilege")
		public ResponseEntity<String> updateBuyerPrivilege(@RequestParam(name = "email") String email ,@RequestParam(name = "privilege") int privilege) {

			ResponseEntity<String> responseEntity = null;

			try {
				
				
				custService.updateBuyerPrivilege(email, privilege);
				String successMessage = environment.getProperty("Buyer.PRIVILEGE_UPDATE_SUCCESS");
				responseEntity = new ResponseEntity<String>(successMessage, HttpStatus.OK);

			} catch (Exception exception) {
				logger.error("Error: " + exception.getMessage(), exception);

				String errorMessage;

				if (exception.getMessage() == null) {
					errorMessage = environment.getProperty("General.EXCEPTION");
					responseEntity = new ResponseEntity<String>(errorMessage, HttpStatus.OK);
					return responseEntity;

				} else {
					errorMessage = environment.getProperty(exception.getMessage());

					if (errorMessage == null)
						errorMessage = environment.getProperty("General.EXCEPTION");
					responseEntity = new ResponseEntity<String>(errorMessage, HttpStatus.OK);
					return responseEntity;

				}
			}

			return responseEntity;

		}
			

}


	// Fetches full profile of a specific customer
	//@GetMapping(value = "/seller/{email}",  produces = MediaType.APPLICATION_JSON_VALUE)
//	public SellerDTO getCustomerProfile(@PathVariable String Email) {
//	
//		logger.info("Profile request for customer {}", Email);
//	
//		SellerDTO custDTO=custService.getCustomerProfile(Email);
//		PlanDTO planDTO=new RestTemplate().getForObject(planUri+custDTO.getCurrentPlan().getPlanId(), PlanDTO.class);
//		custDTO.setCurrentPlan(planDTO);
		
//		@SuppressWarnings("unchecked")
//		List<Long> friends=template.getForObject("http://custribbon/customers/"+phoneNo+"/friends", List.class);
//		custDTO.setFriendAndFamily(friends);
//		return custDTO;
//	}




