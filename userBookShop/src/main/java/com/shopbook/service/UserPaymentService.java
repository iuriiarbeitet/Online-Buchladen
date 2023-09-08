package com.shopbook.service;

import com.shopbook.entity.UserPayment;
import com.shopbook.repository.UserPaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class UserPaymentService {

	private final UserPaymentRepository userPaymentRepository;

	public UserPaymentService(UserPaymentRepository userPaymentRepository) {
		this.userPaymentRepository = userPaymentRepository;
	}

	public UserPayment findById(Long id) {
		return userPaymentRepository.findById(id).orElse(null);
	}
	
	public void removeById(Long id) {
		userPaymentRepository.deleteById(id);
	}
} 
