package com.example;

/**
 * A simple BankAccount class that supports deposits, withdrawals,
 * and basic account management.
 */
public class BankAccount {

    private String owner;
    private double balance;
    private boolean frozen;

    public BankAccount(String owner, double initialBalance) {
        if (owner == null || owner.isBlank()) {
            throw new IllegalArgumentException("Owner name cannot be null or blank.");
        }
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        this.owner = owner;
        this.balance = initialBalance;
        this.frozen = false;
    }

    public void deposit(double amount) {
        if (frozen) {
            throw new IllegalStateException("Cannot deposit into a frozen account.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        this.balance += amount;
    }

    public void withdraw(double amount) {
        if (frozen) {
            throw new IllegalStateException("Cannot withdraw from a frozen account.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (amount > balance) {
            throw new IllegalStateException("Insufficient funds.");
        }
        this.balance -= amount;
    }

    public void freeze() {
        this.frozen = true;
    }

    public void unfreeze() {
        this.frozen = false;
    }

    public double getBalance() {
        return balance;
    }

    public String getOwner() {
        return owner;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public String getSummary() {
        String status = frozen ? "FROZEN" : "ACTIVE";
        return String.format("Account[%s | %s | $%.2f]", owner, status, balance);
    }
}
