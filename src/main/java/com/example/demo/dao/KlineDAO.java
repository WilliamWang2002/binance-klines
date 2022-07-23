package com.example.demo.dao;

import com.example.demo.model.Kline;
import com.example.demo.model.KlineRaw;

import java.util.List;

public interface KlineDAO {
    public void storeRawData(List<KlineRaw> list, String runName);
    public void storeTransData(List<Kline> list, String runName);
}

