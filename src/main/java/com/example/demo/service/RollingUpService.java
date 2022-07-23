package com.example.demo.service;

import com.example.demo.model.Kline;
import com.example.demo.model.KlineRaw;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class RollingUpService {
    // TODO finish rollingupService, 1m to 1 day/month configratable on UI through param
    // TODO front end add a candle graph
    // TODO push to git
    // TODO install redis and docker, play around with them
    // redis cache
        // timeout
        // classer: 1024 * 64g
        // cache strategy: when full, then LRU cache
        // service degradation
            // if redis crashed
        // normally store on RAM
        // store calculated data to prevent redundent loading
        // https://redis.io/download/
    // C++: build result cannot cross-platform   C++ -> compiler -> binary code
    // JAVA: java byte code -> JVM -> binary code. Compile once, run anywhere
    // Docker:     docker engine: translator
    public List<Kline> rollingUp(List<Kline> list, String rollingUpAmount) {
        List<Kline> result = new ArrayList<>();
        int numOfMinute = 0;
        if (rollingUpAmount.equals("d")) {
            numOfMinute = 1440;
        } else if (rollingUpAmount.equals("m")) {
            numOfMinute = 43200;
        } else {
            return list;
        }
            for (int i = 0; i < list.size() % numOfMinute + 1; i++) {
                double open = 0;
                double high = 0;
                double low = 0;
                double close = 0;
                double volume = 0;
                double quoteAssetVolume = 0;
                int numOfTrades = 0;
                Timestamp openTime = list.get(i * numOfMinute).getOpenTime();
                Timestamp closeTime;

                if (i == list.size() % numOfMinute) {
                    closeTime = list.get(list.size() - 1).getCloseTime();
                    for (int j = i * numOfMinute; j < list.size(); j++) {
                        Kline k = list.get(j);
                        updateValue(k, open, high, low, close, volume, quoteAssetVolume, numOfTrades);
                    }
                } else {
                    closeTime = list.get((i + 1) * numOfMinute - 1).getCloseTime();
                    for (int j = i * numOfMinute; j < (i + 1) * numOfMinute; j++) {
                        Kline k = list.get(j);
                        updateValue(k, open, high, low, close, volume, quoteAssetVolume, numOfTrades);
                    }
                }
                result.add(createKline(openTime, closeTime, open, close, high, low, volume, quoteAssetVolume, numOfTrades));
            }

        return result;
    }

    public void updateValue(Kline k, double open, double high, double low, double close, double volume, double quoteAssetvolume, int numOfTrades) {
        open = Math.max(open, k.getOpen());
        high = Math.max(high, k.getHigh());
        low = Math.max(low, k.getLow());
        volume = Math.max(close, k.getClose());
        quoteAssetvolume = quoteAssetvolume + k.getQuoteAssetVolume();
        numOfTrades = numOfTrades + k.getNumOfTrades();
    }

    private Kline createKline(Timestamp openTime, Timestamp closeTime, double open, double high, double low, double close, double volume, double quoteAssetvolume, int numOfTrades){
        return new Kline().
                setOpenTime(openTime)
                .setOpen(open)
                .setHigh(high)
                .setLow(low)
                .setClose(close)
                .setVolume(volume)
                .setCloseTime(closeTime)
                .setQuoteAssetVolume(quoteAssetvolume)
                .setNumOfTrades(numOfTrades);
    }
}
