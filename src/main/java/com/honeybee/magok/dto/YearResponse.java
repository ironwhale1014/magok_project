package com.honeybee.magok.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class YearResponse<T> {
    private List<T> first;
    private List<T> second;
    private List<T> third;
    private List<T> forth;

    @Builder
    public YearResponse(List<T> first, List<T> second, List<T> third, List<T> forth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.forth = forth;
    }
}
