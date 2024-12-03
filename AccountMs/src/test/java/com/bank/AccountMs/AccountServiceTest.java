package com.bank.AccountMs;

import com.bank.AccountMs.model.Account;
import com.bank.AccountMs.model.AccountType;
import com.bank.AccountMs.repository.AccountRepository;
import com.bank.AccountMs.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAccount() {
        // Dado un nuevo Account
        Account account = new Account();
        account.setAccountType(AccountType.SAVINGS);
        account.setCustomerId(1L);

        when(accountRepository.save(account)).thenReturn(account);

        // Cuando se llama a createAccount
        Account createdAccount = accountService.createAccount(account);

        // Entonces
        assertNotNull(createdAccount);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    public void testGetAccountById() {
        // Dado un Account existente
        Account account = new Account();
        account.setId(1L);
        account.setAccountNumber("1000526");

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        // Cuando se llama a getAccountById
        Account foundAccount = accountService.getAccountById(1L);

        // Entonces
        assertNotNull(foundAccount);
        assertEquals("1000526", foundAccount.getAccountNumber());
        verify(accountRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeposit() {
        // Dado un Account existente con balance
        Account account = new Account();
        account.setId(1L);
        account.setBalance(1200);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(accountRepository.save(account)).thenReturn(account);

        // Cuando se llama a deposit
        Account updatedAccount = accountService.deposit(1L, 200.0);

        // Entonces el balance debe aumentar
        assertNotNull(updatedAccount);
        assertEquals(1400.0, updatedAccount.getBalance());
        verify(accountRepository, times(1)).findById(1L);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    public void testWithdraw() {
        // Dado un Account existente con saldo suficiente
        Account account = new Account();
        account.setId(1L);
        account.setBalance(3000.0);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(accountRepository.save(account)).thenReturn(account);

        // Cuando se llama a withdraw
        Account updatedAccount = accountService.withdraw(1L, 200.0);

        // Entonces el balance debe disminuir
        assertNotNull(updatedAccount);
        assertEquals(2800.0, updatedAccount.getBalance());
        verify(accountRepository, times(1)).findById(1L);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    public void testDeleteAccount() {
        // Dado que el Account existe
        when(accountRepository.existsById(1L)).thenReturn(true);

        // Cuando se llama a deleteAccount
        accountService.deleteAccount(1L);

        // Entonces se elimina la cuenta
        verify(accountRepository, times(1)).deleteById(1L);
    }


}
