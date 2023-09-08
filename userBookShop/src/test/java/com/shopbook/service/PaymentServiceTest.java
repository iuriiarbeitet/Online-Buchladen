package com.shopbook.service;

import com.shopbook.entity.Payment;
import com.shopbook.entity.UserPayment;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PaymentServiceTest {

    @Mock
    private UserPayment mockUserPayment;

    @Test
    public void testSetByUserPayment() {
        PaymentService paymentService = new PaymentService();

        Mockito.when(mockUserPayment.getType()).thenReturn("Credit Card");
        Mockito.when(mockUserPayment.getHolderName()).thenReturn("John Doe");
        Mockito.when(mockUserPayment.getCardNumber()).thenReturn("1234 5678 9012 3456");
        Mockito.when(mockUserPayment.getExpiryMonth()).thenReturn(12);
        Mockito.when(mockUserPayment.getExpiryYear()).thenReturn(2023);
        Mockito.when(mockUserPayment.getCvc()).thenReturn(123);

        Payment payment = new Payment();

        Payment updatedPayment = paymentService.setByUserPayment(mockUserPayment, payment);

        assertEquals("Credit Card", updatedPayment.getType());
        assertEquals("John Doe", updatedPayment.getHolderName());
        assertEquals("1234 5678 9012 3456", updatedPayment.getCardNumber());
        assertEquals(12, updatedPayment.getExpiryMonth());
        assertEquals(2023, updatedPayment.getExpiryYear());
        assertEquals(123, updatedPayment.getCvc());
    }
}



