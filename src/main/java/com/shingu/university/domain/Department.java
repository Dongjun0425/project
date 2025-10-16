package com.shingu.university.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    @Column(nullable = false)
    private String name;

    @Column(name = "total")
    private int total;

    @Column(name = "department_score")
    private float departmentScore;
}
