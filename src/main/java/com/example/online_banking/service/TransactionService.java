package com.example.online_banking.service;

import com.example.online_banking.exception.DataInvalidException;
import com.example.online_banking.model.Account;
import com.example.online_banking.model.Transaction;
import com.example.online_banking.model.User;
import com.example.online_banking.repository.AccountRepository;
import com.example.online_banking.repository.TransactionRepository;
import com.example.online_banking.repository.UserRepository;
import com.example.online_banking.repository.custom.TransactionRepositoryCustom;
import com.example.online_banking.rest.model.ErrorCode;
import com.example.online_banking.rest.model.TransferTransactionInput;
import com.example.online_banking.rest.model.TransferTransactionOutput;
import com.example.online_banking.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class TransactionService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionRepositoryCustom transactionRepositoryCustom;

    public TransferTransactionOutput doTransferMoney(Authentication authentication, TransferTransactionInput input) throws DataInvalidException {
            String userName = authentication.getName();
            User user = userRepository.findByUsername(userName);
        //tìm tài khoản mà muốn rút tiền
        Account debitAccount = accountRepository.findFirstByUserId(user.getId());
        //tìm tài khoản muốn chuyển tiền cho
        Account creditAccount = accountRepository.findByAccountNumberAndBankId(input.getAccountNumber(), input.getBankReceiveId());
        BigDecimal amount = new BigDecimal(input.getAmount());

        Transaction transaction = new Transaction();
        transaction.setTransactionAmount(amount);
        transaction.setAccountId(debitAccount.getAccountId());
        transaction.setDescription(input.getDescription());
        Date transactionDate = new Date();
        transaction.setTransactionDate(transactionDate);
        transaction.setTransactionType("TRANSFER");
        transaction.setTransactionAmount(amount);
        transaction.setUserId(user);

        if (input.getBankReceiveId() == null) {
            // nếu người dùng không chọn ngân hàng
            transaction.setStatus(Constants.STATUS_FAIL);
            transaction.setDescription(ErrorCode.getErrorMessage(ErrorCode.NO_BANK));
            transactionRepositoryCustom.insertLog(transaction);
            throw new DataInvalidException(ErrorCode.NO_BANK);
        }

        else if (creditAccount == null) {
            // neu tai khoan nhan tien khong ton tai
            transaction.setStatus(Constants.STATUS_FAIL);
            transaction.setDescription(ErrorCode.getErrorMessage(ErrorCode.ACCOUNT_NOT_EXIST));
            transactionRepositoryCustom.insertLog(transaction);
            throw new DataInvalidException(ErrorCode.ACCOUNT_NOT_EXIST);
        } else if (Double.valueOf(input.getAmount()) > debitAccount.getCurrentBalance().doubleValue()) {
            //nếu tài khoản chuyển tiền lớn hơn só tiền muốn chuyển thì không chuyển được tiền
            transaction.setRecipientAccountID(creditAccount.getAccountId());
            transaction.setStatus(Constants.STATUS_FAIL);
            transaction.setDescription(ErrorCode.getErrorMessage(ErrorCode.ACCOUNT_BALANCE_INVALID));
            transactionRepositoryCustom.insertLog(transaction);
            throw new DataInvalidException(ErrorCode.ACCOUNT_BALANCE_INVALID);
        } else {
            //trừ tiền của tài khoản gửi
            debitAccount.setCurrentBalance(debitAccount.getCurrentBalance().subtract(amount));
            accountRepository.save(debitAccount);
            //cộng tiền vào tài khoản nhận
            creditAccount.setCurrentBalance(creditAccount.getCurrentBalance().add(amount));
            accountRepository.save(creditAccount);
            //Lưu log giao dịch lên database
            transaction.setRecipientAccountID(creditAccount.getAccountId());
            transaction.setStatus(Constants.STATUS_SUCCESS);
            Transaction trans = transactionRepository.save(transaction);
            return TransferTransactionOutput.builder().id(trans.getTransactionID()).build();
        }
    }

    public TransferTransactionOutput doWithdrawMoney(Authentication authentication, TransferTransactionInput input) throws DataInvalidException {
        String userName = authentication.getName();
        User user = userRepository.findByUsername(userName);
        //tìm tài khoản mà muốn rút tiền
        Account debitAccount = accountRepository.findFirstByUserId(user.getId());
        BigDecimal amount = new BigDecimal(input.getAmount());
        //Tạo transaction mới
        Transaction transaction = new Transaction();
        transaction.setTransactionAmount(amount);
        transaction.setAccountId(debitAccount.getAccountId());
        transaction.setDescription(input.getDescription());
        Date transactionDate = new Date();
        transaction.setTransactionDate(transactionDate);
        transaction.setTransactionType("WITHDRAW");
        transaction.setTransactionAmount(amount);
        transaction.setUserId(user);
        if(input.getAmount() == null){
            transaction.setStatus(Constants.STATUS_FAIL);
            transaction.setDescription(ErrorCode.getErrorMessage(ErrorCode.NO_AMOUNT));
            transactionRepositoryCustom.insertLog(transaction);
            throw new DataInvalidException(ErrorCode.NO_AMOUNT);
        }else if (Double.valueOf(input.getAmount()) > debitAccount.getCurrentBalance().doubleValue()) {
            transaction.setStatus(Constants.STATUS_FAIL);
            transaction.setDescription(ErrorCode.getErrorMessage(ErrorCode.ACCOUNT_BALANCE_INVALID));
            transactionRepositoryCustom.insertLog(transaction);
            throw new DataInvalidException(ErrorCode.ACCOUNT_BALANCE_INVALID);
        } else {
            //lưu vào database
            transaction.setStatus(Constants.STATUS_WAITING);
            debitAccount.setCurrentBalance(debitAccount.getCurrentBalance().subtract(new BigDecimal(input.getAmount())));
            Transaction trans = transactionRepository.save(transaction);
            return TransferTransactionOutput.builder().id(trans.getTransactionID()).build();
        }
    }

    public TransferTransactionOutput doDepositMoney(Authentication authentication, TransferTransactionInput input) throws DataInvalidException {
        String userName = authentication.getName();
        User user = userRepository.findByUsername(userName);
        //tìm tài khoản mà muốn cho tiền vào
        Account debitAccount = accountRepository.findFirstByUserId(user.getId());
        BigDecimal amount = new BigDecimal(input.getAmount());
        //Tạo transaction mới
        Transaction transaction = new Transaction();
        transaction.setTransactionAmount(amount);
        transaction.setAccountId(debitAccount.getAccountId());
        transaction.setDescription(input.getDescription());
        Date transactionDate = new Date();
        transaction.setTransactionDate(transactionDate);
        transaction.setTransactionType("DEPOSIT");
        transaction.setTransactionAmount(amount);
        transaction.setUserId(user);
        if(input.getAmount() == null){
            transaction.setStatus(Constants.STATUS_FAIL);
            transaction.setDescription(ErrorCode.getErrorMessage(ErrorCode.NO_AMOUNT));
            transactionRepositoryCustom.insertLog(transaction);
            throw new DataInvalidException(ErrorCode.NO_AMOUNT);
        }else if (Double.valueOf(input.getAmount()) > 1000000) {
            transaction.setStatus(Constants.STATUS_FAIL);
            transaction.setDescription(ErrorCode.getErrorMessage(ErrorCode.TOO_MUCH_MONEY));
            transactionRepositoryCustom.insertLog(transaction);
            throw new DataInvalidException(ErrorCode.TOO_MUCH_MONEY);
        } else {
            //lưu vào database
            transaction.setStatus(Constants.STATUS_WAITING);
            debitAccount.setCurrentBalance(debitAccount.getCurrentBalance().add(new BigDecimal(input.getAmount())));
            Transaction trans = transactionRepository.save(transaction);
            return TransferTransactionOutput.builder().id(trans.getTransactionID()).build();
        }
    }
}