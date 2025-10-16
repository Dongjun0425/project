package com.shingu.university.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // ✅ int -> Integer로 변경

    private String email;
    private String password;
    private String name;

    @Column(name = "user_type")
    private String userType;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id")
    private University university;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    public Integer getUniversityId() {
        return university != null ? university.getId() : null;
    }

    public Integer getDepartmentId() {
        return department != null ? department.getId() : null;
    }
}