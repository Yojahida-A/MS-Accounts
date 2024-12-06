package com.bank.AccountMs.controller;

import com.bank.AccountMs.model.Account;
import com.bank.AccountMs.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
@Tag(name = "Accounts", description = "Account Management")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Operation(summary = "Crear una cuenta", description ="Crear una cuenta del sistema")
    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {

        return new ResponseEntity<>(accountService.createAccount(account), HttpStatus.CREATED);
    }

    @Operation(summary = "Lista todas las cuentas", description ="Lista todas las cuentas del sistema")
    @GetMapping
    public List<Account> getAllAccounts() {

        return accountService.getAllAccounts();
    }


    @Operation(summary = "Busca una cuenta por ID", description = "Busca una cuenta del sistema por ID")
    @ApiResponse(responseCode = "404", description = "Cuenta no encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {

        Account account = accountService.getAccountById(id);
        return account != null ? ResponseEntity.ok(account) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Depósito en una cuenta", description ="Depósito en una cuenta del sistema")
    @PutMapping("/{accountId}/deposit")
    public ResponseEntity<Account> deposit(@PathVariable Long accountId, @RequestParam Double amount) {

        Account account = accountService.deposit(accountId, amount);
        return account != null ? ResponseEntity.ok(account) : ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Retiro desde una cuenta", description = "Retiro desde una cuenta del sistema")
    @PutMapping("/{accountId}/withdraw")
    public ResponseEntity<Account> withdraw(@PathVariable Long accountId, @RequestParam Double amount) {

        Account account = accountService.withdraw(accountId, amount);
        return account != null ? ResponseEntity.ok(account) : ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Elimina una cuenta", description ="Elimina una cuenta del sistema")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {

        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }


}
