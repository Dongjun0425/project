package com.shingu.university.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AdmissionStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id")
    private University university;

    private Integer year;
    private String schoolName;
    private String schoolKind;          // SCHOOL_KIND_NM
    private String foundDiv;            // FOUND_DIV_NM
    private String capacityDivision;    // PSN_CAPA_DIV_NM
    private String entranceType;        // SCRI_TYPE_NM
    private String scriLargeClass;      // SCRI_LRGE_CLASS_NM
    private String scriMedClass;        // SCRI_MED_CLASS_NM
    private String scriSmallClass;      // SCRI_SM_CLASS_NM

    private Integer anytimeRecruitCount;   // ANYTM_RECRUT_PSN_CNT
    private Integer anytimeRegisterCount;  // ANYTM_REGIST_PSN_CNT
    private Double anytimeRate;            // ANYTM_REGIST_RT

    private Integer regrRecruitCount;      // REGR_RECRUT_PSN_CNT
    private Integer regrRegisterCount;     // REGR_REGIST_PSN_CNT
    private Double regrRate;               // REGR_REGIST_RT

    private Integer addRecruitCount;       // ADD_RECRUT_PSN_CNT
    private Integer addRegisterCount;      // ADD_REGIST_PSN_CNT
    private Double addRate;                // ADD_REGIST_RT

    private Integer lastRecruitCount;      // LAST_RECRUT_PSN_CNT
    private Integer lastRegisterCount;     // LAST_REGIST_PSN_CNT
    private Double registrationRate;       // LAST_REGIST_RT
}
