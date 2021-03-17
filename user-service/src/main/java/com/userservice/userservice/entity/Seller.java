package com.userservice.userservice.entity;

import javax.persistence.*;

@Entity
@Table (name = "seller")

public class Seller{

    @Id
//    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "SELLERID", unique = true, length = 50)
    private int sellerId;

    public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	@Column (name = "NAME")
    private String userName;
	@Column (name = "EMAIL")
    private String email;
    @Column (name = "PASSWORD", nullable = false, length = 50)
    private String userPassword;
    @Column (name = "ISACTIVE")
    private int active;
    
    @Column (name = "PHONENUMBER")
    private String phoneNumber;
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
	
	//public Long getId() {
		// TODO Auto-generated method stub
	//	return null;
	//}
}
    

   // @OneToOne (cascade = CascadeType.ALL)
   // @JoinColumn (name = "user_details_id")
  //  private UserDetails userDetails;

   // @ManyToOne
  //  @JoinColumn (name = "role_id")
   // private UserRole role;

