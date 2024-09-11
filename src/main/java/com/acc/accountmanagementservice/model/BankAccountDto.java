package com.acc.accountmanagementservice.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountDto {

    @NotNull(message = "Bank Name should not be null")
    @NotBlank(message = "Bank Name should not be empty")
    private String bankName;

    @NotNull(message = "Account Number should not be null")
    @NotBlank(message = "Account Number should not be empty")
    private String accountNumber;
}
