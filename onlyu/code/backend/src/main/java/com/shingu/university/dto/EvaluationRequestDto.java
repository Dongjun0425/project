// dto/EvaluationRequestDto.java
package com.shingu.university.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationRequestDto {
    private Integer universityId;
    private Integer userId;
    private float professorScore;
    private float educationQualityScore;
    private float campusEnvironmentScore;
    private float studentSatisfactionScore;
    private float careerSupportScore;
    private float clubActivityScore;
    private String review;
}
