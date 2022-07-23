package com.example.demo.service;

import com.example.demo.controller.Controller;
import com.example.demo.dao.ValidateDAO;
import com.example.demo.dao.mapper.ValidationMapper;
import com.example.demo.model.Symbol;
import com.example.demo.model.exception.InvalidTimeException;
import com.example.demo.model.exception.RunNameCollisionException;
import com.example.demo.model.exception.SymbolNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Arrays;


@Service
public class ValidationService {
    // runName: create a Dao,  check in the database
    @Autowired
    private ValidateDAO validateDAO;

    @Autowired
    private ValidationMapper mapper;

    public void validate(@NotBlank String symbol, @NotNull String startingTime, @NotNull String endingTime, @NotBlank String runName) {
        // validate symbol
        try {
            Symbol.valueOf(symbol);
        } catch (IllegalArgumentException e) {
            String error = String.format("Unsupported symbol %s, only supported %s", symbol, Arrays.asList(Symbol.values()));
            throw new SymbolNotFoundException(error);
        }
        // validate runName
        //if (validateDAO.checkRunName(runName)) {
        int count = mapper.count(runName);
        if (count > 0){
            throw new RunNameCollisionException("Run Name '" + runName + "' already exist");
        }
        Long startTime;
        Long endTime;
        try {         startTime = Long.parseLong(startingTime);
                      endTime = Long.parseLong(endingTime);
        } catch (Exception e) {
            // TODO add string format to print starting time and ending time in message
            throw new InvalidTimeException("starting time and ending time should be timestamp number");
        }
        if (startTime >= endTime) {
            throw new InvalidTimeException("Starting time should be earlier than ending time");
        }
    }
}
