package com.shingu.university.repository;
import com.shingu.university.domain.AdmissionStat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface AdmissionStatRepository extends JpaRepository<AdmissionStat, Long> {
    List<AdmissionStat> findByUniversityId(Long univId);
}
