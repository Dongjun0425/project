package com.shingu.university.dto;

import lombok.Data;

@Data
public class StatisticDataDto {
    private int year;
    private String type;
    private double value;

    public StatisticDataDto(int year, String type, double value) {
        this.year = year;
        this.type = type;
        this.value = value;
    }
}
