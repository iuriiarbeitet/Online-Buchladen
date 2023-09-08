package com.adminportal.service;


import com.adminportal.entity.ShoppingCart;
import com.adminportal.entity.User;
import com.adminportal.entity.UserPayment;
import com.adminportal.entity.UserShipping;
import com.adminportal.entity.security.UserRole;
import com.adminportal.repository.RoleRepository;
import com.adminportal.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class UserServiceAdmin {
	private static final Logger LOG = LoggerFactory.getLogger(UserServiceAdmin.class);
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	public UserServiceAdmin(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	public User createUser(User user, Set<UserRole> userRoles) {
		User localUser = userRepository.findByUsername(user.getUsername()).orElse(null);

		if(localUser != null) {
			LOG.info("user {} already exists. Nothing will be done.", user.getUsername());
		} else {
			for (UserRole ur : userRoles) {
				roleRepository.save(ur.getRole());
			}

			user.getUserRoles().addAll(userRoles);

			ShoppingCart shoppingCart = new ShoppingCart();
			shoppingCart.setUser(user);
			user.setShoppingCart(shoppingCart);

			user.setUserShippingList(new ArrayList<UserShipping>());
			user.setUserPaymentList(new ArrayList<UserPayment>());

			localUser = userRepository.save(user);
		}
		return localUser;
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username).orElse(null);
	}


	public User findById(Long id){
		return userRepository.findById(id).orElse(null);
	}

	public User findByEmail (String email) {
		return userRepository.findByEmail(email);
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public List<User> findAll(){
		return (List<User>) userRepository.findAll();
	}

	public void removeOne(Long id) {
		userRepository.deleteById(id);
	}



}

