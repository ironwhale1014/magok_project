package com.honeybee.magok.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;


@NoArgsConstructor
@Getter
public class ViewFourYearResponse<T extends IResponse> {
    private Integer month;
    private T first;
    private T second;
    private T third;
    private T forth;
    private IntSummaryStatistics useAmount;
    private IntSummaryStatistics supplyPrice;
    private IntSummaryStatistics payment;

    private SumNAvg useAmountSumNAvg;
    private SumNAvg supplyPriceSumNAvg;
    private SumNAvg paymentSumNAvg;


    @Builder
    public ViewFourYearResponse(Integer month, T first, T second,
                                T third, T forth) {

        List<T> priceBioResponses = new ArrayList<>();
        List<Integer> payments = new ArrayList<>();
        List<Integer> supplyPrices = new ArrayList<>();
        List<Integer> useAmounts = new ArrayList<>();

        this.month = month;
        this.first = first;
        this.second = second;
        this.third = third;
        this.forth = forth;

        if (!first.getMemo().equals("가짜")) {
            priceBioResponses.add(first);
        }
        if (!second.getMemo().equals("가짜")) {
            priceBioResponses.add(second);
        }
        if (!third.getMemo().equals("가짜")) {
            priceBioResponses.add(third);
        }

        if (!forth.getMemo().equals("가짜") && priceBioResponses.size() != 3) {
            priceBioResponses.add(forth);
        }

        for (T priceBioRespons : priceBioResponses) {
            useAmounts.add(priceBioRespons.getUseAmount());
            supplyPrices.add(priceBioRespons.getSupplyPrice());
            payments.add(priceBioRespons.getTotalPrice() - priceBioRespons.getVat());
        }

        this.payment = getStatistics(payments);
        this.supplyPrice = getStatistics(supplyPrices);
        this.useAmount = getStatistics(useAmounts);



        this.useAmountSumNAvg = SumNAvg.builder().avg( (double) Math.round(useAmount.getAverage() * 100) /100).sum(useAmount.getSum()).build();
        this.supplyPriceSumNAvg = SumNAvg.builder().avg((double) Math.round(supplyPrice.getAverage() * 100) /100).sum(supplyPrice.getSum()).build();
        this.paymentSumNAvg = SumNAvg.builder().avg((double) Math.round(payment.getAverage() * 100) /100).sum(payment.getSum()).build();
    }

    private IntSummaryStatistics getStatistics(List<Integer> val) {
        return val.stream().mapToInt(num -> num).summaryStatistics();
    }


    @Builder
    @Getter
    private static class SumNAvg {
        double avg;
        Long sum;

    }
}
