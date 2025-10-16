package com.shingu.university.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String location;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    @Column
    private String code;

    @Column(nullable = false)
    private String type; // '전문', '일반'

    @OneToMany(mappedBy = "university")
    private List<Department> departments;

    @OneToMany(mappedBy = "university")
    private List<User> users;

    @OneToMany(mappedBy = "university")
    private List<UniversityEvaluation> evaluations;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

}
