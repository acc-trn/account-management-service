package com.acc.accountmanagementservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class AccountManagementServiceApplicationTests {

	@Test
	void contextLoads() {
		assertDoesNotThrow(() -> AccountManagementServiceApplication.main(new String[] {}));
	}
}