package com.shingu.university.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchoolInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "university_id")
    @JsonIgnore  // ✅ university → SchoolInfo → university 무한 반복 방지
    private University university;

    private String schlNm;
    private String schlDivNm;
    private String schlEstbDivNm;
    private String schlEstbDt;
    private String postNoAdrs;
    private String postNo;
    private String schlRepTpNoCtnt;
    private String schlRepFxNoCtnt;
    private String schlUrlAdrs;
}
