package com.bank.AccountMs.controller;

import com.bank.AccountMs.model.Account;
import com.bank.AccountMs.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/accounts")
@Tag(name = "Accounts", description = "Account Management")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Operation(summary = "Create a new account")
    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {

        return new ResponseEntity<>(accountService.createAccount(account), HttpStatus.CREATED);
    }

    @Operation(summary = "List all accounts")
    @GetMapping
    public List<Account> getAllAccounts() {

        return accountService.getAllAccounts();
    }

    @Operation(summary = "Get account by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {

        Account account = accountService.getAccountById(id);
        return account != null ? ResponseEntity.ok(account) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Deposit into an account")
    @PutMapping("/{accountId}/deposit")
    public ResponseEntity<Account> deposit(@PathVariable Long accountId, @RequestParam Double amount) {

        Account account = accountService.deposit(accountId, amount);
        return account != null ? ResponseEntity.ok(account) : ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Withdraw from an account")
    @PutMapping("/{accountId}/withdraw")
    public ResponseEntity<Account> withdraw(@PathVariable Long accountId, @RequestParam Double amount) {

        Account account = accountService.withdraw(accountId, amount);
        return account != null ? ResponseEntity.ok(account) : ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Delete an account")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {

        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }


}
