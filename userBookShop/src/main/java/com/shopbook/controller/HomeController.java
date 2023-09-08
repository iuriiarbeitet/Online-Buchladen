package com.shopbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("classActiveLogin", true);
        return "myAccount";
    }

    @GetMapping("/hours")
    public String hours() {
        return "hours";
    }

    @GetMapping("/faq")
    public String faq() {
        return "faq";
    }

}
