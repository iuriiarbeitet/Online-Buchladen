package com.adminportal;

import com.adminportal.entity.User;
import com.adminportal.service.UserServiceAdmin;
import com.adminportal.entity.security.Role;
import com.adminportal.entity.security.UserRole;
import com.adminportal.utility.SecurityUtility;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class AdminBookShopApplication implements CommandLineRunner {

	private final UserServiceAdmin userServiceAdmin;

	public AdminBookShopApplication(UserServiceAdmin userServiceAdmin) {
		this.userServiceAdmin = userServiceAdmin;
	}

	public static void main(String[] args) {
		SpringApplication.run(AdminBookShopApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		User user1 = new User();
		user1.setUsername("admin");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("admin"));
		user1.setEmail("administrator@gmail.com");
		Set<UserRole> userRoles = new HashSet<>();
		Role role1= new Role();
		role1.setRoleId(0);
		role1.setName("ROLE_ADMIN");
		userRoles.add(new UserRole(user1, role1));

		userServiceAdmin.createUser(user1, userRoles);
	}
}
