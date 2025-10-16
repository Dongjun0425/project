package com.shingu.university.repository;

import com.shingu.university.domain.TuitionData;
import com.shingu.university.domain.University;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TuitionDataRepository extends JpaRepository<TuitionData, Long> {
    List<TuitionData> findByUniversityOrderByYearAsc(University university);
}
