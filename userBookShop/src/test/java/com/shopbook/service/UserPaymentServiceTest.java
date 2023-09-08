package com.shopbook.service;

import com.shopbook.entity.UserPayment;
import com.shopbook.repository.UserPaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
public class UserPaymentServiceTest {

    @InjectMocks
    private UserPaymentService userPaymentService;

    @Mock
    private UserPaymentRepository userPaymentRepository;

    @Test
    public void testFindById() {
        UserPayment expectedUserPayment = new UserPayment();
        when(userPaymentRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(expectedUserPayment));

        UserPayment result = userPaymentService.findById(1L);

        assertEquals(expectedUserPayment, result);
    }

    @Test
    public void testRemoveById() {
        Long idToRemove = 2L;

        userPaymentService.removeById(idToRemove);

        verify(userPaymentRepository, times(1)).deleteById(idToRemove);
    }
}