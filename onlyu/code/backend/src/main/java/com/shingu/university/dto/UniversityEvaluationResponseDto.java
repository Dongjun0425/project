package com.shingu.university.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UniversityEvaluationResponseDto {

    private Integer id;
    private Integer universityId;
    private Integer userId;
    private float professorScore;
    private float educationQualityScore;
    private float campusEnvironmentScore;
    private float studentSatisfactionScore;
    private float careerSupportScore;
    private float clubActivityScore;
    private String review;
    private LocalDateTime createdAt;

    // ✅ 추가 필드
    private String userName;
    private String userType;
    private String universityName;
    private String departmentName;

    @Builder
    public UniversityEvaluationResponseDto(Integer id, Integer universityId, Integer userId,
                                           float professorScore, float educationQualityScore,
                                           float campusEnvironmentScore, float studentSatisfactionScore,
                                           float careerSupportScore, float clubActivityScore,
                                           String review, LocalDateTime createdAt,
                                           String userName, String userType,
                                           String universityName, String departmentName) {
        this.id = id;
        this.universityId = universityId;
        this.userId = userId;
        this.professorScore = professorScore;
        this.educationQualityScore = educationQualityScore;
        this.campusEnvironmentScore = campusEnvironmentScore;
        this.studentSatisfactionScore = studentSatisfactionScore;
        this.careerSupportScore = careerSupportScore;
        this.clubActivityScore = clubActivityScore;
        this.review = review;
        this.createdAt = createdAt;

        // ✅ 추가된 필드 초기화
        this.userName = userName;
        this.userType = userType;
        this.universityName = universityName;
        this.departmentName = departmentName;
    }
}
