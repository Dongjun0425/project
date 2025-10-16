package com.shingu.university.controller;

import com.shingu.university.domain.SchoolInfo;
import com.shingu.university.domain.University;
import com.shingu.university.dto.UniversityDetailDto;
import com.shingu.university.repository.SchoolInfoRepository;
import com.shingu.university.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schoolinfo")
@RequiredArgsConstructor
public class SchoolInfoController {

    private final SchoolInfoRepository schoolInfoRepository;
    private final UniversityRepository universityRepository;

    @GetMapping("/{univId}")
    public UniversityDetailDto getSchoolInfo(@PathVariable Long univId) {
        University university = universityRepository.findById(univId)
                .orElseThrow(() -> new IllegalArgumentException("해당 대학이 없습니다."));
        SchoolInfo schoolInfo = schoolInfoRepository.findByUniversityId(univId);

        return UniversityDetailDto.from(university, schoolInfo);
    }
}

