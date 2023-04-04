package com.example.online_banking.controller;

import com.example.online_banking.model.User;
import com.example.online_banking.repository.UserRepository;
import com.example.online_banking.service.RegisterService;
import com.example.online_banking.utils.CommonUtils;
import com.example.online_banking.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("")
public class HomeController {
    @Autowired
    private UserRepository repository;

    @Autowired
    private RegisterService service;

    // HOME PAGE
    @RequestMapping("/home")
    public String viewHomePage(Authentication authentication, Model model) {
        String userName = authentication.getName();
        User user = repository.findByUsername(userName);
        model.addAttribute("currentUser", user);
        return "HomePage";
    }

    // ERROR PAGE
    @RequestMapping("/403")
    public String errorPage() {
        return "errorPage";
    }

    // LOGIN
    @RequestMapping("/login")
    public String login(Authentication authentication, Model model) {
        if (authentication != null) {
            return "redirect:/";
        }
        return "login";
    }

    //logout
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
//        model.addAttribute("title", "Logout");
        return "redirect:/";
    }


    //register
    @RequestMapping(value = "/register")
    public String register(Model model) {
        User register = new User();
        model.addAttribute("register", register);
        return "register";
    }

    @RequestMapping(value = "/register/save")
    public String saveUpdate(@Valid User register, BindingResult result) {
        if (result.hasErrors()) {
            return "errorPage";
        }
        service.save(register);
        return "redirect:/login";
    }

    @RequestMapping("/")
    public String home(Authentication authentication) {
        String role = CommonUtils.getAuthority(authentication);
        if (Constants.ROLE_USER.equals(role)) {
            return "redirect:/customer";
        } else if (Constants.ROLE_ADMIN.equals(role)) {
            return "redirect:/admin";
        }
        return "errorPage";
    }
}