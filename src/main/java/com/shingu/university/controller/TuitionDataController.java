package com.shingu.university.controller;

import com.shingu.university.domain.TuitionData;
import com.shingu.university.domain.University;
import com.shingu.university.repository.TuitionDataRepository;
import com.shingu.university.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tuition")
public class TuitionDataController {
    private final TuitionDataRepository tuitionDataRepository;
    private final UniversityRepository universityRepository;

    @GetMapping("/{universityId}")
    public List<TuitionData> getTuitionByUniversity(@PathVariable Long universityId) {
        University university = universityRepository.findById(universityId)
                .orElseThrow(() -> new IllegalArgumentException("대학 없음"));
        return tuitionDataRepository.findByUniversityOrderByYearAsc(university);
    }

}
