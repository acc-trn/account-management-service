package com.acc.accountmanagementservice.service.impl;

import com.acc.accountmanagementservice.entity.Account;
import com.acc.accountmanagementservice.model.APIResponse;
import com.acc.accountmanagementservice.model.AccountDto;
import com.acc.accountmanagementservice.model.mapper.AccountMapper;
import com.acc.accountmanagementservice.repository.AccountRepository;
import com.acc.accountmanagementservice.service.AccountService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public APIResponse<AccountDto> createAccount(AccountDto accountDto) {
        Account account = accountMapper.dtoToModel(accountDto);
        Account savedAccount = accountRepository.save(account);
        return accountMapper.mapToApiResponse(savedAccount);
    }

    @Override
    public APIResponse<AccountDto> getAccountById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Account with id: %s not found", id)
                ));
        return accountMapper.mapToApiResponse(account);
    }

    @Override
    public APIResponse<AccountDto> updateAccount(Long id, AccountDto accountDto) {
        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Account with id: %s not found", id)
                ));
        existingAccount.setAccountName(accountDto.getAccountName());
        existingAccount.setBalance(accountDto.getBalance());

        Account updatedAccount = accountRepository.save(existingAccount);
        return accountMapper.mapToApiResponse(updatedAccount);
    }

    @Override
    public APIResponse<String> deleteAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Account with id: %s not found", id)
                ));
        accountRepository.delete(account);
        return new APIResponse<>("Account deleted successfully");
    }

    @Override
    public APIResponse<AccountDto> updateBalance(Long id, Double newBalance) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Account with id: %s not found", id)
                ));
        account.setBalance(newBalance);
        Account updatedAccount = accountRepository.save(account);
        return accountMapper.mapToApiResponse(updatedAccount);
    }

    @Override
    public APIResponse<List<AccountDto>> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accountMapper.mapListToApiResponse(accounts);
    }
}
