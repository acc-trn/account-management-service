package com.acc.accountmanagementservice.repository;

import com.acc.accountmanagementservice.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
}
