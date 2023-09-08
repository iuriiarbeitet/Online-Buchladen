package com.shopbook.service;

import com.shopbook.entity.ShippingAddress;
import com.shopbook.entity.UserShipping;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShippingAddressServiceTest {
    @Mock
    private UserShipping mockUserShipping;

    @Test
    public void testSetByUserShipping() {
        ShippingAddressService shippingAddressService = new ShippingAddressService();

        Mockito.when(mockUserShipping.getUserShippingName()).thenReturn("Mack Berg");
        Mockito.when(mockUserShipping.getUserShippingStreet1()).thenReturn("4 haupt St");
        Mockito.when(mockUserShipping.getUserShippingStreet2()).thenReturn("Unit 202");
        Mockito.when(mockUserShipping.getUserShippingCity()).thenReturn("Luedensheid");
        Mockito.when(mockUserShipping.getUserShippingState()).thenReturn("DE");
        Mockito.when(mockUserShipping.getUserShippingCountry()).thenReturn("Germany");
        Mockito.when(mockUserShipping.getUserShippingZipcode()).thenReturn("50645");

        ShippingAddress shippingAddress = new ShippingAddress();

        ShippingAddress updatedShippingAddress = shippingAddressService.setByUserShipping(mockUserShipping, shippingAddress);

        assertEquals("Mack Berg", updatedShippingAddress.getShippingAddressName());
        assertEquals("4 haupt St", updatedShippingAddress.getShippingAddressStreet1());
        assertEquals("Unit 202", updatedShippingAddress.getShippingAddressStreet2());
        assertEquals("Luedensheid", updatedShippingAddress.getShippingAddressCity());
        assertEquals("DE", updatedShippingAddress.getShippingAddressState());
        assertEquals("Germany", updatedShippingAddress.getShippingAddressCountry());
        assertEquals("50645", updatedShippingAddress.getShippingAddressZipcode());
    }
}