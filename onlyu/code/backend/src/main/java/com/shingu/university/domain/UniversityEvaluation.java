package com.shingu.university.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UniversityEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private float professorScore;
    private float educationQualityScore;
    private float campusEnvironmentScore;
    private float studentSatisfactionScore;
    private float careerSupportScore;
    private float clubActivityScore;

    @Column(columnDefinition = "TEXT")
    private String review;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
