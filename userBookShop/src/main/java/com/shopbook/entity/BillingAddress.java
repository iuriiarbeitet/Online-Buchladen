package com.shopbook.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class BillingAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String BillingAddressName;
	private String BillingAddressStreet1;
	private String BillingAddressStreet2;
	private String BillingAddressCity;
	private String BillingAddressState;
	private String BillingAddressCountry;
	private String BillingAddressZipcode;
	
	@OneToOne
	private Order order;


}
