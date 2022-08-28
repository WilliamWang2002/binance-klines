package com.example.demo.service;

import com.example.demo.dao.KlineDAO;
import com.example.demo.model.Kline;
import com.example.demo.model.KlineRaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class TransferService {

    public List<Kline> transfer(List<KlineRaw> klineRawList) {

        return klineRawList.stream()
                .map(this::createKline)
                .collect(Collectors.toList());
    }

    private Kline createKline(KlineRaw klineRaw){
        return new Kline()
                .setOpenTime(new Timestamp(klineRaw.getOpenTime()))
                .setCloseTime(new Timestamp(klineRaw.getCloseTime()))
                .setOpen(Double.parseDouble(klineRaw.getOpen()))
                .setClose(Double.parseDouble(klineRaw.getClose()))
                .setHigh(Double.parseDouble(klineRaw.getHigh()))
                .setLow(Double.parseDouble(klineRaw.getLow()))
                .setVolume(Double.parseDouble(klineRaw.getVolume()))
                .setQuoteAssetVolume(Double.parseDouble(klineRaw.getQuoteAssetVolume()))
                .setNumOfTrades(klineRaw.getNumOfTrades());
    }
}
