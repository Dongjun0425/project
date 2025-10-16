package com.shingu.university.repository;

import com.shingu.university.domain.UniversityEvaluation;
import com.shingu.university.dto.UniversityRankingDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UniversityEvaluationRepository extends JpaRepository<UniversityEvaluation, Integer> {

    // 🔽 리뷰 목록 조회용 메서드 추가
    List<UniversityEvaluation> findByUniversityId(Integer universityId);

    @Query("SELECT new com.shingu.university.dto.UniversityRankingDto(e.university.id, e.university.name, AVG(e.professorScore)) " +
            "FROM UniversityEvaluation e GROUP BY e.university.id, e.university.name ORDER BY AVG(e.professorScore) DESC")
    List<UniversityRankingDto> findProfessorRanking();

    @Query("SELECT new com.shingu.university.dto.UniversityRankingDto(e.university.id, e.university.name, AVG(e.educationQualityScore)) " +
            "FROM UniversityEvaluation e GROUP BY e.university.id, e.university.name ORDER BY AVG(e.educationQualityScore) DESC")
    List<UniversityRankingDto> findEducationRanking();

    @Query("SELECT new com.shingu.university.dto.UniversityRankingDto(e.university.id, e.university.name, AVG(e.campusEnvironmentScore)) " +
            "FROM UniversityEvaluation e GROUP BY e.university.id, e.university.name ORDER BY AVG(e.campusEnvironmentScore) DESC")
    List<UniversityRankingDto> findCampusRanking();

    @Query("SELECT new com.shingu.university.dto.UniversityRankingDto(e.university.id, e.university.name, AVG(e.studentSatisfactionScore)) " +
            "FROM UniversityEvaluation e GROUP BY e.university.id, e.university.name ORDER BY AVG(e.studentSatisfactionScore) DESC")
    List<UniversityRankingDto> findSatisfactionRanking();

    @Query("SELECT new com.shingu.university.dto.UniversityRankingDto(e.university.id, e.university.name, AVG(e.careerSupportScore)) " +
            "FROM UniversityEvaluation e GROUP BY e.university.id, e.university.name ORDER BY AVG(e.careerSupportScore) DESC")
    List<UniversityRankingDto> findCareerRanking();

    @Query("SELECT new com.shingu.university.dto.UniversityRankingDto(e.university.id, e.university.name, AVG(e.clubActivityScore)) " +
            "FROM UniversityEvaluation e GROUP BY e.university.id, e.university.name ORDER BY AVG(e.clubActivityScore) DESC")
    List<UniversityRankingDto> findClubRanking();

    boolean existsByUserIdAndUniversityId(Integer userId, Integer universityId);
}
