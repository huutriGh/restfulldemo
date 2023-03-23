package com.example.restfull.service;


import com.example.restfull.dto.LoginRequest;
import com.example.restfull.dto.LoginResponse;
import com.example.restfull.dto.TransferReponse;
import com.example.restfull.dto.TransferRequest;
import com.example.restfull.helper.JwtToken;
import com.example.restfull.model.Account;
import com.example.restfull.model.SaveTransaction;
import com.example.restfull.repository.IRepository;
import com.example.restfull.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@ApplicationScoped
public class AccountServiceImpl implements AccountService {

    IRepository<Account, String> accountRepository;
    IRepository<SaveTransaction, Integer> transactionRepository;

    public AccountServiceImpl() {
        accountRepository = new Repository<>();
        transactionRepository = new Repository<>();
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {

        accountRepository.setType(Account.class);
        Account account = accountRepository.getById(loginRequest.getAccountNo());
        LoginResponse response = new LoginResponse();
        if (account.getAccountNo().equals(loginRequest.getAccountNo()) && account.getPinCode().equals(loginRequest.getPinCode())) {
            response.setBalance(account.getBalance());
            response.setAccountName(account.getAccountName());
            response.setAccountNo(account.getAccountNo());
            response.setToken(JwtToken.createJWT(account));
        }
        return response;
    }

    @Override

    public TransferReponse transfer(TransferRequest transaction) {

        accountRepository.setType(Account.class);
        Account source = accountRepository.getById(transaction.getAccountSource());
        Account target = accountRepository.getById(transaction.getAccountTarget());
        TransferReponse transferReponse = new TransferReponse();
        if (source == null || target == null || transaction.getAmount() <= 0) {
            transferReponse.setMessage("Transfer fail !!!");
        }


        source.setBalance(source.getBalance() - transaction.getAmount());
        target.setBalance((target.getBalance()) + transaction.getAmount());
        accountRepository.update(source);
        accountRepository.update(target);

        SaveTransaction saveTransaction = new SaveTransaction();
        saveTransaction.setAccountNo(transaction.getAccountSource());
        saveTransaction.setAmount(transaction.getAmount());
        saveTransaction.setComment(transaction.getComment());

        saveTransaction.setTrandate(new Date());
        //saveTransaction.setAccount(source);


        transactionRepository.add(saveTransaction);

        transferReponse.setMessage("Transfer Success.");

        return transferReponse;


    }
}
