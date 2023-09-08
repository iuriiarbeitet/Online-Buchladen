package com.shopbook.service;


import com.shopbook.entity.BillingAddress;
import com.shopbook.entity.UserBilling;
import org.springframework.stereotype.Service;

/**
 * Service class for managing Billing Address operations.
 */

@Service
public class BillingAddressService {

	/**
	 * Sets the properties of a BillingAddress based on a UserBilling object.
	 *
	 * @param userBilling The UserBilling object containing the billing information.
	 * @param billingAddress The BillingAddress object to be updated.
	 * @return The updated BillingAddress object.
	 */
	public BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress) {
		billingAddress.setBillingAddressName(userBilling.getUserBillingName());
		billingAddress.setBillingAddressStreet1(userBilling.getUserBillingStreet1());
		billingAddress.setBillingAddressStreet2(userBilling.getUserBillingStreet2());
		billingAddress.setBillingAddressCity(userBilling.getUserBillingCity());
		billingAddress.setBillingAddressState(userBilling.getUserBillingState());
		billingAddress.setBillingAddressCountry(userBilling.getUserBillingCountry());
		billingAddress.setBillingAddressZipcode(userBilling.getUserBillingZipcode());
		
		return billingAddress;
	}

}
