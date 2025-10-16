package com.shingu.university.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TuitionData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id")
    private University university;

    private Integer year; // 기준연도

    private Double totalAmount;   // 전체 평균 등록금 (avgTuit)
    private Double collegeAmount; // 대학 평균 등록금 (avgCollTuit)
    private Double gradAmount;    // 대학원 평균 등록금 (avgGradTuit)
}
