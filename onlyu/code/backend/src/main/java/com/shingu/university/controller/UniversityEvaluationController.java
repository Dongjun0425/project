package com.shingu.university.controller;

import com.shingu.university.dto.EvaluationRequestDto;
import com.shingu.university.dto.UniversityEvaluationResponseDto;
import com.shingu.university.service.UniversityEvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluations")
@RequiredArgsConstructor
public class UniversityEvaluationController {

    private final UniversityEvaluationService evaluationService;

    // 평가 제출 API
    @PostMapping
    public ResponseEntity<UniversityEvaluationResponseDto> submitEvaluation(@RequestBody EvaluationRequestDto dto) {
        UniversityEvaluationResponseDto responseDto = evaluationService.submitEvaluation(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // 평가 생성 (응답 없음)
    @PostMapping("/create")
    public ResponseEntity<Void> createEvaluation(@RequestBody EvaluationRequestDto requestDto) {
        evaluationService.createEvaluation(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 전체 평가 목록 조회
    @GetMapping
    public ResponseEntity<List<UniversityEvaluationResponseDto>> getAllEvaluations() {
        List<UniversityEvaluationResponseDto> evaluations = evaluationService.getAllEvaluations();
        return ResponseEntity.ok(evaluations);
    }

    // ✅ 특정 대학교의 평가 목록 조회
    @GetMapping("/university/{universityId}")
    public ResponseEntity<List<UniversityEvaluationResponseDto>> getEvaluationsByUniversity(@PathVariable Integer universityId) {
        List<UniversityEvaluationResponseDto> evaluations = evaluationService.getEvaluationsByUniversityId(universityId);
        return ResponseEntity.ok(evaluations);
    }
}
