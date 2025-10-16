package com.shingu.university.controller;

import com.shingu.university.dto.UniversityDto;
import com.shingu.university.dto.UniversityRankingDto;
import com.shingu.university.dto.UniversityResponse;
import com.shingu.university.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("university")
public class UniversityController {
    private final UniversityService universityService;

    @GetMapping("/all")
    public List<UniversityDto> getAll() {
        return universityService.getAllUniversity();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UniversityResponse> getUniversityDetail(@PathVariable int id) {
        return ResponseEntity.ok(universityService.getUniversityDetail(id));
    }

    // 특정 타입(일반/전문)별 대학 조회
    @GetMapping("/type/{type}")
    public List<UniversityDto> getUniversitiesByType(@PathVariable String type) {
        return universityService.getUniversitiesByType(type);
    }

    @GetMapping("/rankings/all")
    public ResponseEntity<Map<String, List<UniversityRankingDto>>> getAllCategoryRankings() {
        return ResponseEntity.ok(universityService.getAllCategoryRankings());
    }

    @GetMapping("/search")
    public ResponseEntity<List<UniversityDto>> searchUniversities(@RequestParam String keyword) {
        if (keyword.length() < 1) {
            return ResponseEntity.badRequest().body(List.of()); // 한 글자 이상일 때만 검색
        }
        List<UniversityDto> result = universityService.searchUniversitiesByName(keyword);
        return ResponseEntity.ok(result);
    }
}