package com.acc.accountmanagementservice.controller;

import com.acc.accountmanagementservice.model.APIResponse;
import com.acc.accountmanagementservice.model.AccountDto;
import com.acc.accountmanagementservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<APIResponse<AccountDto>> createAccount(@RequestBody AccountDto accountDto) {
        APIResponse<AccountDto> response = accountService.createAccount(accountDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<AccountDto>> getAccountById(@PathVariable Long id) {
        APIResponse<AccountDto> response = accountService.getAccountById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<AccountDto>> updateAccount(@PathVariable Long id, @RequestBody AccountDto accountDto) {
        APIResponse<AccountDto> response = accountService.updateAccount(id, accountDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> deleteAccount(@PathVariable Long id) {
        APIResponse<String> response = accountService.deleteAccount(id);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}/balance")
    public ResponseEntity<APIResponse<AccountDto>> updateBalance(@PathVariable Long id, @RequestParam Double newBalance) {
        APIResponse<AccountDto> response = accountService.updateBalance(id, newBalance);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<AccountDto>>> getAllAccounts() {
        APIResponse<List<AccountDto>> response = accountService.getAllAccounts();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
