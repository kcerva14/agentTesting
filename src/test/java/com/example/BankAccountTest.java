package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {
    private BankAccount account;

    @BeforeEach
    public void setUp() {
        account = new BankAccount();
    }

    @Test
    public void testInitialBalance() {
        assertEquals(0.0, account.getBalance());
    }

    @Test
    public void testDepositPositiveAmount() {
        account.deposit(100.0);
        assertEquals(100.0, account.getBalance());
    }

    @Test
    public void testDepositZeroAmount() {
        account.deposit(0.0);
        assertEquals(0.0, account.getBalance());
    }

    @Test
    public void testDepositNegativeAmount() {
        account.deposit(-50.0);
        assertEquals(0.0, account.getBalance());
    }

    @Test
    public void testWithdrawFromEmptyAccount() {
        account.withdraw(50.0);
        assertEquals(0.0, account.getBalance());
    }

    @Test
    public void testWithdrawValidAmount() {
        account.deposit(100.0);
        account.withdraw(50.0);
        assertEquals(50.0, account.getBalance());
    }

    @Test
    public void testWithdrawExactBalance() {
        account.deposit(100.0);
        account.withdraw(100.0);
        assertEquals(0.0, account.getBalance());
    }

    @Test
    public void testWithdrawMoreThanBalance() {
        account.deposit(50.0);
        account.withdraw(100.0);
        assertEquals(50.0, account.getBalance());
    }

    @Test
    public void testWithdrawNegativeAmount() {
        account.deposit(100.0);
        account.withdraw(-50.0);
        assertEquals(100.0, account.getBalance());
    }

    @Test
    public void testMultipleDepositsAndWithdrawals() {
        account.deposit(200.0);
        account.withdraw(50.0);
        account.deposit(75.0);
        account.withdraw(25.0);
        assertEquals(200.0, account.getBalance());
    }
}
