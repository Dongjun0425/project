package com.shingu.university.controller;

import com.shingu.university.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/batch")
@RequiredArgsConstructor
public class AdminBatchController {

    private final TuitionBatchService tuitionBatchService;
    private final StatisticDataBatchService statisticDataBatchService;
    private final SchoolInfoService schoolInfoService;
    private final AdmissionStatService admissionStatService;
    private final ScholarshipBatchService scholarshipBatchService;

    @GetMapping("/tuition")
    public ResponseEntity<String> fetchTuition() {
        tuitionBatchService.fetchAndSaveTuitionForAllUniv();
        return ResponseEntity.ok("Tuition data fetched successfully.");
    }

    @GetMapping("/scholarship")
    public ResponseEntity<String> fetchScholarship() {
        scholarshipBatchService.fetchAndSaveScholarshipForAllUniv();
        return ResponseEntity.ok("Scholarship data fetched successfully.");
    }


    // 🎯 입학전형 등록률만 저장
    @GetMapping("/admission-statistics")
    public ResponseEntity<String> fetchAndSaveAdmissionStats() {
        admissionStatService.fetchAndSaveAdmissionStats();
        return ResponseEntity.ok("입학전형 등록률 저장 완료");
    }

    // 🎯 재학생충원율, 휴학생비율 저장
    @GetMapping("/student-statistics")
    public ResponseEntity<String> fetchAndSaveStudentStats() {
        statisticDataBatchService.fetchAndSaveStudentEnrollmentRate();
        statisticDataBatchService.fetchAndSaveLeaveOfAbsenceRate();
        statisticDataBatchService.fetchAndSaveFreshmanCompetitionRate();
        statisticDataBatchService.fetchAndSaveGraduateEmploymentRate();
        statisticDataBatchService.fetchAndSaveFreshmanEnsureStatus();
        statisticDataBatchService.fetchAndSaveForeignStudentStatus();
        return ResponseEntity.ok("재학생충원율 및 휴학생비율 저장 완료");
    }


    @PostMapping("/schoolinfo")
    public String loadSchoolInfo() {
        schoolInfoService.fetchAndSaveSchoolInfo();
        return "학교 기본정보 저장 완료";
    }


    @PostMapping("/admission")
    public String runAdmission() {
        admissionStatService.fetchAndSaveAdmissionStats();
        return "입학전형 통계 저장 완료";
    }

}
