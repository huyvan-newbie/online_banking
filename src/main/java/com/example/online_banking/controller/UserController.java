package com.example.online_banking.controller;

import com.example.online_banking.model.*;
import com.example.online_banking.repository.*;
import com.example.online_banking.rest.model.TransferTransactionInput;
import com.example.online_banking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class UserController {
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private LoansPackageRepository loansPackageRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private LoansRepository loansRepository;

    @Autowired
    private SavingRepositoy savingRepositoy;

    @Autowired
    private SavingPackageRepository savingPackageRepository;

    @RequestMapping("")
    public String viewCustomerHome(Authentication authentication, Model model) {
        String userName = authentication.getName();
        User user = userRepository.findByUsername(userName);
        model.addAttribute("currentUser", user);
        return "customerHome";
    }


//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public String logoutSuccessfulPage(Model model) {
//        model.addAttribute("title", "Logout");
//        return "redirect:/login";
//    }


    @RequestMapping("/profile")
    public String viewProfile(Authentication authentication, Model model) {
        String userName = authentication.getName();
        User user = userRepository.findByUsername(userName);
        model.addAttribute("user", user);
        return "customerProfile";
    }

    @RequestMapping("/viewBalance")
    public String showViewBalance(Authentication authentication, Model model) {
        String userName = authentication.getName();
        User user = userRepository.findByUsername(userName);
        Account account = accountRepository.findFirstByUserId(user.getId());
//        Card card = cardRepository.getById(account.getAccountId());
        model.addAttribute("account", account);
        return "viewBalancePage";
    }

    @RequestMapping("/transferMoney")
    public String transferMoney(Authentication authentication,
                                Model model) {
        String userName = authentication.getName();
        User user = userRepository.findByUsername(userName);
        Account account = accountRepository.findFirstByUserId(user.getId());
        model.addAttribute("account", account);

        List<Bank> bankList = bankRepository.findAll();
        model.addAttribute("bankList", bankList);
        return "TransferTransaction";
    }


    @RequestMapping("/moneyLoans")
    public String moneyLoans(Authentication authentication, Model model) {
        String userName = authentication.getName();
        User user = userRepository.findByUsername(userName);
        List<Loans> loansList = loansRepository.findUserByUserId(user.getId());
        model.addAttribute("loansList", loansList);

//        Hiện lên LoansPackage
        List<LoansPackage> loansPackages = loansPackageRepository.findAll();
        model.addAttribute("loansPackages", loansPackages);
        return "moneyLoans";
    }

    @RequestMapping("/moneySaving")
    public String savingMoney(Authentication authentication, Model model) {
        String userName = authentication.getName();
        User user = userRepository.findByUsername(userName);
        List<Saving> savingsList = savingRepositoy.findUserByUserId(user.getId());
        model.addAttribute("savingsList", savingsList);

//        Hiển thị lên saving package
        List<SavingPackage> savingsPackage = savingPackageRepository.findAll();
        model.addAttribute("savingsPackage",savingsPackage);
        return "savingMoney";
    }


    @RequestMapping("/transferSuccess")
    public String transferSuccess(
            @RequestParam("id") Long id,
            @RequestParam("type") String type,
            Model model) {
        Transaction transaction = transactionRepository.getById(id);

        Account account = accountRepository.getById(transaction.getAccountId());
        User user = userRepository.getById(account.getUserId());

        model.addAttribute("transaction", transaction);
        model.addAttribute("account", account);
        model.addAttribute("user", user);
        model.addAttribute("type", type);
        return "transactionSuccess";
    }

    @RequestMapping("/withdrawMoney")
    public String withdrawMoney(Authentication authentication,
//                                @RequestParam("id") Long id,
//                                @RequestParam("type") String type,
                                Model model) {
        String userName = authentication.getName();
        User user = userRepository.findByUsername(userName);
        Account account = accountRepository.findFirstByUserId(user.getId());
//        Transaction transaction = transactionRepository.getById(id);
        model.addAttribute("account", account);
//        model.addAttribute("type", type);
//        model.addAttribute("transaction", transaction);

        return "withdrawMoney";
    }

    @RequestMapping("/depositMoney")
    public String depositMoney(Authentication authentication,
//                               @RequestParam("id") Long id,
//                               @RequestParam("type") String type,
                               Model model) {
        //tìm tài khoản mà muốn cho tiền vào
        String userName = authentication.getName();
        User user = userRepository.findByUsername(userName);
        Account account1 = accountRepository.findFirstByUserId(user.getId());
        model.addAttribute("account1", account1);
//        model.addAttribute("type", type);
//        Transaction transaction = new Transaction();
//        model.addAttribute("transaction", transaction);
//        return "redirect:/transferSuccess";
        return "depositMoney";
    }

}