package com.shopbook.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UserShipping {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String userShippingName;
	private String userShippingStreet1;
	private String userShippingStreet2;
	private String userShippingCity;
	private String userShippingState;
	private String userShippingCountry;
	private String userShippingZipcode;
	private boolean userShippingDefault;

	@ManyToOne
	private User user;

}
