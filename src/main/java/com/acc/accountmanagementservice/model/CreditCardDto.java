package com.acc.accountmanagementservice.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardDto {

    @NotNull(message = "Card Number should not be null")
    @NotBlank(message = "Card Number should not be empty")
    private String cardNumber;

    @NotNull(message = "Card Holder Name should not be null")
    @NotBlank(message = "Card Holder Name should not be empty")
    private String cardHolderName;

    @NotNull(message = "Credit Limit should not be null")
    @Positive(message = "Credit Limit should be a positive number")
    private Double creditLimit;
}
