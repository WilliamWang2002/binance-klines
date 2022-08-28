package com.example.demo.dao;

import com.example.demo.model.KlineRaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ValidateDaoImpl implements ValidateDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean checkRunName(String runName) {
        String query = "SELECT COUNT(*) FROM kline WHERE run_name = '" + runName + "'";
        int result = jdbcTemplate.queryForObject(query, Integer.class);
        return result != 0;
    }
}
