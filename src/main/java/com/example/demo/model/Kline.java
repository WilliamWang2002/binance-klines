package com.example.demo.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Kline {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    public int id;
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
