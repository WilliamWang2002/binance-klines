package com.example.demo.service;

import com.example.demo.dao.KlineDAO;
import com.example.demo.model.Frequency;
import com.example.demo.model.Kline;
import com.example.demo.model.KlineRaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class KlineService {
    @Autowired
    private KlineRawService klineRawService;

    @Autowired
    private TransferService transferService;

    @Autowired
    private RollingUpService rollingUpService;

    @Autowired
    private KlineDAO klineDAO;


    @Transactional
    public List<Kline> load(@NotBlank String symbol, @NotNull Long startingTime, @NotNull Long endingTime, @NotBlank String runName, String frequency) {


        List<KlineRaw> listRaw = klineRawService.getKlineRawData(symbol, startingTime, endingTime);
        klineDAO.storeRawData(listRaw, runName);
        List<Kline> listTrans = transferService.transfer(listRaw);
        if (frequency != "") {
            Frequency f = Frequency.valueOf(frequency);
            List<Kline> listRolled = rollingUpService.rollingUp(listTrans, f);
            klineDAO.storeTransData(listRolled, runName);
            return listRolled;
        } else {
            klineDAO.storeTransData(listTrans, runName);
            return listTrans;
        }
    }

}
