// src/main/java/com/shingu/university/repository/StatisticDataRepository.java
package com.shingu.university.repository;

import com.shingu.university.domain.StatisticData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatisticDataRepository extends JpaRepository<StatisticData, Long> {
    List<StatisticData> findByUniversityId(Long univId);
}
