package com.example.demo.service;

import com.example.demo.dao.KlineDAO;
import com.example.demo.model.KlineRaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Service
public class KlineRawService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${urlTemplate}")
    public String urlTemplate;

    public List<KlineRaw> getKlineRawData(@NotBlank String symbol, Long startTime, Long endTime) {

        if ((endTime - startTime) / 1000 <= 1000 * 60) {
            return getData(symbol, startTime, endTime);
        }

        List<KlineRaw> list = new ArrayList<>();
        LongStream stream = LongStream.range(startTime, endTime);
        stream.forEach((time) -> {
            list.addAll(getData(symbol, startTime, time));
        });
        // Stream vs loop
//        while ((endTime - startTime) / 1000 > 1000 * 60) {
//            Long temp = startTime + 60 * 1000 * 1000;
//            list.addAll(getData(symbol, startTime, temp));
//            startTime = temp;
//        }
//        list.addAll(getData(symbol, startTime, endTime));
        return list;
    }

    private List<KlineRaw> getData(String symbol, Long startingTime, Long endTime) {
        String url = String.format(urlTemplate, symbol, startingTime, endTime);
        //TODO log url
        String[][] res = restTemplate.getForObject(url, String[][].class);

        assert res != null;
        return Arrays.stream(res)
                .map(this::createKlineRaw).toList();
    }

    private KlineRaw createKlineRaw(String[] k){
        return new KlineRaw().
                setOpenTime(Long.parseLong(k[0]))
                .setOpen(k[1])
                .setHigh(k[2])
                .setLow(k[3])
                .setClose(k[4])
                .setVolume(k[5])
                .setCloseTime(Long.parseLong(k[0]))
                .setQuoteAssetVolume(k[7])
                .setNumOfTrades(Integer.valueOf(k[8]))
                .setTakerBaseVolume(k[9])
                .setTakerQuoteVolume(k[10])
                .setIgnore( k[11]);
    }

}
