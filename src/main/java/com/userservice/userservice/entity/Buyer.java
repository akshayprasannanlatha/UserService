package com.userservice.userservice.entity;

import javax.persistence.*;

@Entity
@Table (name = "buyer")

public class Buyer {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "BUYERID",  length = 50)
    private int buyerId;

   
	@Column (name = "NAME", nullable = false, unique = true, length = 50)
    private String userName;
    @Column (name = "PASSWORD", nullable = false, length = 50)
    private String userPassword;
    @Column (name = "ISACTIVE")
    private int active;
    @Column (name = "EMAIL")
    private String email;
    @Column (name = "PHONENUMBER", nullable = false, length = 50)
    private String phoneNumber;
    @Column (name = "ISPRIVILEGED")
    private int isPrivilaged;
    @Column (name = "REWARDPOINTS")
    private int rewardPoints;
    
  
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	
	
	public int getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}
	public int getIsPrivilaged() {
		return isPrivilaged;
	}
	public void setIsPrivilaged(int isPrivilaged) {
		this.isPrivilaged = isPrivilaged;
	}
	public int getRewardPoints() {
		return rewardPoints;
	}
	public void setRewardPoints(int rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
	public Buyer()
	{
		super();
	}
	}
    

   // @OneToOne (cascade = CascadeType.ALL)
   // @JoinColumn (name = "user_details_id")
  //  private UserDetails userDetails;

   // @ManyToOne
  //  @JoinColumn (name = "role_id")
   // private UserRole role;

