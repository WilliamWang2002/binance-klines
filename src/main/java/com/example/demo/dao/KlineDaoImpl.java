package com.example.demo.dao;

import com.example.demo.model.Kline;
import com.example.demo.model.KlineRaw;
import com.example.demo.model.KlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//TODO use mapper
@Repository
@Transactional
public class KlineDaoImpl implements KlineDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private KlineRepository klineRepository;

    @Value("${urlTemplate}")
    public String urlTemplate;



    public void storeRawData(List<KlineRaw> list, String runName) {
        jdbcTemplate.batchUpdate("INSERT INTO kline (open_time, open, high, low, close, volume, close_time,quote_asset_volume, num_of_trades, taker_base_volume, taker_quote_volume, ignore, type, run_name) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
            new BatchPreparedStatementSetter() {

                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    KlineRaw klineRaw = list.get(i);
                    ps.setLong(1, klineRaw.getOpenTime());
                    ps.setString(2, klineRaw.getOpen());
                    ps.setString(3, klineRaw.getHigh());
                    ps.setString(4, klineRaw.getLow());
                    ps.setString(5, klineRaw.getClose());
                    ps.setString(6, klineRaw.getVolume());
                    ps.setLong(7, klineRaw.getCloseTime());
                    ps.setString(8, klineRaw.getQuoteAssetVolume());
                    ps.setLong(9, klineRaw.getNumOfTrades());
                    ps.setString(10, klineRaw.getTakerBaseVolume());
                    ps.setString(11, klineRaw.getTakerQuoteVolume());
                    ps.setString(12, klineRaw.getIgnore());
                    ps.setString(13, "raw");
                    ps.setString(14, runName);
                }

                public int getBatchSize() {
                    return list.size();
                }
            });
    }

    public void storeTransData(List<Kline> list, String runName) {
        jdbcTemplate.batchUpdate("INSERT INTO kline_trans (open_time, open, high, low, close, volume, close_time, quote_asset_volume, num_of_trades, type, run_name) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                new BatchPreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        Kline kline = list.get(i);
                        ps.setTimestamp(1, kline.getOpenTime());
                        ps.setDouble(2, kline.getOpen());
                        ps.setDouble(3, kline.getHigh());
                        ps.setDouble(4, kline.getLow());
                        ps.setDouble(5, kline.getClose());
                        ps.setDouble(6, kline.getVolume());
                        ps.setTimestamp(7, kline.getCloseTime());
                        ps.setDouble(8, kline.getQuoteAssetVolume());
                        ps.setInt(9, kline.getNumOfTrades());
                        ps.setString(10, "transferred");
                        ps.setString(11, runName);
                    }

                    public int getBatchSize() {
                        return list.size();
                    }
                });

        // store to redis
        list.stream().
                forEach(kline -> klineRepository.save(kline));

    }

    public List<Kline> getData() {
        List<Kline> list = new ArrayList<>();
        klineRepository.findAll().forEach(list::add);
        return list;
    }

    // devops: 运维
    // Connect git: establish credicials
    // command: list of command e.g. git pull
    // build
    // deploy: sftp
    // CI/CD: continuous integration (git level) / continuous distribution (jenkins: automate command build deploy)
        // git hook jenkins
    // TODO install jenkins
    // TODO update resume --- 8.13 - 11.1 apply


}