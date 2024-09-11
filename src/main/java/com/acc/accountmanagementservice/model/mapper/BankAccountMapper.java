package com.acc.accountmanagementservice.model.mapper;

import com.acc.accountmanagementservice.entity.BankAccount;
import com.acc.accountmanagementservice.model.BankAccountDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BankAccountMapper {

    BankAccountDto modelToDto(BankAccount bankAccount);

    List<BankAccountDto> modelsToDtos(List<BankAccount> bankAccounts);

    // Mapping DTO back to Entity (Optional, if needed)
    BankAccount dtoToModel(BankAccountDto bankAccountDto);
}

