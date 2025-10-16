// src/main/java/com/shingu/university/domain/StatisticData.java
package com.shingu.university.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class StatisticData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer year;

    private String type;  // "재학생충원율", "휴학생비율"

    private Double value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id")
    private University university;
}
