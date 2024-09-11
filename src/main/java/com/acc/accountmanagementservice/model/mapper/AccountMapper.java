package com.acc.accountmanagementservice.model.mapper;

import com.acc.accountmanagementservice.entity.Account;
import com.acc.accountmanagementservice.model.AccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BankAccountMapper.class, CreditCardMapper.class})
public interface AccountMapper extends APIResponseMapper<Account, AccountDto> {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Override
    @Mapping(source = "bankAccounts", target = "bankAccountDtos")
    @Mapping(source = "creditCards", target = "creditCardDtos")
    AccountDto modelToDto(Account account);

    List<AccountDto> modelsToDtos(List<Account> accounts);

    Account dtoToModel(AccountDto accountDto);

}

