package com.example.online_banking.controller;

import com.example.online_banking.model.User;
import com.example.online_banking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class  AdminController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("")
    public String adminHome(Authentication authentication, Model model) {
        String userName = authentication.getName();
        User user = userRepository.findByUsername(userName);
        model.addAttribute("currentUser", user);
        return "adminHome";
    }

    // manage customer
    @RequestMapping("/manage-customer")
    public String manageCustomer() {
        return "admin/manage-customer";
    }

    //TODO: manage admin
    @RequestMapping("/manage-admin")
    public String manageAdmin() {
        return "admin/manage-admin";
    }

    // TODO: manage loans package
    @RequestMapping("/manage-loans-package")
    public String manageLoansPackage() {
        return "admin/manage-loans-package";
    }

    // TODO: manage loans
//    @RequestMapping("/manage-loans")
//    public String manageLoans() {
//        return "admin/manage-loans";
//    }

    // TODO: manage savings package
//    @RequestMapping("/manage-savingsPackage")
//    public String manageSavingsPackage() {
//        return "admin/manage-savingsPackage";
//    }

    // TODO: manage savings
//    @RequestMapping("/manage-savings")
//    public String manageSavings() {
//        return "admin/manage-savings";
//    }
}
