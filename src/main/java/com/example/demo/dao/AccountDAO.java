package com.example.demo.dao;

import com.example.demo.model.Account;

public interface AccountDAO {
    public Account findByID(int id);
    public void deposit(int id, double amount);
    public void withdraw(int id, double amount) throws Exception;
    public void transfer(int fromId, int toId, double amount) throws Exception;
}
