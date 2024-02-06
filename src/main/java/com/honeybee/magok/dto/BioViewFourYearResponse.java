package com.honeybee.magok.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BioViewFourYearResponse {
    private Integer month;
    private double valueOfAvg;
    private PriceBioResponse first;
    private PriceBioResponse second;
    private PriceBioResponse third;
    private PriceBioResponse forth;

    @Builder
    public BioViewFourYearResponse(Integer month, PriceBioResponse first, PriceBioResponse second, PriceBioResponse third, PriceBioResponse forth,double avg) {
        this.month = month;
        this.first = first;
        this.second = second;
        this.third = third;
        this.forth = forth;
        this.valueOfAvg=avg;
    }
}
