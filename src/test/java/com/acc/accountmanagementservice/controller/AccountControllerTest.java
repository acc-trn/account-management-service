package com.acc.accountmanagementservice.controller;

import com.acc.accountmanagementservice.model.APIResponse;
import com.acc.accountmanagementservice.model.AccountDto;
import com.acc.accountmanagementservice.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAccount() {
        AccountDto accountDto = new AccountDto();
        APIResponse<AccountDto> apiResponse = new APIResponse<>();
        when(accountService.createAccount(any(AccountDto.class))).thenReturn(apiResponse);

        ResponseEntity<APIResponse<AccountDto>> response = accountController.createAccount(accountDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(apiResponse, response.getBody());
        verify(accountService, times(1)).createAccount(any(AccountDto.class));
    }

    @Test
    void getAccountById() {
        Long id = 1L;
        APIResponse<AccountDto> apiResponse = new APIResponse<>();
        when(accountService.getAccountById(id)).thenReturn(apiResponse);

        ResponseEntity<APIResponse<AccountDto>> response = accountController.getAccountById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(apiResponse, response.getBody());
        verify(accountService, times(1)).getAccountById(id);
    }

    @Test
    void updateAccount() {
        Long id = 1L;
        AccountDto accountDto = new AccountDto();
        APIResponse<AccountDto> apiResponse = new APIResponse<>();
        when(accountService.updateAccount(id, accountDto)).thenReturn(apiResponse);

        ResponseEntity<APIResponse<AccountDto>> response = accountController.updateAccount(id, accountDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(apiResponse, response.getBody());
        verify(accountService, times(1)).updateAccount(id, accountDto);
    }

    @Test
    void deleteAccount() {
        Long id = 1L;
        APIResponse<String> apiResponse = new APIResponse<>("Account deleted successfully");
        when(accountService.deleteAccount(id)).thenReturn(apiResponse);

        ResponseEntity<APIResponse<String>> response = accountController.deleteAccount(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(apiResponse, response.getBody());
        verify(accountService, times(1)).deleteAccount(id);
    }

    @Test
    void updateBalance() {
        Long id = 1L;
        Double newBalance = 100.0;
        APIResponse<AccountDto> apiResponse = new APIResponse<>();
        when(accountService.updateBalance(id, newBalance)).thenReturn(apiResponse);

        ResponseEntity<APIResponse<AccountDto>> response = accountController.updateBalance(id, newBalance);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(apiResponse, response.getBody());
        verify(accountService, times(1)).updateBalance(id, newBalance);
    }

    @Test
    void getAllAccounts() {
        List<AccountDto> accountDtos = Arrays.asList(new AccountDto(), new AccountDto());
        APIResponse<List<AccountDto>> apiResponse = new APIResponse<>(accountDtos);
        when(accountService.getAllAccounts()).thenReturn(apiResponse);

        ResponseEntity<APIResponse<List<AccountDto>>> response = accountController.getAllAccounts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(apiResponse, response.getBody());
        verify(accountService, times(1)).getAllAccounts();
    }
}