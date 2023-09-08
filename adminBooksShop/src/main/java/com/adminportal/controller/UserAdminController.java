package com.adminportal.controller;

import com.adminportal.entity.User;
import com.adminportal.entity.security.Role;
import com.adminportal.entity.security.UserRole;
import com.adminportal.repository.UserRepository;
import com.adminportal.service.UserServiceAdmin;
import com.adminportal.utility.SecurityUtility;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/userAdminControl")
public class UserAdminController {

    private final UserServiceAdmin userServiceAdmin;
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserAdminController(UserServiceAdmin userServiceAdmin, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userServiceAdmin = userServiceAdmin;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping(value = "/add")
    public String addUserAdmin(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "addUserAdmin";
    }

    @PostMapping(value = "/add")
    public String addUserPostAdmin(@ModelAttribute("username") String username,
                                   @ModelAttribute("firstname") String firstname,
                                   @ModelAttribute("lastname") String lastname,
                                   @ModelAttribute("password") String password,
                                   @ModelAttribute("email") String email,
                                   HttpServletRequest request) {
        User user1 = new User();
        user1.setFirstName(firstname);
        user1.setLastName(lastname);
        user1.setUsername(username);
        user1.setPassword(SecurityUtility.passwordEncoder().encode(password));
        user1.setEmail(email);
        Set<UserRole> userRoles = new HashSet<>();
        Role role1= new Role();
        role1.setRoleId(1);
        role1.setName("ROLE_USER");
        userRoles.add(new UserRole(user1, role1));

        userServiceAdmin.createUser(user1, userRoles);


        return "redirect:userListAdmin";
    }

    @GetMapping("/userInfoAdmin")
    public String userInfoAdmin(@RequestParam("id") Long id, Model model) {
        User user = userServiceAdmin.findById(id);
        model.addAttribute("user", user);

        return "userInfoAdmin";
    }

    @GetMapping("/updateUserAdmin")
    public String updateBook(@RequestParam("id") Long id, Model model) {
        User user = userServiceAdmin.findById(id);
        model.addAttribute("user", user);

        return "updateUserAdmin";
    }

    @RequestMapping(value="/updateUserAdmin")
    public String updateUserPostAdmin( @ModelAttribute("user") User user,
                                        @ModelAttribute("username") String username,
                                        @ModelAttribute("firstname") String firstname,
                                        @ModelAttribute("lastname") String lastname,
                                        @ModelAttribute("email") String email,
                                      HttpServletRequest request) {
        user.setUsername(username);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setEmail(email);
        user.setPassword(user.getPassword());
        userServiceAdmin.save(user);

        return "redirect:/userAdminControl/userInfoAdmin?id="+user.getId();

    }

    @GetMapping("/userListAdmin")
    public String userListAdmin(Model model) {
        List<User> userListAdmin = userServiceAdmin.findAll();
        model.addAttribute("userListAdmin", userListAdmin);
        return "userListAdmin";

    }

    @RequestMapping(value="/remove")
    public String removeUserAdmin(
            @ModelAttribute("id") String id, Model model
    ) {
        userServiceAdmin.removeOne(Long.parseLong(id.substring(8)));
        List<User> userListAdmin = userServiceAdmin.findAll();
        model.addAttribute("userListAdmin", userListAdmin);

        return "redirect:/userAdminControl/userListAdmin";
    }
}
