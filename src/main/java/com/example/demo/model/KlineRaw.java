package com.example.demo.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Accessors(chain = true)
public class KlineRaw {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Integer id;
    private Long openTime;
    private String open;
    private String high;
    private String low;
    private String close;
    private String volume;
    private Long closeTime;
    public String quoteAssetVolume;
    public Integer numOfTrades;
    public String takerBaseVolume;
    public String takerQuoteVolume;
    public String ignore;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        KlineRaw klineRaw = (KlineRaw) o;
//        return id != null && Objects.equals(id, klineRaw.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }
}
