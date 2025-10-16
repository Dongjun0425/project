package com.shingu.university.repository;

import com.shingu.university.domain.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {
    List<University> findByType(String type);

    @Query("SELECT u FROM University u LEFT JOIN FETCH u.evaluations WHERE u.id = :id")
    Optional<University> findWithEvaluationsById(@Param("id") int id);

    List<University> findByNameContaining(String keyword);
    Optional<University> findByName(String name);

    Optional<University> findByCode(String code);  // ✅ 올바른 선언


    Optional<University> findByNameContainingIgnoreCase(String name);
}
