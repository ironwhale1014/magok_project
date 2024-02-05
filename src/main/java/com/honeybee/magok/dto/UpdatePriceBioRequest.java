package com.honeybee.magok.dto;

import lombok.*;

/**
 * DTO for {@link com.honeybee.magok.domain.PriceBio}
 */

@Getter
public class UpdatePriceBioRequest {

    private final String date;
    private final Integer useAmount;
    private final Integer unit;
    private final Integer etcPrice;
    private final Integer supplyPrice;
    private final Integer vat;
    private final Integer totalPrice;
    private final String memo;


    @Builder
    public UpdatePriceBioRequest(String date, Integer useAmount, Integer unit, Integer etcPrice, Integer supplyPrice, Integer vat, Integer totalPrice, String memo) {
        this.date = date;
        this.useAmount = useAmount;
        this.unit = unit;
        this.etcPrice = etcPrice;
        this.supplyPrice = supplyPrice;
        this.vat = vat;
        this.totalPrice = totalPrice;
        this.memo = memo;
    }
}