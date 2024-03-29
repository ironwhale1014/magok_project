package com.honeybee.magok.dto;

import com.honeybee.magok.domain.PriceBio;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class PriceBioResponse implements IResponse {

    private final LocalDate date;
    private final int year;
    private final int month;
    private final Integer useAmount;
    private final Integer unit;
    private final Integer supplyPrice;
    private final String memo;
    private final Integer totalPrice;
    private final Integer vat;


    public PriceBioResponse(PriceBio priceBio) {
        this.date = priceBio.getDate();
        this.useAmount = priceBio.getUseAmount();
        this.supplyPrice = priceBio.getSupplyPrice();
        this.unit = priceBio.getUnit();
        this.memo = priceBio.getMemo();
        this.year = date.getYear();
        this.month = date.getMonthValue();
        this.totalPrice = priceBio.getTotalPrice();
        this.vat = priceBio.getVat();
    }

}
