package com.acc.accountmanagementservice.service;

import com.acc.accountmanagementservice.entity.Account;
import com.acc.accountmanagementservice.model.APIResponse;
import com.acc.accountmanagementservice.model.AccountDto;
import com.acc.accountmanagementservice.model.mapper.AccountMapper;
import com.acc.accountmanagementservice.repository.AccountRepository;
import com.acc.accountmanagementservice.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountMapper accountMapper;

    @InjectMocks
    private AccountServiceImpl accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAccount() {
        AccountDto accountDto = new AccountDto();
        Account account = new Account();
        Account savedAccount = new Account();
        APIResponse<AccountDto> apiResponse = new APIResponse<>();

        when(accountMapper.dtoToModel(accountDto)).thenReturn(account);
        when(accountRepository.save(account)).thenReturn(savedAccount);
        when(accountMapper.mapToApiResponse(savedAccount)).thenReturn(apiResponse);

        APIResponse<AccountDto> result = accountService.createAccount(accountDto);

        assertEquals(apiResponse, result);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    void getAccountById() {
        Long id = 1L;
        Account account = new Account();
        APIResponse<AccountDto> apiResponse = new APIResponse<>();

        when(accountRepository.findById(id)).thenReturn(Optional.of(account));
        when(accountMapper.mapToApiResponse(account)).thenReturn(apiResponse);

        APIResponse<AccountDto> result = accountService.getAccountById(id);

        assertEquals(apiResponse, result);
        verify(accountRepository, times(1)).findById(id);
    }

    @Test
    void getAccountById_NotFound() {
        Long id = 1L;

        when(accountRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> accountService.getAccountById(id));
        verify(accountRepository, times(1)).findById(id);
    }

    @Test
    void updateAccount() {
        Long id = 1L;
        AccountDto accountDto = new AccountDto();
        Account existingAccount = new Account();
        Account updatedAccount = new Account();
        APIResponse<AccountDto> apiResponse = new APIResponse<>();

        when(accountRepository.findById(id)).thenReturn(Optional.of(existingAccount));
        when(accountRepository.save(existingAccount)).thenReturn(updatedAccount);
        when(accountMapper.mapToApiResponse(updatedAccount)).thenReturn(apiResponse);

        APIResponse<AccountDto> result = accountService.updateAccount(id, accountDto);

        assertEquals(apiResponse, result);
        verify(accountRepository, times(1)).findById(id);
        verify(accountRepository, times(1)).save(existingAccount);
    }

    @Test
    void updateAccount_NotFound() {
        Long id = 1L;
        AccountDto accountDto = new AccountDto();

        when(accountRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> accountService.updateAccount(id, accountDto));
        verify(accountRepository, times(1)).findById(id);
    }

    @Test
    void deleteAccount() {
        Long id = 1L;
        Account account = new Account();
        APIResponse<String> apiResponse = new APIResponse<>("Account deleted successfully");

        when(accountRepository.findById(id)).thenReturn(Optional.of(account));

        APIResponse<String> result = accountService.deleteAccount(id);

        assertEquals(apiResponse, result);
        verify(accountRepository, times(1)).findById(id);
        verify(accountRepository, times(1)).delete(account);
    }

    @Test
    void deleteAccount_NotFound() {
        Long id = 1L;

        when(accountRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> accountService.deleteAccount(id));
        verify(accountRepository, times(1)).findById(id);
    }

    @Test
    void updateBalance() {
        Long id = 1L;
        Double newBalance = 100.0;
        Account account = new Account();
        Account updatedAccount = new Account();
        APIResponse<AccountDto> apiResponse = new APIResponse<>();

        when(accountRepository.findById(id)).thenReturn(Optional.of(account));
        when(accountRepository.save(account)).thenReturn(updatedAccount);
        when(accountMapper.mapToApiResponse(updatedAccount)).thenReturn(apiResponse);

        APIResponse<AccountDto> result = accountService.updateBalance(id, newBalance);

        assertEquals(apiResponse, result);
        verify(accountRepository, times(1)).findById(id);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    void updateBalance_NotFound() {
        Long id = 1L;
        Double newBalance = 100.0;

        when(accountRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> accountService.updateBalance(id, newBalance));
        verify(accountRepository, times(1)).findById(id);
    }

    @Test
    void getAllAccounts() {
        List<Account> accounts = Arrays.asList(new Account(), new Account());
        APIResponse<List<AccountDto>> apiResponse = new APIResponse<>();

        when(accountRepository.findAll()).thenReturn(accounts);
        when(accountMapper.mapListToApiResponse(accounts)).thenReturn(apiResponse);

        APIResponse<List<AccountDto>> result = accountService.getAllAccounts();

        assertEquals(apiResponse, result);
        verify(accountRepository, times(1)).findAll();
    }
}