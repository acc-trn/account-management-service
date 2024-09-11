package com.acc.accountmanagementservice.service;

import com.acc.accountmanagementservice.model.APIResponse;
import com.acc.accountmanagementservice.model.AccountDto;

import java.util.List;

public interface AccountService {

    APIResponse<AccountDto> createAccount(AccountDto accountDto);

    APIResponse<AccountDto> getAccountById(Long id);

    APIResponse<AccountDto> updateAccount(Long id, AccountDto accountDto);

    APIResponse<Void> deleteAccount(Long id);

    APIResponse<AccountDto> updateBalance(Long id, Double newBalance);

    APIResponse<List<AccountDto>> getAllAccounts();

}
