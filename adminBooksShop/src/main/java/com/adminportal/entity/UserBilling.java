package com.adminportal.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UserBilling {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String userBillingName;
	private String userBillingStreet1;
	private String userBillingStreet2;
	private String userBillingCity;
	private String userBillingState;
	private String userBillingCountry;
	private String userBillingZipcode;
	
	@OneToOne(cascade=CascadeType.ALL)
	private UserPayment userPayment;


	
	
}
