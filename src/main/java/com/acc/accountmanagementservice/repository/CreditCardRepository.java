package com.acc.accountmanagementservice.repository;

import com.acc.accountmanagementservice.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
