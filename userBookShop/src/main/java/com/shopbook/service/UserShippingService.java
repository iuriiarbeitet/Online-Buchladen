package com.shopbook.service;

import com.shopbook.entity.UserShipping;
import com.shopbook.repository.UserShippingRepository;
import org.springframework.stereotype.Service;

@Service
public class UserShippingService {


	private final UserShippingRepository userShippingRepository;

	public UserShippingService(UserShippingRepository userShippingRepository) {
		this.userShippingRepository = userShippingRepository;
	}


	public UserShipping findById(Long id) {
		return userShippingRepository.findById(id).orElse(null);
	}
	
	public void removeById(Long id) {
		userShippingRepository.deleteById(id);
	}

}
