package com.bank.AccountMs.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

public enum AccountType {
    SAVINGS, CHECKING;

    @Getter
    @Setter
    public static class CreateAccountRequest {

        @NotNull
        private Long customerId;  // ID del cliente que será dueño de la cuenta

        @NotNull
        private AccountType accountType;  // Tipo de cuenta (AHORROS o CORRIENTE)


    }
}
