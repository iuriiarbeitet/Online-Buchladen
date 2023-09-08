package com.shopbook.service;

import com.shopbook.entity.BillingAddress;
import com.shopbook.entity.UserBilling;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BillingAddressServiceTests {

    @Mock
    private UserBilling mockUserBilling;

    @Test
    public void testSetByUserBilling() {
        BillingAddressService billingAddressService = new BillingAddressService();

        Mockito.when(mockUserBilling.getUserBillingName()).thenReturn("Mack Jane");
        Mockito.when(mockUserBilling.getUserBillingStreet1()).thenReturn("12 St");
        Mockito.when(mockUserBilling.getUserBillingStreet2()).thenReturn("koellner 99");
        Mockito.when(mockUserBilling.getUserBillingCity()).thenReturn("Bergneustadt");
        Mockito.when(mockUserBilling.getUserBillingState()).thenReturn("DE");
        Mockito.when(mockUserBilling.getUserBillingCountry()).thenReturn("Germany");
        Mockito.when(mockUserBilling.getUserBillingZipcode()).thenReturn("50702");

        BillingAddress billingAddress = new BillingAddress();

        billingAddressService.setByUserBilling(mockUserBilling, billingAddress);

        assertEquals("Mack Jane", billingAddress.getBillingAddressName());
        assertEquals("12 St", billingAddress.getBillingAddressStreet1());
        assertEquals("koellner 99", billingAddress.getBillingAddressStreet2());
        assertEquals("Bergneustadt", billingAddress.getBillingAddressCity());
        assertEquals("DE", billingAddress.getBillingAddressState());
        assertEquals("Germany", billingAddress.getBillingAddressCountry());
        assertEquals("50702", billingAddress.getBillingAddressZipcode());
    }
}