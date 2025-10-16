package com.shingu.university.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UniversityEvaluationDto {
    private float professorScore;
    private float educationQualityScore;
    private float campusEnvironmentScore;
    private float studentSatisfactionScore;
    private float careerSupportScore;
    private float clubActivityScore;
    private String review;
}
