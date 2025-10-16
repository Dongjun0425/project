package com.shingu.university.service;

import com.shingu.university.domain.University;
import com.shingu.university.domain.UniversityEvaluation;
import com.shingu.university.domain.User;
import com.shingu.university.dto.EvaluationRequestDto;
import com.shingu.university.dto.UniversityEvaluationResponseDto;
import com.shingu.university.repository.UniversityEvaluationRepository;
import com.shingu.university.repository.UniversityRepository;
import com.shingu.university.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UniversityEvaluationService {

    private final UniversityEvaluationRepository evaluationRepository;
    private final UserRepository userRepository;
    private final UniversityRepository universityRepository;

    public Optional<UniversityEvaluation> findById(Integer id) {
        return evaluationRepository.findById(id);
    }

    public UniversityEvaluationResponseDto submitEvaluation(EvaluationRequestDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));

        University university = user.getUniversity();
        if (university == null || university.getId() == null) {
            throw new IllegalStateException("사용자의 소속 대학 정보가 없습니다.");
        }

        boolean hasAlreadyEvaluated = evaluationRepository.existsByUserIdAndUniversityId(
                dto.getUserId(),
                dto.getUniversityId()
        );
        if (hasAlreadyEvaluated) {
            throw new IllegalStateException("이미 해당 대학교에 대한 평가를 완료하였습니다.");
        }

        Integer universityIdFromUser = university.getId();
        Integer universityIdFromDto = dto.getUniversityId();
        String userType = user.getUserType() != null ? user.getUserType().trim() : "";

        if (universityIdFromUser == null ||
                universityIdFromDto == null ||
                !universityIdFromDto.equals(universityIdFromUser) ||
                !(userType.equals("재학생") || userType.equals("졸업생"))) {
            throw new IllegalStateException("해당 대학의 재학생 또는 졸업생만 평가할 수 있습니다.");
        }

        UniversityEvaluation evaluation = UniversityEvaluation.builder()
                .university(university)
                .user(user)
                .professorScore(dto.getProfessorScore())
                .educationQualityScore(dto.getEducationQualityScore())
                .campusEnvironmentScore(dto.getCampusEnvironmentScore())
                .studentSatisfactionScore(dto.getStudentSatisfactionScore())
                .careerSupportScore(dto.getCareerSupportScore())
                .clubActivityScore(dto.getClubActivityScore())
                .review(dto.getReview())
                .createdAt(LocalDateTime.now())
                .build();

        UniversityEvaluation saved = evaluationRepository.save(evaluation);

        return UniversityEvaluationResponseDto.builder()
                .id(saved.getId())
                .universityId(saved.getUniversity().getId())
                .userId(saved.getUser().getId())
                .professorScore(saved.getProfessorScore())
                .educationQualityScore(saved.getEducationQualityScore())
                .campusEnvironmentScore(saved.getCampusEnvironmentScore())
                .studentSatisfactionScore(saved.getStudentSatisfactionScore())
                .careerSupportScore(saved.getCareerSupportScore())
                .clubActivityScore(saved.getClubActivityScore())
                .review(saved.getReview())
                .createdAt(saved.getCreatedAt())
                .userName(saved.getUser().getName())
                .userType(saved.getUser().getUserType())
                .universityName(saved.getUniversity().getName())
                // ✅ 수정: user.getDepartmentName()을 사용
                .departmentName(saved.getUser().getDepartmentName()) // ✅ user.getDepartmentName() 사용
                .build();
    }

    public void createEvaluation(EvaluationRequestDto dto) {
        submitEvaluation(dto);
    }

    public List<UniversityEvaluationResponseDto> getAllEvaluations() {
        List<UniversityEvaluation> evaluations = evaluationRepository.findAll();

        return evaluations.stream().map(e -> UniversityEvaluationResponseDto.builder()
                .id(e.getId())
                .universityId(e.getUniversity().getId())
                .userId(e.getUser().getId())
                .professorScore(e.getProfessorScore())
                .educationQualityScore(e.getEducationQualityScore())
                .campusEnvironmentScore(e.getCampusEnvironmentScore())
                .studentSatisfactionScore(e.getStudentSatisfactionScore())
                .careerSupportScore(e.getCareerSupportScore())
                .clubActivityScore(e.getClubActivityScore())
                .review(e.getReview())
                .createdAt(e.getCreatedAt())
                .userName(e.getUser().getName())
                .userType(e.getUser().getUserType())
                .universityName(e.getUniversity().getName())
                // ✅ 수정: e.getUser().getDepartmentName()을 사용
                .departmentName(e.getUser().getDepartmentName()) // ✅ e.getUser().getDepartmentName() 사용
                .build()
        ).collect(Collectors.toList());
    }

    public List<UniversityEvaluationResponseDto> getEvaluationsByUniversityId(Integer universityId) {
        // ✅ Department 엔티티를 LAZY 로딩하기 때문에, N+1 문제 발생 가능성 방지 및 Department Name 즉시 로드를 위해 fetch join 또는 EntityGraph 고려
        // 여기서는 User 엔티티의 departmentName 필드를 직접 사용하므로 이 문제는 회피됩니다.
        List<UniversityEvaluation> evaluations = evaluationRepository.findByUniversityId(universityId);

        return evaluations.stream().map(e -> UniversityEvaluationResponseDto.builder()
                .id(e.getId())
                .universityId(e.getUniversity().getId())
                .userId(e.getUser().getId())
                .professorScore(e.getProfessorScore())
                .educationQualityScore(e.getEducationQualityScore())
                .campusEnvironmentScore(e.getCampusEnvironmentScore())
                .studentSatisfactionScore(e.getStudentSatisfactionScore())
                .careerSupportScore(e.getCareerSupportScore())
                .clubActivityScore(e.getClubActivityScore())
                .review(e.getReview())
                .createdAt(e.getCreatedAt())
                .userName(e.getUser().getName())
                .userType(e.getUser().getUserType())
                .universityName(e.getUniversity().getName())
                // ✅ 수정: e.getUser().getDepartmentName()을 사용
                .departmentName(e.getUser().getDepartmentName()) // ✅ e.getUser().getDepartmentName() 사용
                .build()
        ).collect(Collectors.toList());
    }
}