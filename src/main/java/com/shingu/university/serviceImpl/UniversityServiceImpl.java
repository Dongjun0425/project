package com.shingu.university.serviceImpl;

import com.shingu.university.domain.University;
import com.shingu.university.dto.UniversityDto;
import com.shingu.university.dto.UniversityEvaluationDto;
import com.shingu.university.dto.UniversityRankingDto;
import com.shingu.university.dto.UniversityResponse;
import com.shingu.university.repository.UniversityEvaluationRepository;
import com.shingu.university.repository.UniversityRepository;
import com.shingu.university.service.UniversityService;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Getter
public class UniversityServiceImpl implements UniversityService {
    private final UniversityRepository universityRepository;
    private final UniversityEvaluationRepository evaluationRepository;

    @Override
    public List<UniversityDto> getAllUniversity() {
        return universityRepository.findAll().stream()
                .map(university -> new UniversityDto(
                        university.getId(),
                        university.getName(),
                        university.getDescription(),
                        university.getLocation(),
                        university.getType(),
                        university.getLatitude(),
                        university.getLongitude(),
                        university.getImageUrl(),
                        university.getCode()
                ))
                .collect(Collectors.toList());
    }

    public List<UniversityDto> getUniversitiesByType(String type) {
        List<University> universities = universityRepository.findByType(type);
        return universities.stream()
                .map(UniversityDto::fromEntity)
                .collect(Collectors.toList());
    }


    @Override
    public UniversityResponse getUniversityDetail(int id) {
        University university = universityRepository.findWithEvaluationsById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 대학을 찾을 수 없습니다."));

        UniversityDto universityDto = new UniversityDto(
                university.getId(),
                university.getName(),
                university.getDescription(),
                university.getLocation(),
                university.getType(),
                university.getLatitude(),
                university.getLongitude(),
                university.getImageUrl(),
                university.getCode()
        );

        List<UniversityEvaluationDto> evaluationDtos = university.getEvaluations().stream()
                .map(e -> new UniversityEvaluationDto(
                        e.getProfessorScore(),
                        e.getEducationQualityScore(),
                        e.getCampusEnvironmentScore(),
                        e.getStudentSatisfactionScore(),
                        e.getCareerSupportScore(),
                        e.getClubActivityScore(),
                        e.getReview()
                ))
                .toList();

        return new UniversityResponse(universityDto, evaluationDtos);
    }

    public Map<String, List<UniversityRankingDto>> getAllCategoryRankings() {
        Map<String, List<UniversityRankingDto>> result = new HashMap<>();

        result.put("professor", evaluationRepository.findProfessorRanking());
        result.put("education", evaluationRepository.findEducationRanking());
        result.put("campus", evaluationRepository.findCampusRanking());
        result.put("satisfaction", evaluationRepository.findSatisfactionRanking());
        result.put("career", evaluationRepository.findCareerRanking());
        result.put("club", evaluationRepository.findClubRanking());

        return result;
    }

    public List<UniversityDto> searchUniversitiesByName(String keyword) {
        return universityRepository.findByNameContaining(keyword).stream()
                .map(UniversityDto::fromEntity)
                .collect(Collectors.toList());
    }


}
