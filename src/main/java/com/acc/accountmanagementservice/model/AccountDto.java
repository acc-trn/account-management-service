package com.acc.accountmanagementservice.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    @NotNull(message = "Account Name should not be null")
    @NotBlank(message = "Account Name should not be empty")
    private String accountName;

    @NotNull(message = "Balance should not be null")
    @Positive(message = "Balance should be a positive number")
    private Double balance;

    private List<BankAccountDto> bankAccounts;

    private List<CreditCardDto> creditCards;

}