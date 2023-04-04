package com.example.online_banking.controller.rest;

import com.example.online_banking.exception.DataInvalidException;
import com.example.online_banking.rest.model.*;
import com.example.online_banking.service.LoansService;
import com.example.online_banking.service.SavingMoneyService;
import com.example.online_banking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer/rest")
public class RestUserController {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private LoansService loansService;

    @Autowired
    private SavingMoneyService savingMoneyService;

    @PostMapping(value = "/doTransferMoney")
    public ResponseData<TransferTransactionOutput> doTransferMoney(
            @RequestBody TransferTransactionInput input,
            Authentication authentication) throws Exception{
        return ResponseData.ok(transactionService.doTransferMoney(authentication, input));
    }

    @PostMapping(value = "/doWithdrawMoney")
    public ResponseData<TransferTransactionOutput> doWithdrawMoney(
            @RequestBody TransferTransactionInput input,
            Authentication authentication) throws DataInvalidException {
        return ResponseData.ok(transactionService.doWithdrawMoney(authentication, input));
    }

    @PostMapping(value = "/doDepositMoney")
    public ResponseData<TransferTransactionOutput> doDepositMoney(
            @RequestBody TransferTransactionInput input,
            Authentication authentication) throws DataInvalidException {
        return ResponseData.ok(transactionService.doDepositMoney(authentication, input));
    }

    @PostMapping(value = "/doMoneyLoans")
    public ResponseData<LoansMoneyOutput> doMoneyLoans(
            @RequestBody LoansMoneyInput input,
            Authentication authentication) throws DataInvalidException {
        return ResponseData.ok(loansService.doMoneyLoans(authentication, input));
    }

    @PostMapping(value = "/doSavingMoney")
    public ResponseData<SavingMoneyOutput> doSavingMoney(
            @RequestBody SavingMoneyInput input,
            Authentication authentication) throws DataInvalidException {
        return ResponseData.ok(savingMoneyService.doSavingMoney(authentication, input));
    }
}