package com.honeybee.magok.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Entity
@Table(name = "price_bio")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class PriceBio {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mdate")
    private LocalDate date;

    @Column(name = "use_amount")
    private Integer useAmount;

    @Column(name = "unit")
    private Integer unit;

    @Column(name = "etc_price")
    private Integer etcPrice;

    @Column(name = "supply_price")
    private Integer supplyPrice;

    @Column(name = "vat")
    private Integer vat;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "memo")
    private String memo;


    @Builder
    public PriceBio(LocalDate date, Integer useAmount, Integer unit, Integer etcPrice, Integer supplyPrice, Integer vat, Integer totalPrice, String memo) {
        this.date = date;
        this.useAmount = useAmount;
        this.unit = unit;
        this.etcPrice = etcPrice;
        this.supplyPrice = supplyPrice;
        this.vat = vat;
        this.totalPrice = totalPrice;
        this.memo = memo;
    }

    public void update(String date, Integer heat, Integer unit, Integer etcPrice, Integer supplyPrice, Integer vat, Integer totalPrice, String memo) {
        this.date = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        this.useAmount = heat;
        this.unit = unit;
        this.etcPrice = etcPrice;
        this.supplyPrice = supplyPrice;
        this.vat = vat;
        this.totalPrice = totalPrice;
        this.memo = memo;
    }
}