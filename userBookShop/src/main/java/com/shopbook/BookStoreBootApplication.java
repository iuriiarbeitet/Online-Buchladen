package com.shopbook;

import com.shopbook.entity.User;
import com.shopbook.entity.security.Role;
import com.shopbook.entity.security.UserRole;
import com.shopbook.service.UserService;
import com.shopbook.utility.SecurityUtility;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class BookStoreBootApplication implements CommandLineRunner {

    private final UserService userService;

    public BookStoreBootApplication(UserService userService) {
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookStoreBootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Adams");
        user1.setUsername("john");
        user1.setPassword(SecurityUtility.passwordEncoder().encode("p"));
        user1.setEmail("jhoohn@gmail.com");
        Set<UserRole> userRoles = new HashSet<>();
        Role role1= new Role();
        role1.setRoleId(1);
        role1.setName("ROLE_USER");
        userRoles.add(new UserRole(user1, role1));

        userService.createUser(user1, userRoles);
    }
}