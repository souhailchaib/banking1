package com.banking;

public class User {
    int userId;
    double balance;

    public User(int userId, double balance) {
        this.userId = userId;
        this.balance = balance;
    }
    public int getUserId() {
        return userId;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    
}
