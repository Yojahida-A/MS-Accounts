package com.bank.AccountMs.service;


import com.bank.AccountMs.model.Account;
import com.bank.AccountMs.model.AccountType;
import com.bank.AccountMs.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account) {
        account.setAccountNumber(UUID.randomUUID().toString());
        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    public Account deposit(Long accountId, Double amount) {
        Account account = getAccountById(accountId);
        if (account != null) {
            account.setBalance(account.getBalance() + amount);
            return accountRepository.save(account);
        }
        return null;
    }

    public Account withdraw(Long accountId, Double amount) {
        Account account = getAccountById(accountId);
        if (account != null && account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            return accountRepository.save(account);
        }
        return null;
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

}
