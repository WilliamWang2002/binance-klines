package com.example.demo.service;

import com.example.demo.model.Frequency;
import com.example.demo.model.Kline;
import com.example.demo.model.KlineRaw;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class RollingUpService {

    public List<Kline> rollingUp(List<Kline> list, Frequency f) {
        List<Kline> res = new ArrayList<>();
        for(int i = 0; i < list.size(); i+= f.minutes){
            int end = i + f.minutes;
            end = Math.min(end, list.size());
            Kline k = convert(list,i, end);
            res.add(k);
        }
        return res;

    }

    protected Kline convert(List<Kline> list, int start, int endExclude){
        Kline k = new Kline();
        // set beginning
        Kline kStart = list.get(start);
        k.setOpenTime(kStart.getOpenTime());
        k.setOpen(kStart.getOpen());
        // set ending
        Kline kEnd = list.get(endExclude-1);
        k.setCloseTime(kEnd.getCloseTime());
        k.setClose(kEnd.getClose());
        // calculate high, low, volume
        double high = 0, low = 0, volume = 0;
        for(int i = start; i < endExclude; i++){
            Kline current = list.get(i);
            high = Math.max(current.getHigh(), high);
            low = Math.min(low, current.getLow());
            volume += current.getVolume();
        }
        k.setVolume(volume);
        k.setHigh(high);
        k.setLow(low);
        return k;
    }

    // TODO configratable on UI through param
    // TODO front end add a candle graph
    // TODO install redis and docker, play around with them
    // TODO store result to redis
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
    // [10000] -> [1100] pagination



//    public List<Kline> rollingUp(List<Kline> list, String rollingUpAmount) {
//        List<Kline> result = new ArrayList<>();
//        int numOfMinute = 0;
//        if (rollingUpAmount.equals("d")) {
//            // TODO: frequncy let user input
//            numOfMinute = 1440;
//        } else if (rollingUpAmount.equals("m")) {
//            numOfMinute = 43200;
//        } else {
//            return list;
//        }
//        // TODO: convert list to list of list, [[Kline],[Kline],[Kline],[kline]]
//        // calculate
//        int numOfRollingUpAmount = (int) (Math.floor(list.size() / numOfMinute) + 1);
//        for (int i = 0; i < numOfRollingUpAmount; i++) {
//            double open = 0;
//            double high = 0;
//            double low = 0;
//            double close = 0;
//            double volume = 0;
//            Timestamp openTime = list.get(i * numOfMinute).getOpenTime();
//            Timestamp closeTime;
//
//            if (i == numOfRollingUpAmount - 1) {
//                closeTime = list.get(list.size() - 1).getCloseTime();
//                for (int j = i * numOfMinute; j < list.size(); j++) {
//                    Kline k = list.get(j);
//                    open = Math.max(open, k.getOpen());
//                    high = Math.max(high, k.getHigh());
//                    low = Math.min(low, k.getLow());
//                    close = Math.min(close, k.getClose());
//                    volume = volume + k.getVolume();
//                }
//            } else {
//                closeTime = list.get((i + 1) * numOfMinute - 1).getCloseTime();
//                for (int j = i * numOfMinute; j < (i + 1) * numOfMinute; j++) {
//                    Kline k = list.get(j);
//                    open = Math.max(open, k.getOpen());
//                    high = Math.max(high, k.getHigh());
//                    low = Math.min(low, k.getLow());
//                    close = Math.min(close, k.getClose());
//                    volume = volume + k.getVolume();
//                }
//            }
//            result.add(createKline(openTime, closeTime, open, close, high, low, volume));
//        }
//
//        return result;
//    }
//
////    public void updateValue(Kline k, double open, double high, double low, double close, double volume, double quoteAssetvolume, int numOfTrades) {
////        open = Math.max(open, k.getOpen());
////        high = Math.max(high, k.getHigh());
////        low = Math.min(low, k.getLow());
////        close = Math.min(close, k.getClose());
////        volume = volume + k.getVolume();
////        quoteAssetvolume = quoteAssetvolume + k.getQuoteAssetVolume();
////        numOfTrades = numOfTrades + k.getNumOfTrades();
////    }
//
//    private Kline createKline(Timestamp openTime, Timestamp closeTime, double open, double high, double low, double close, double volume){
//        return new Kline().
//                setOpenTime(openTime)
//                .setOpen(open)
//                .setHigh(high)
//                .setLow(low)
//                .setClose(close)
//                .setVolume(volume)
//                .setCloseTime(closeTime);
//    }
}
