package com.shingu.university.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UniversityRankingDto {
    private Integer universityId;
    private String universityName;
    private double averageScore;
}