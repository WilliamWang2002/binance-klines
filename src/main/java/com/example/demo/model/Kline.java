package com.example.demo.model;

import javax.persistence.*;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Accessors(chain = true) //  similar to @Builder, so that you can . countinously
@RedisHash("Kline")
public class Kline implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    public Timestamp openTime;
    public double open;
    public double high;
    public double low;
    public double close;
    public double volume;
    public Timestamp closeTime;
    public double quoteAssetVolume;
    public int numOfTrades;
}
