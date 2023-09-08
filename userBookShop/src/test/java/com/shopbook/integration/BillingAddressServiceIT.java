package com.shopbook.integration;

import com.shopbook.annotation.IT;
import com.shopbook.entity.BillingAddress;
import com.shopbook.entity.UserBilling;
import com.shopbook.service.BillingAddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@IT
public class BillingAddressServiceIT {

    @Autowired
    private BillingAddressService billingAddressService;

    @Test
    public void testSetByUserBilling() {
        UserBilling userBilling = new UserBilling();
        userBilling.setUserBillingName("Mack Jane");
        userBilling.setUserBillingStreet1("12 St");
        userBilling.setUserBillingStreet2("koellner 99");
        userBilling.setUserBillingCity("Bergneustadt");
        userBilling.setUserBillingState("DE");
        userBilling.setUserBillingCountry("Germany");
        userBilling.setUserBillingZipcode("50702");

        BillingAddress billingAddress = new BillingAddress();

        BillingAddress updatedBillingAddress = billingAddressService.setByUserBilling(userBilling, billingAddress);

        assertEquals("Mack Jane", updatedBillingAddress.getBillingAddressName());
        assertEquals("12 St", updatedBillingAddress.getBillingAddressStreet1());
        assertEquals("koellner 99", updatedBillingAddress.getBillingAddressStreet2());
        assertEquals("Bergneustadt", updatedBillingAddress.getBillingAddressCity());
        assertEquals("DE", updatedBillingAddress.getBillingAddressState());
        assertEquals("Germany", updatedBillingAddress.getBillingAddressCountry());
        assertEquals("50702", updatedBillingAddress.getBillingAddressZipcode());
    }
}