package com.shingu.university.repository;

import com.shingu.university.domain.ScholarshipData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScholarshipRepository extends JpaRepository<ScholarshipData, Long> {
    List<ScholarshipData> findByUniversityId(Long universityId);
}