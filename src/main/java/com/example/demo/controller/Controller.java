package com.example.demo.controller;

import com.example.demo.dao.AccountDAO;
import com.example.demo.dao.KlineDAO;
import com.example.demo.model.Account;
import com.example.demo.model.Kline;
import com.example.demo.model.KlineRaw;
import com.example.demo.model.LoadResult;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    private TransferService transferService;

    @Autowired
    private KlineDAO klineDAO;

    @Autowired
    private KlineRawService klineRawService;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private KlineService klineService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getKlineData")
    public ResponseEntity<LoadResult> getKlineData(@RequestParam String symbol, @RequestParam String startingTime, @RequestParam String endingTime, @RequestParam String runName) {
        // Controller should be as clean as possible. Put logic into different services. Dao is about db

        // TODO USE LOG instead of system out println. log4j library
        validationService.validate(symbol, startingTime, endingTime, runName);
        Long startTime = Long.parseLong(startingTime);
        Long endTime = Long.parseLong(endingTime);
        klineService.load(symbol, startTime, endTime, runName);
        LoadResult result = new LoadResult("good", null);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
