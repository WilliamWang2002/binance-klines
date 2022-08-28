package com.example.demo.dao;

import com.example.demo.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AccountDaoImpl implements AccountDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public Account findByID(int id) {
            Account account = jdbcTemplate.queryForObject("SELECT account_id, owner_name, amount FROM account WHERE account_id=?",
                    BeanPropertyRowMapper.newInstance(Account.class), id);
            return account;
    }

    @Override
    public void deposit(int id, double amount) {
        double currAmount = findByID(id).getAmount();
        jdbcTemplate.update("UPDATE account SET amount=? WHERE account_id=?", currAmount + amount, id);
    }

    @Override
    public void withdraw(int id, double amount) throws Exception {
        double currAmount = findByID(id).getAmount();
        if (currAmount < amount) {
            throw new Exception("Not enough balance to do the transaction");
        } else {
            jdbcTemplate.update("UPDATE account SET amount=? WHERE account_id=?", findByID(id).getAmount() - amount, id);
        }
    }

    @Override
    public void transfer(int fromId, int toId, double amount) throws Exception {
        double currAmount = findByID(fromId).getAmount();
        if (currAmount < amount) {
            throw new Exception("Not enough balance to do the transaction");
        } else {
            withdraw(fromId, amount);
             int x = 3 / 0;
            deposit(toId, amount);
        }
    }
}
