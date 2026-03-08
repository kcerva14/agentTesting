package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {
    private BankAccount account;

    @BeforeEach
    public void setUp() {
        account = new BankAccount("John Doe", 100.0);
    }

    @Test
    public void testInitialBalance() {
        assertEquals(100.0, account.getBalance());
    }

    @Test
    public void testGetOwner() {
        assertEquals("John Doe", account.getOwner());
    }

    @Test
    public void testAccountNotFrozenInitially() {
        assertFalse(account.isFrozen());
    }

    @Test
    public void testDepositPositiveAmount() {
        account.deposit(50.0);
        assertEquals(150.0, account.getBalance());
    }

    @Test
    public void testDepositZeroAmountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> account.deposit(0.0));
    }

    @Test
    public void testDepositNegativeAmountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-50.0));
    }

    @Test
    public void testWithdrawValidAmount() {
        account.withdraw(50.0);
        assertEquals(50.0, account.getBalance());
    }

    @Test
    public void testWithdrawExactBalance() {
        account.withdraw(100.0);
        assertEquals(0.0, account.getBalance());
    }

    @Test
    public void testWithdrawMoreThanBalanceThrowsException() {
        assertThrows(IllegalStateException.class, () -> account.withdraw(150.0));
    }

    @Test
    public void testWithdrawZeroAmountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(0.0));
    }

    @Test
    public void testWithdrawNegativeAmountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-50.0));
    }

    @Test
    public void testMultipleDepositsAndWithdrawals() {
        account.deposit(200.0);
        account.withdraw(50.0);
        account.deposit(75.0);
        account.withdraw(25.0);
        assertEquals(300.0, account.getBalance());
    }

    @Test
    public void testFreezeAccount() {
        account.freeze();
        assertTrue(account.isFrozen());
    }

    @Test
    public void testDepositOnFrozenAccountThrowsException() {
        account.freeze();
        assertThrows(IllegalStateException.class, () -> account.deposit(50.0));
    }

    @Test
    public void testWithdrawOnFrozenAccountThrowsException() {
        account.freeze();
        assertThrows(IllegalStateException.class, () -> account.withdraw(50.0));
    }

    @Test
    public void testUnfreezeAccount() {
        account.freeze();
        account.unfreeze();
        assertFalse(account.isFrozen());
    }

    @Test
    public void testDepositAfterUnfreeze() {
        account.freeze();
        account.unfreeze();
        account.deposit(50.0);
        assertEquals(150.0, account.getBalance());
    }

    @Test
    public void testGetSummary() {
        String summary = account.getSummary();
        assertTrue(summary.contains("John Doe"));
        assertTrue(summary.contains("ACTIVE"));
        assertTrue(summary.contains("100.00"));
    }

    @Test
    public void testGetSummaryWhenFrozen() {
        account.freeze();
        String summary = account.getSummary();
        assertTrue(summary.contains("FROZEN"));
    }

    @Test
    public void testConstructorWithNullOwnerThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount(null, 100.0));
    }

    @Test
    public void testConstructorWithBlankOwnerThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("   ", 100.0));
    }

    @Test
    public void testConstructorWithNegativeBalanceThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("John Doe", -100.0));
    }

    @Test
    public void testConstructorWithZeroBalance() {
        BankAccount zeroAccount = new BankAccount("Jane Doe", 0.0);
        assertEquals(0.0, zeroAccount.getBalance());
    }
}
