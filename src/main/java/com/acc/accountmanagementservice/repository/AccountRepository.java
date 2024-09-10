package com.acc.accountmanagementservice.repository;

import com.acc.accountmanagementservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
