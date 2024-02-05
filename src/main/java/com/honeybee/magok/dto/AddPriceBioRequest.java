package com.honeybee.magok.dto;

import com.honeybee.magok.domain.PriceBio;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.honeybee.magok.domain.PriceBio}
 */
@Value
public class AddPriceBioRequest implements Serializable {
    String date;
    Integer useAmount;
    Integer unit;
    Integer etcPrice;
    String memo;

}