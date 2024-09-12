package com.acc.accountmanagementservice.controller;

import com.acc.accountmanagementservice.entity.Account;
import com.acc.accountmanagementservice.model.AccountDto;
import com.acc.accountmanagementservice.repository.AccountRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        accountRepository.deleteAll();
    }

    @Test
    void createAccount() throws Exception {
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountName("Test Account");
        accountDto.setBalance(1000.0);

        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.accountName").value("Test Account"))
                .andExpect(jsonPath("$.data.balance").value(1000.0));
    }

    @Test
    void getAccountById() throws Exception {
        Account account = new Account();
        account.setAccountName("Test Account");
        account.setBalance(1000.0);
        account = accountRepository.save(account);

        mockMvc.perform(get("/accounts/{id}", account.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.accountName").value("Test Account"))
                .andExpect(jsonPath("$.data.balance").value(1000.0));
    }

    @Test
    void updateAccount() throws Exception {
        Account account = new Account();
        account.setAccountName("Test Account");
        account.setBalance(1000.0);
        account = accountRepository.save(account);

        AccountDto accountDto = new AccountDto();
        accountDto.setAccountName("Updated Account");
        accountDto.setBalance(2000.0);

        mockMvc.perform(put("/accounts/{id}", account.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.accountName").value("Updated Account"))
                .andExpect(jsonPath("$.data.balance").value(2000.0));
    }

    @Test
    void deleteAccount() throws Exception {
        Account account = new Account();
        account.setAccountName("Test Account");
        account.setBalance(1000.0);
        account = accountRepository.save(account);

        mockMvc.perform(delete("/accounts/{id}", account.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    void updateBalance() throws Exception {
        Account account = new Account();
        account.setAccountName("Test Account");
        account.setBalance(1000.0);
        account = accountRepository.save(account);

        mockMvc.perform(put("/accounts/{id}/balance")
                        .param("newBalance", "2000.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.balance").value(2000.0));
    }

    @Test
    void getAllAccounts() throws Exception {
        Account account1 = new Account();
        account1.setAccountName("Test Account 1");
        account1.setBalance(1000.0);
        accountRepository.save(account1);

        Account account2 = new Account();
        account2.setAccountName("Test Account 2");
        account2.setBalance(2000.0);
        accountRepository.save(account2);

        mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(2));
    }
}